package com.leo.algorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @Copyright zm
 * @FileName: Leo-cloud
 * @Author: lihao
 * @Date: 2020/11/8 8:25 下午
 * @Description:
 * @author: lihao
 */
public class Question144 {


    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        while (node != null) {
            if (node.right != null) {
                stack.push(node.right);
            }
            list.add(node.val);
            if (node.left == null && !stack.isEmpty()) {
                node = stack.pop();
            } else {
                node = node.left;
            }
        }
        return list;

    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            if(node != null){
                stack.push(node);
                node=node.left;
            }else if (stack.isEmpty()) {
                return list;
            }else {
                node=stack.pop();
                list.add(node.val);
                node=node.right;
            }
        }

    }


}
