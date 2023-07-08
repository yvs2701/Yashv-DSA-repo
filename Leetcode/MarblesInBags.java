/* Leetcode 2251: "Put Marbles in Bags"
You have k bags. You are given a 0-indexed integer array weights where weights[i] is the weight of the ith marble.
Divide the marbles into the k bags according to the following rules:
- No bag is empty.
- If the ith marble and jth marble are in a bag, then all marbles with an index between the ith and jth indices should also be in that same bag.
- If a bag consists of all the marbles with an index from i to j inclusively, then the cost of the bag is weights[i] + weights[j].

The score after distributing the marbles is the sum of the costs of all the k bags.
Return the difference between the maximum and minimum scores among marble distributions.

Example 1:
    Input: weights = [1,3,5,1], k = 2
    Output: 4
Explanation: 
The distribution [1],[3,5,1] results in the minimal score of (1+1) + (3+1) = 6. 
The distribution [1,3],[5,1], results in the maximal score of (1+3) + (5+1) = 10. 
Thus, we return their difference 10 - 6 = 4.

Example 2:
    Input: weights = [1, 3], k = 2
    Output: 0
Explanation: The only distribution possible is [1],[3]. 
Since both the maximal and minimal score are the same, we return 0.
*/

import java.util.Arrays;
import java.util.Scanner;

class MarblesInBags {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), k = sc.nextInt(), weights[] = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = sc.nextInt();
        }
        sc.close();

        System.out.println(putMarbles(weights, k));
    }

    static long putMarbles(int[] weights, int k) {
        /* To split into k bags, we actually choose (k - 1) cut points:
            A[0]...A[i1], A[i1+1]...A[i2], A[i2+1]...A[i3], ..., A[ik+1]...A[n-1]
        
            The result score is: (A[0] + A[i1]) + (A[i1+1] + A[i2]) + (A[i2+1] + ...
        
            So the problem turns out to be, calculate the max/min sum of
            k - 1 numbers in: A[0] + A[1], A[1] + A[2], ..., A[n-1] + A[n].
            We can simply sort them in O(sort) or we can apply a priority queue in O(nlogk).
        */
        int n = weights.length - 1;
        long dp[] = new long[n];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = weights[i] + weights[i + 1];
        }

        Arrays.sort(dp); // nlog(n) time complexity

        long res = 0;
        for (int i = 0; i < k - 1; i++) {
            // towards the end we will have maximal score (dp[n - 1 - i])
            // towards the beginning we will have minimal score (dp[i])
            res += dp[n - 1 - i] - dp[i];
        }

        return res;
    }
}
