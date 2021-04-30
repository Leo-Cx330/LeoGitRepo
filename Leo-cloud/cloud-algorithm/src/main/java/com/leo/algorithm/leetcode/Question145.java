package com.leo.algorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @Copyright zm
 * @FileName: Leo-cloud
 * @Author: lihao
 * @Date: 2020/10/31 9:55 上午
 * @Description: 后序遍历二叉树
 * @author: lihao
 */
public class Question145 {


    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        return postorder(root, list);
    }

    /**
     * 递归实现
     *
     * @param treeNode
     * @param list
     * @return
     */
    private List<Integer> postorder(TreeNode treeNode, List<Integer> list) {
        if (treeNode == null) {
            return list;
        }
        postorder(treeNode.left, list);
        postorder(treeNode.right, list);
        list.add(treeNode.val);
        return list;
    }

    /**
     * 迭代实现
     *
     * @param root
     * @return
     */
    private List<Integer> postorder1(TreeNode root) {
        LinkedList<Integer> list=new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null|| !stack.isEmpty()) {
            if(root!=null){
                stack.push(root);
                list.addFirst(root.val);
                root=root.right;
            }else{
                TreeNode pop = stack.pop();
                root=pop.left;
            }

        }
        return list;
    }
}
