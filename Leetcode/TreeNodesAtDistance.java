/* Leetcode 863: "All nodes distance K in Binary Tree"
Given the root of a binary tree, the value of a target node target, and an integer k, return an array
of the values of all nodes that have a distance k from the target node.
You can return the answer in any order.

Example 1:
    Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
    Output: [7,4,1]
    Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
Example 2:
    Input: root = [1], target = 1, k = 3
    Output: []
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class TreeNodesAtDistance {
    Map<TreeNode, TreeNode> childParent;
    List<Integer> nodes;
    Set<TreeNode> visited;

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
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        System.out.println(new TreeNodesAtDistance().distanceK(root, root.left, 2));
    }

    List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        this.childParent = new HashMap<>();
        TreeNode t = findTarget(root, target);

        this.nodes = new ArrayList<>();
        this.visited = new HashSet<TreeNode>();
        findNodesAtDistance(t, k);

        return this.nodes;
    }

    TreeNode findTarget(TreeNode root, TreeNode target) {
        if (root == null)
            return null;

        if (root.equals(target))
            return root;

        if (root.left != null) {
            this.childParent.put(root.left, root);
            TreeNode t = findTarget(root.left, target);
            if (t != null)
                return t;

            // if t == null we didn't find the target
            // hence this Child Parent mapping is useless
            this.childParent.remove(root.left);
        }

        if (root.right != null) {
            this.childParent.put(root.right, root);
            TreeNode t = findTarget(root.right, target);
            if (t != null)
                return t;

            // if t == null we didn't find the target
            // hence this Child Parent mapping is useless
            this.childParent.remove(root.right);
        }

        // didn't find the node in either left or right subtree of this TreeNode
        return null;
    }

    void findNodesAtDistance(TreeNode root, int k) {
        if (root == null)
            return;
        if (k == 0)
            this.nodes.add(root.val);
        else {
            this.visited.add(root);
            k--;

            if (root.left != null && !this.visited.contains(root.left))
                findNodesAtDistance(root.left, k);
            if (root.right != null && !this.visited.contains(root.right))
                findNodesAtDistance(root.right, k);
            findNodesAtDistance(this.childParent.get(root), k);
        }
    }
}