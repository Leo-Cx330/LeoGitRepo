package com.leo.algorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Copyright zm
 * @FileName: Leo-cloud
 * @Author: lihao
 * @Date: 2020/10/30 10:31 下午
 * @Description: 层序遍历二叉树
 * @author: lihao
 */
public class Question102 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>>list=new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return list;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer>innerList=new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                innerList.add(node.val);
                if (node.left != null) {
                queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
            list.add(innerList);
        }
        return list;
    }


}
