import java.util.ArrayList;
import java.util.List;

/**
 * <h3><a href="https://leetcode.com/problems/beautiful-array/description/">
 * LeetCode 932: Beautiful Array
 * </a></h3>
 * <p>
 * An array nums of length n is beautiful if:
 * <ul>
 * <li> nums is a permutation of the integers in the range [1, n]. </li>
 * <li> For every 0 <= i < j < n, there is no index k with i < k < j where
 *   2 * nums[k] == nums[i] + nums[j]. </li>
 * </ul>
 * </p>
 * <p>
 * Given the integer n, return any beautiful array nums of length n. There will be at least one valid answer for the given n. </br>
 * Example 1: <br/>
 * Input: n = 4 <br/>
 * Output: [2,1,4,3] <br/>
 * Example 2: <br/>
 * Input: n = 5 <br/>
 * Output: [3,1,2,5,4]
 * </p>
 * <p>
 * Constraints: </br>
 * 1 <= n <= 1000
 * </p>
 */
public class BeautifulArray {
    /**
     * <p>
     * It is given that for a sequence {a, ... b, ... c}
     * 2b != a + c . <br/>
     * We know that 2b is an even number (since it's divisible by 2) <br/>
     * If we ensure a + c is an odd number then there is no possibility of
     * 2b = a + c .
     * </p>
     * <p>
     * For any given whole number:
     * <ul>
     * <li>even + even = even</li>
     * <li>odd + odd = even</li>
     * <li>even + odd = odd</li>
     * </ul>
     * Therefore, if we ensure on one side of the b there are all even numbers
     * and other side has even numbers then array will be beautiful. <br/>
     * Such an array can be represented as: {2b - 1, ... b, ... 2b}
     * </p>
     */
    public static int[] beautifulArray(int n) {
        List<Integer> beautifulArr = new ArrayList<>();
        beautifulArr.add(1);
        while (beautifulArr.size() < n) {
            beautifulArr = getNextBeautifulArray(n, beautifulArr);
        }

        int[] result = new int[beautifulArr.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = beautifulArr.get(i);
        }
        return result;
    }

    /**
     * Generates a `beautiful array` of the size larger `beautifulArr.size() + 1`
     */
    private static List<Integer> getNextBeautifulArray(int n, List<Integer> beautifulArr) {
        List<Integer> temp = new ArrayList<>();
        // odd numbers on the left
        for (Integer i : beautifulArr) {
            // numbers must be in the range: [1...n]
            if (2 * i - 1 <= n) {
                temp.add(2 * i - 1);
            }
        }
        // even numbers on the right
        for (Integer i : beautifulArr) {
            // numbers must be in the range: [1...n]
            if (2 * i <= n) {
                temp.add(2 * i);
            }
        }
        return temp;
    }

    public static void main(String[] args) {
        int[] testcases = {1, 2, 3, 4, 5, 10};
        for (int n : testcases) {
            int[] beautifulArr = beautifulArray(n);
            System.out.println("Beautiful Array for n = " + n + ":");
            for (int i : beautifulArr) {
                System.out.print(i + " ");
            }
            System.out.println("\n---");
        }
    }
}
