/* Leetcode 96: "Unique Binary Search Trees"
Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.
Example:
    Input: n = 3
    Output: 5
    Explanation: [1, 2, 3], [1, 3, 2], [2, [1, 3]], [3, 1, 2], [3, 2, 1] are the possible trees (level order representation)

Leetcode 95: "Unique Binary Search Trees II"
Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
Example:
    Input: n = 3
    Output: [[1, 2, 3], [1, 3, 2], [2, [1, 3]], [3, 1, 2], [3, 2, 1]]
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class NumberOfBST {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        List<List<Integer>> levelOrder() {
            Queue<TreeNode> queue = new LinkedList<TreeNode>();
            queue.add(this);

            List<List<Integer>> levels = new LinkedList<List<Integer>>();
            while (!queue.isEmpty()) {
                int n = queue.size();
                List<Integer> level = new LinkedList<>();

                while (n-- > 0) {
                    TreeNode temp = queue.poll();
                    level.add(temp.val);

                    if (temp.left != null)
                        queue.add(temp.left);

                    if (temp.right != null)
                        queue.add(temp.right);
                }
                levels.add(level);
            }
            return levels;
        }
    }

    public static void main(String[] args) {
        int totalNodes = 3; // total number of nodes
        NumberOfBST obj = new NumberOfBST();

        System.out.println(obj.numOfBST(totalNodes));

        List<TreeNode> trees = obj.generateTrees(totalNodes);

        for (TreeNode tree : trees) {
            List<List<Integer>> levels = tree.levelOrder();
            System.out.println(levels);
        }
    }

    long numOfBST(int n) {
        if (n == 1 || n == 0)
            return 1;

        // Binary search trees = (Left BST) * (Right BST) * (Ways to choose the root)
        // for a given root (n is total number of nodes, i => 1, 2, ..., i, ... n):
        // f(n) = f(i - 1) * f(n - i)
        // if any node can become a root:
        // f(n) = f(i - 1) * f(n - i)
        long sum = 0;

        for (int i = 1; i <= n; ++i) {
            sum += numOfBST(i - 1) * numOfBST(n - i);
        }

        return sum;
    }

    List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }

        return f(1, n);
    }

    List<TreeNode> f(int lo, int hi) {
        List<TreeNode> result = new ArrayList<>();

        // base case
        if (lo > hi) {
            result.add(null);
            return result;
        }

        // subproblem to reach bottom
        for (int i = lo; i <= hi; i++) {
            List<TreeNode> left = f(lo, i - 1);
            List<TreeNode> right = f(i + 1, hi);
            // reconstruct tree from bottom to up
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    result.add(root);
                }
            }
        }

        // return list of root to last layer
        return result;
    }
}