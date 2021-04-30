package com.leo.algorithm.leetcode;

/**
 * @Copyright zm
 * @FileName: Leo-cloud
 * @Author: lihao
 * @Date: 2020/6/24 2:47 PM
 * @Description:
 * @author: lihao
 */
public class Question226 {

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    /**
     * 输入：
     *
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     * 输出：
     *
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/invert-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root 递归
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if(root==null) {
            return root;
        }
         TreeNode temp=root.left;
         root.left=root.right;
         root.right=temp;
         invertTree(root.left);
         invertTree(root.right);
         return root;
    }



}
