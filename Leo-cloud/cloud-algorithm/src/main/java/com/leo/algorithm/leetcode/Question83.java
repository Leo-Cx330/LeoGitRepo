package com.leo.algorithm.leetcode;

import java.util.TreeSet;

/**
 * @Copyright zm
 * @FileName: Leo-cloud
 * @Author: lihao
 * @Date: 2020/11/3 3:48 下午
 * @Description:
 * @author: lihao
 */
public class Question83 {


    /**
     * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1->1->2
     * 输出: 1->2
     * 示例 2:
     * <p>
     * 输入: 1->1->2->3->3
     * 输出: 1->2->3
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        TreeSet<Integer> set=new TreeSet<>((o1, o2) -> o1-o2);
        while (head != null) {
            set.add(head.val);
            head = head.next;
        }
        ListNode parent = null;
        ListNode sub=null;
        for (Integer node : set) {
            ListNode newNode = new ListNode(node);
            if(parent==null){
                parent=newNode;
                sub=parent;
            }else {
               sub.next=newNode;
               sub=sub.next;
            }
        }
        return parent;
    }
    public static ListNode deleteDuplicates2(ListNode head) {
        ListNode  parent=head;
        while (parent!=null && parent.next!=null){
             if(parent.val==parent.next.val){
                 parent.next=parent.next.next;
             }else {
                 parent=parent.next;
             }
        }
        return  head;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(-3);
        ListNode listNode2 = new ListNode(-1);
        ListNode listNode3 = new ListNode(0);
        ListNode listNode4 = new ListNode(3);
        ListNode listNode5 = new ListNode(3);
        listNode4.next = listNode5;
        listNode3.next = listNode4;
        listNode2.next = listNode3;
        listNode.next = listNode2;
        deleteDuplicates2(listNode);

    }


}
