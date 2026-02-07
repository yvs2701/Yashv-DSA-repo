/* LeetCode quest "Queue" Q2: Time Needed to Buy Tickets

There are n people in a line queuing to buy tickets, where the 0th person is at the front of the
 * line and the (n - 1)th person is at the back of the line. You are given a 0-indexed array tickets
 * of length n where the number of tickets that the ith person would like to buy is tickets[i].
 * Each person takes exactly 1 second to buy a ticket. A person can only buy 1 ticket at a time
 * and has to go back to the end of the line (which happens instantaneously) in order to buy
 * more tickets. If a person does not have any tickets left to buy, the person will leave the line.
 *
 * Return the time taken for the person initially at position k (0-indexed) to finish buying tickets.
 *
 * Example 1:
 *  Input: tickets = [2,3,2], k = 2
 *  Output: 6
 * Example 2:
 *  Input: tickets = [5,1,1,1], k = 0
 *  Output: 8
 * Example 3:
 *  Input: tickets = [1,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2,1], k = 8
 *  Output: 81 */

public class TimeNeededToBuyTickets {

    /**
     * <pre> For the given queue of n people indexed as -> 0 1 2 ... k ... (n-1) </pre>
     * <p> For people standing in front of k:
     *     <ul>
     *         <li> If they need more tickets than tickets[k], they will always be present
     *         in the queue (and take 1 second to buy a ticket) till k buys all the ticket (= tickets[k]).
     *         Each such person contributes "tickets[k] * 1" seconds to the time. </li>
     *         <li> If they need less ticket than tickets[k], they will be gone when they have bought
     *         all the tickets they needed (= tickets[i]). Each ticket takes 1 second to buy.
     *         They will contribute "tickets[i] * 1" seconds to the time. </li>
     *      </ul>
     * </p>
     * <p> For people standing behind k: Exact same logic should be applied but remember that k has
     * already bought 1 ticket. For any person who need more than "tickets[k] - 1"
     * will take "(tickets[k] - 1) * 1" seconds time. Rest will take "tickets[i] * 1" seconds. </p>
     */
    public int timeRequiredToBuy(int[] tickets, int k) {
        int time = 0;
        for (int i = 0; i <= k; i++) {
            time += Math.min(tickets[i], tickets[k]);
        }
        tickets[k]--;
        for (int i = k + 1; i < tickets.length; i++) {
            time += Math.min(tickets[i], tickets[k]);
        }

        return time;
    }

    public static void main(String[] args) {
        TimeNeededToBuyTickets solution = new TimeNeededToBuyTickets();
        int[][][] testcases = {
                {{2, 3, 2}, {2}},
                {{5, 1, 1, 1}, {0}},
                {{1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 4, 3, 2, 1}, {8}}
        };
        for (int[][] testcase : testcases) {
            int[] tickets = testcase[0];
            int k = testcase[1][0];
            System.out.println("Input: tickets = " + java.util.Arrays.toString(tickets) + ", k = " + k);
            int output = solution.timeRequiredToBuy(tickets, k);
            System.out.println("Output: " + output);
        }
    }
}
