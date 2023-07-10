/* Leetcode 111: "Minimum Depth of Binary Tree"
Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Example 1:
          3
         / \
        9  20
           / \
          15  7
    Input: root = [3,9,20,null,null,15,7]
    Output: 2

Example 2:
      2
       \
        3
         \
          4
           \
            5
             \
              6
    Input: root = [2,null,3,null,4,null,5,null,6]
    Output: 5

Example 3:
    Input: root = []
    Output: 0
*/

import java.util.LinkedList;
import java.util.Queue;

class MinDepth {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println("BFS = " + minDepthBFS(root));
        System.out.println("DFS = " + minDepthDFS(root));
    }

    static int minDepthDFS(TreeNode root) {
        // Depth First Search
        if (root == null)
            return 0;
        int left = minDepthDFS(root.left);
        int right = minDepthDFS(root.right);

        if (left == 0 || right == 0)
            return left + right + 1;
        return Math.min(left, right) + 1;
    }

    static int minDepthBFS(TreeNode root) {
        if (root == null)
            return 0;
        Queue<TreeNode> breadth = new LinkedList<TreeNode>();
        breadth.add(root);
        breadth.add(null); // delimiter for each level

        int depth = 1;
        while (!breadth.isEmpty()) {
            // Breadth First Search
            TreeNode curr = breadth.remove();

            if (curr != null) {
                if (curr.left == null & curr.right == null)
                    break;
                else {
                    if (curr.left != null)
                        breadth.add(curr.left);
                    if (curr.right != null)
                        breadth.add(curr.right);
                }
            } else if (!breadth.isEmpty()) {
                // if there are more TreeNodes
                breadth.add(null); // add the level delimiter
                depth++;
            }
        }

        return depth;
    }
}