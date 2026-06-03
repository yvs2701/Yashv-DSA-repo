/**
 * <h3><a href="https://leetcode.com/problems/sum-of-square-numbers/description/">LeetCode 633. Sum of Square Numbers</a></h3>
 * Given a non-negative integer c, decide whether there are two integers a and b such that a^2 + b^2 = c.
 * <p>
 * Example 1: <br>
 * Input: c = 5 <br>
 * Output: true <br>
 * Explanation: 1 * 1 + 2 * 2 = 5
 * <p>
 * Example 2: <br>
 * Input: c = 3
 * <p>
 * Constraints: <br>
 * 0 <= c <= 2^31 - 1 <br>
 */
public class SumOfSquareNumbers_BinarySearch {
    /**
     * Fermat Theorem
     * Any positive number n is expressible as a sum of two squares
     * if and only if the prime factorization of n, every prime of
     * the form (4k+3) occurs an even number of times.
     * To do so we simply find all the prime factors of the given number c,
     * which could range from [2,sqrt(c)] along with the count of
     * factors, by repeated division. If at any step, we find out that the
     * number of occurrences of any prime factor of the form (4k+3) occurs
     * an odd number of times, we can return a False value.
     * In case, c itself is a prime number, it won't be divisible by any of
     * the primes in the [2,sqrt(c)]. Thus, we need to check if c can be
     * expressed in the form of 4k+3. If so, we need to return a False
     * value, indicating that this prime occurs an odd number(1) of times.
     * Otherwise, we can return a True value.
     */
    static boolean judgeSquareSum_mathsFermatTheorem(int c) {
        // Time: O(sqrt(c))
        for (int i = 2; i * i <= c; i++) {
            int count = 0;
            if (c % i == 0) {
                while (c % i == 0) {
                    count++;
                    c /= i;
                }
                if (i % 4 == 3 && count % 2 != 0) return false;
            }
        }
        return c % 4 != 3;
    }

    static boolean judgeSquareSum_optimizedBruteForce(int c) {
        // Time: O(sqrt(c) * log(c, 2))
        // If a^2 + b^2 = c, then c - b^2 = a^2
        // We need to find a number "a" in the range [0, c - b^2]
        // such that a^2 = c - b^2
        for (long b = 0; b * b <= c; b++) {
            int a = c - (int) (b * b);

            long start = 0, end = a;
            while (start <= end) {
                long mid = start + (end - start) / 2;
                long midSq = mid * mid;
                if (a == midSq)
                    return true;
                else if (a > midSq) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return false;
    }

    static boolean judgeSquareSum_linearSearch(int c) {
        // Time: O(sqrt(c))
        // define search range
        long start = 0, end = (long) Math.sqrt(c) + 1;

        // linear search
        while (start <= end) {
            long sum = start * start + end * end;
            if (sum < c) {
                start++;
            } else if (sum > c) {
                end--;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] testcases = {5, 3, 4, 2, 1, 0, 1000000000};

        for (int testcase : testcases) {
            System.out.println("Input: " + testcase);
            System.out.println("Output: " + judgeSquareSum_mathsFermatTheorem(testcase));
            System.out.println("Output: " + judgeSquareSum_optimizedBruteForce(testcase));
            System.out.println("Output: " + judgeSquareSum_linearSearch(testcase));
            System.out.println("---");
        }
    }
}
