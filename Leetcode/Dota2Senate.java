import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList;

/* Leetcode 649
The Dota2 senate consists of senators coming from two parties "Radiant" and "Dire". Now the Senate wants to decide on a change in the Dota2 game. The voting for this change is a round-based procedure. In each round, each senator can exercise one of the two rights:
- Ban one senator's right: A senator can make another senator lose all his rights in this and all the following rounds.
- Announce the victory: If this senator found the senators who still have rights to vote are all from the same party, he can announce the victory and decide on the change in the game.
Given a string senate representing each senator's party belonging. The character 'R' and 'D' represent the Radiant party and the Dire party. Then if there are n senators, the size of the given string will be n.

The round-based procedure starts from the first senator to the last senator in the given order. This procedure will last until the end of voting. All the senators who have lost their rights will be skipped during the procedure.
Suppose every senator is smart enough and will play the best strategy for his own party. Predict which party will finally announce the victory and change the Dota2 game. The output should be "Radiant" or "Dire".

input: DRRDRDRDRDDRDRDR
output: Radiant
explanation:
    0: "DRRDRDRDRDDRDRDR"
    1: "DXRXRXRXRXDXDXD"
    2: "DXRXRXRX"
    3: "XXXXRXRX" -> R wins
    At the 3th round, D will ban the first R, and be banned by the 2nd R. The 3rd R will Announce the victory.
    So It should be "Radiant".*/

class Dota2Senate {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        sc.close();
        String solution = (new Solution()).predictPartyVictory(in);
        System.out.println(solution);
    }
}

class Solution { /* FASTER SOLUTION */
    public String predictPartyVictory(String senate) {
        Queue<Integer> qr = new LinkedList<>();
        Queue<Integer> qd = new LinkedList<>();
        int n = senate.length();

        // Store indices FOR R and D senators
        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R') {
                qr.offer(i);
            } else {
                qd.offer(i);
            }
        }

        // Voting happens till either party has lost all senators
        while (!qr.isEmpty() && !qd.isEmpty()) {
            int r_id = qr.poll(); // remove this R and store its index (pop)
            int d_id = qd.poll(); // remove this D and store its index (pop)

            // If R was standing before D, R will ban D, and vice versa (Queue.offer() = Queue.add() except that it doesnt throw an error)
            if (r_id < d_id) {
                qr.offer(r_id + n); // add updated index of this R (this R can be banned only after all other Rs in the string)
            } else {
                qd.offer(d_id + n); // add updated index of this D (this D can be banned only after all other Ds in the string)
            }
        }

        return (qr.size() > qd.size()) ? "Radiant" : "Dire";
    }
}

class MySolution { /* MEMORY EFFICIENT */
    public String predictPartyVictory(String senate) {
        char[] arr = senate.toCharArray();
        int n = arr.length, d = 0, r = 0;

        for (int i = 0; i < n; ++i)
            if (arr[i] == 'R') ++r; else ++d;

        char turn = 'X'; // senator who is attempting to ban another senator
        int nxt = 0; // index to jump back to (in order to carry on voting in the specified order)
        for (int i = 0; r > 0 && d > 0;) {
            i = i % n;

            if (arr[i] != 'X' && turn == 'X') {
                turn = arr[i];
                nxt = i + 1; // jump back to next element after banning an opposing senator
            }
            else if (arr[i] != 'X' && turn != arr[i]) {
                if(arr[i] == 'R') --r; else --d;
                arr[i] = 'X'; // banned a senator
                turn = 'X'; // reset the turn
                i = nxt; // jump back, to continue voting in specified order
                continue;
            }
            ++i;
        }

        return (r > 0) ? "Radiant" : "Dire";
    }
}
