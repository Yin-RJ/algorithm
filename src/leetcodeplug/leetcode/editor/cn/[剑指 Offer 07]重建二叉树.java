package leetcodeplug.leetcode.editor.cn;

//输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。 
//
// 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。 
//
// 
//
// 示例 1: 
// 
// 
//Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//Output: [3,9,20,null,null,15,7]
// 
//
// 示例 2: 
//
// 
//Input: preorder = [-1], inorder = [-1]
//Output: [-1]
// 
//
// 
//
// 限制： 
//
// 0 <= 节点个数 <= 5000 
//
// 
//
// 注意：本题与主站 105 题重复：https://leetcode-cn.com/problems/construct-binary-tree-from-
//preorder-and-inorder-traversal/ 
//
// Related Topics 树 数组 哈希表 分治 二叉树 👍 969 👎 0


//leetcode submit region begin(Prohibit modification and deletion)


import java.util.HashMap;
import java.util.Map;

/**
 * Definition for a binary tree node.
 * public class leetcodeplug.leetcode.editor.cn.TreeNode {
 *     int val;
 *     leetcodeplug.leetcode.editor.cn.TreeNode left;
 *     leetcodeplug.leetcode.editor.cn.TreeNode right;
 *     leetcodeplug.leetcode.editor.cn.TreeNode(int x) { val = x; }
 * }
 */
class SolutionOffer07 {
    Map<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; ++i) {
            map.put(inorder[i], i);
        }
        return rebuild(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }


    private TreeNode rebuild(int[] preorder, int[] inorder, int pl, int pr, int il, int ir) {
        if (pl > pr) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[pl]);
        int k = map.get(preorder[pl]);

        root.left = rebuild(preorder, inorder, pl + 1, k - 1 - il + pl + 1, il, k - 1);
        root.right = rebuild(preorder, inorder, k - 1 - il + pl + 1 + 1, pr, k + 1, ir);

        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
