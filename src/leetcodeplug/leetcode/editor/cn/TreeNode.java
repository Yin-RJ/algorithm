package leetcodeplug.leetcode.editor.cn;

/**
 * @author yinrongjie
 * @version 1.0
 * @date 2023/1/31
 * @description
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
