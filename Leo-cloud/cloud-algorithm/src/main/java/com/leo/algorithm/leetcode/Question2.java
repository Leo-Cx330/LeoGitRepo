package com.leo.algorithm.leetcode;

import lombok.val;

/**
 * Copyright xxxx
 * FileName: leo-cloud
 * Author:   lihao
 * Date:     2019/8/6 7:32 PM
 * Description: 两数相加
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * author: leo
 */


public class Question2 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {


        ListNode node1 = l1;
        ListNode node2 = l2;
        ListNode node = new ListNode(0);
        ListNode root = node;
        int carry = 0;
        while (node1 != null || node2 != null || carry!=0) {
            int v1 = node1.val;
            int v2 = node2.val;
            int sum = (v1 + v2) + carry;
            int newVal = sum % 10;
            carry=sum/10;
            ListNode nextNode = new ListNode(newVal);
            root.next =nextNode;
            root=nextNode;
            node1=node1.next;
            node2=node2.next;

        }
        return root;
    }

    public static void main(String[] args) {
        Question2 question2 = new Question2();
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        node1.next = node2;

        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(6);
        ListNode node6 = new ListNode(4);
        node5.next = node6;
        node4.next = node5;

        ListNode node = question2.addTwoNumbers(node1, node4);
        System.out.println();
    }
}
