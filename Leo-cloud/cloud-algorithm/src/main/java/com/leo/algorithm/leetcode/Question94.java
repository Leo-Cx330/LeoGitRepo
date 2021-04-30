package com.leo.algorithm.leetcode;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Question94 {


  
    static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }

    /**
     * 二叉树中序遍历 递归
     */
    List<Integer>ret=new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
           if(root!=null){
               inorderTraversal(root.left);
               ret.add(root.val);
               System.out.println(root.val);
               inorderTraversal(root.right);
           }
           return  ret;
    }

    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stk = new Stack<>();
        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            root = stk.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;

    }


    public static void main(String[] args) {
        Question94 question94=new Question94();
        TreeNode treeNode=new TreeNode(1,null,new TreeNode(2,new TreeNode(3,null,null),null));
        question94.inorderTraversal(treeNode);
    }
}
