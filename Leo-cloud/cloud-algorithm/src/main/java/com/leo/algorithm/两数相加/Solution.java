package com.leo.algorithm.两数相加;

/**
 * Copyright xxxx
 * FileName: leo-cloud
 * Author:   lihao
 * Date:     2019/8/6 7:32 PM
 * Description:
 * author: leo
 */


public class Solution {

    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode node=new ListNode(0);
        ListNode node1=l1;
        ListNode node2=l2;
        int carry=0;
        while(node1!=null || node2!=null){

           int x=l1.val==0?0:l1.val;
           int y=l2.val==0?0:l2.val;
            int sum=x+y+carry;
            carry=sum/10;
            node.next=new ListNode(sum%10);
            node=node.next;
            if(node1!=null){
                node1=node1.next;
                node2=node2.next;
            }
        }
        if(carry>0){
            node.next=new ListNode(carry);
        }

        return node.next;
    }
}
