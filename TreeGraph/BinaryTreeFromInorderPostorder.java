/**
 * <h3><a href="https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/">
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 * </a></h3>
 * <p>
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree
 * and postorder is the postorder traversal of the same tree, construct and return the binary tree.
 * </p>
 * <p>
 * Example 1: <br>
 * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3] <br>
 * Output: [3,9,20,null,null,15,7] <br>
 * Example 2: <br>
 * Input: inorder = [-1], postorder = [-1] <br>
 * Output: [-1]
 * </p>
 * <p>
 * Constraints: <br>
 * 1 <= inorder.length <= 3000 <br>
 * postorder.length == inorder.length <br>
 * -3000 <= inorder[i], postorder[i] <= 3000 <br>
 * inorder and postorder consist of unique values. <br>
 * Each value of postorder also appears in inorder. <br>
 * inorder is guaranteed to be the inorder traversal of the tree. <br>
 * postorder is guaranteed to be the postorder traversal of the tree.
 * </p>
 */

import java.util.*;

class BinaryTreeFromInorderPostorder {

    static final Map<Integer, Integer> inorderCache = new HashMap<>();
    static final Map<Integer, Integer> postorderCache = new HashMap<>();

    int[] inorder;
    int[] postorder;

    /**
     * Algorithm:
     * find the root node (last index in postorder[])
     * determine left and right subtrees using inorder[]
     * then find root nodes of the subtree using postorder[], and repeat.
     */
    TreeNode buildTree(int[] inorder, int[] postorder) {
        // build cache
        for (int i = 0; i < inorder.length; i++) {
            inorderCache.put(inorder[i], i);
        }
        for (int i = 0; i < postorder.length; i++) {
            postorderCache.put(postorder[i], i);
        }

        // raise the scope of frequently used arrays
        this.inorder = inorder;
        this.postorder = postorder;

        // create tree
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        int idx_in = inorderCache.get(root.val);

        if (idx_in > 0) {
            root.left = buildSubtree(0, idx_in - 1);
        }
        if (idx_in < inorder.length - 1) {
            root.right = buildSubtree(idx_in + 1, inorder.length - 1);
        }
        return root;
    }

    private TreeNode buildSubtree(int start_inorder, int end_inorder) {
        int rightMostIdx = postorderCache.get(inorder[end_inorder]);
        for (int i = start_inorder; i < end_inorder; i++) {
            int postorderIdx = postorderCache.get(inorder[i]);
            if (postorderIdx > rightMostIdx) {
                rightMostIdx = postorderIdx;
            }
        }

        TreeNode root = new TreeNode(postorder[rightMostIdx]);
        int root_inorder = inorderCache.get(root.val);

        if (root_inorder > start_inorder) {
            root.left = buildSubtree(start_inorder, root_inorder - 1);
        }
        if (root_inorder < end_inorder) {
            root.right = buildSubtree(root_inorder + 1, end_inorder);
        }

        return root;
    }

    public static void main(String[] args) {
        int[][][] testcases = new int[][][]{
                {{9, 3, 15, 20, 7}, {9, 15, 7, 20, 3}},
                {{4, 2, 5, 1, 3, 6}, {4, 5, 2, 6, 3, 1}},
                {{-1}, {-1}}
        };

        BinaryTreeFromInorderPostorder obj = new BinaryTreeFromInorderPostorder();
        for (int[][] testcase : testcases) {
            TreeNode root = obj.buildTree(testcase[0], testcase[1]);

            // print tree levels
            System.out.println("inorder: " + Arrays.toString(testcase[0]));
            System.out.println("postorder: " + Arrays.toString(testcase[1]));
            System.out.println("Constructed tree: ");
            List<List<Integer>> levels = root.levelOrder();
            for (List<Integer> level : levels) {
                for (Integer i : level) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        List<List<Integer>> levelOrder() {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(this);

            List<List<Integer>> levels = new LinkedList<>();
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
}
