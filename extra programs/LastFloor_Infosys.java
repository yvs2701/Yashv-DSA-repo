/* Rida and Hussein are staying in a hotel, and they decided to play a game. They have a grid A that consists of N rows and M columns.
At each cell there's a card with a namber A[i][j]. A[i][j] is the number that's written on the card in the ith row and jth column.
In this game they take turns in the following way:
In the first turn Rida chooses a row and picks the leftmost card in that fow. 
Let the number on that card be x, then Rida and Hussein go x floors up. Then comes Hussein's turn.
He chooses a row and picks the rightmost card in that row, Let the number arpthat card be y.
Then Rida and Hussein go y floors down.
Then it's Rida's turn again.

The game ends when thefe's no more cards to pick.
Rida wants to go as high as possible, while Hussein wants to go as low as possible

Assume that both Rida and Hussein choose their-cards optimally.
Determine the last floor that Rida and Hussein will be in when the game ends.

Notes:
N*M<=10^6
It is given that Rida and Hussein start playing the game at the floor number 0.
The number might be negative which means that they are underground.
The hotel has an infinite number of floors.
Sample:
Input- 3 1 5 7 3
Output- 5
    We have 3x1 grid, 
    1st row: card 1 = 5, 
    2nd row: card 2 = 7,
    3rd row: card 3 = 3
    Rida picks leftmost = 5 floors up
    Hussein picks 7 floors down
    Rida picks 3 floors up; FINAL FLOOR = 5 */
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

class LastFloor_Infosys {
    private static int getAnswer(List<List<Integer>> list) {
        int max = 0;
        int curr_floor = 0;
        int n;
        while (!list.isEmpty()) {
            // Player 1's turn (Ridlist)
            n = list.size(); max = 0;
            for (int i = 0; i < n; i++) {
                if (!list.get(i).isEmpty())
                    if (list.get(i).get(0) > list.get(max).get(0))
                        max = i;
            }
            curr_floor += list.get(max).remove(0);
            if (list.get(max).isEmpty()) list.remove(max);

            if (list.isEmpty()) break;

            // Player 2's turn (Hussein)
            n = list.size(); max = 0;
            for (int i = 0; i < n; i++) {
                if (!list.get(i).isEmpty()) {
                    int size = list.get(i).size();
                    if (list.get(i).get(size - 1) > list.get(max).get(list.get(max).size() - 1))
                        max = i;
                }
            }
            curr_floor -= list.get(max).remove(list.get(max).size() - 1);
            if (list.get(max).isEmpty()) list.remove(max);
        }
        
        return curr_floor;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        List<List<Integer>> list = new ArrayList<>(N);
        List<Integer> arr = new ArrayList<>(M);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++)
                arr.set(j, sc.nextInt());
            list.set(i, arr);
        }
        sc.close();
        System.out.println(getAnswer(list));
    }
}
