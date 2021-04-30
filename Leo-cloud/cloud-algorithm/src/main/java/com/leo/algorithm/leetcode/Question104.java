package com.leo.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Copyright zm
 * @FileName: Leo-cloud
 * @Author: lihao
 * @Date: 2020/10/31 5:09 下午
 * @Description: 剑指 Offer 55 - I. 二叉树的深度
 * @author: lihao
 */
public class Question104 {


    public int maxDepth(TreeNode root) {

        return levelSort(root);
    }


    private int levelSort(TreeNode treeNode) {
        Queue<TreeNode> queue = new LinkedList();
        if(treeNode==null){
            return 0;
        }
        int levelSize=1;
        int depth=0;
        queue.offer(treeNode);
        while (!queue.isEmpty()){
            TreeNode poll = queue.poll();
            levelSize--;
            if(poll.left!=null){
                queue.offer(poll.left);
            }
            if(poll.right!=null){
                queue.offer(poll.right);
            }
            if(levelSize==0){
                levelSize=queue.size();
                depth++;
            }
        }
        return depth;

    }

}
