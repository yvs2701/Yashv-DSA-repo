/* Leetcode 530: "Minimum Absolute Difference in BST" (same as Leetcode 783: https://leetcode.com/problems/minimum-distance-between-bst-nodes/)
Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.
Input: root = [4,2,6,1,3]
        4
       / \
      2   6
     / \
    1   3
Output: 1 (2 - 1 or 3 - 2)

Input: root = [1,0,48,null,null,12,49]
        1
       / \
      0   48
         /  \
        12   49
Output: 1 (1 - 0)

Input: [236,104,701,null,227,null,911]
        236
       /   \
     104    701
       \     \
        227   911
Output: 9 (236 - 227)
*/

import java.util.Scanner;

class MinDiffBST {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        TreeNode root = new TreeNode(sc.nextInt());
        TreeNode node = root;

        for (int i = 1; i < n; i++) {
            int val = sc.nextInt(); // give input in level order (exclude the "NULL" values)
            node = root;
            while (true) {
                if (val < node.val) {
                    if (node.left == null) {
                        node.left = new TreeNode(val);
                        break;
                    } else
                        node = node.left;
                } else {
                    if (node.right == null) {
                        node.right = new TreeNode(val);
                        break;
                    } else
                        node = node.right;
                }
            }
        }
        sc.close();

        System.out.println(getMinimumDifference(root));
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static TreeNode prev = null;
    private static int min = Integer.MAX_VALUE;

    private static int getMinimumDifference(TreeNode root) {
        getMinDiff(root);
        return min;
    }

    private static void getMinDiff(TreeNode node) {
        if (node == null)
            return;

        /* Traversing this way helps us to find the difference of
        max element on the left side with the parent node and
        min element on the right of the parent node. */
        getMinDiff(node.left);

        if (prev != null) {
            int diff = Math.abs(node.val - prev.val);
            if (diff < min)
                min = diff;
        }

        prev = node;

        getMinDiff(node.right);

        // /* Traversing this way is wrong: */
        // if (prev != null) {
        //     int diff = Math.abs(node.val - prev.val);
        //     if (diff < min)
        //         min = diff;
        // }

        // prev = node;
        // getMinDiff(node.left);
        // prev = node;
        // getMinDiff(node.right);
    }
}
