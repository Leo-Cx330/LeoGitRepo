package com.leo.algorithm.leetcode;

/**
 * @Copyright zm
 * @FileName: Leo-cloud
 * @Author: lihao
 * @Date: 2020/7/3 4:47 PM
 * @Description:
 * @author: lihao
 */
public class Question21 {


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
     * <p>
     *  
     * <p>
     * 示例：
     * <p>
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {

        if (l1 == null ) {
            return l2;
        }
        if(l2==null){
            return  l1;
        }
        if(l1.val<l2.val){
             l1.next = merge(l1.next,l2);
             return l1;
        }else{
            l2.next=merge(l1,l2.next);
            return  l2;
        }

    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(5);
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        Question21 question = new Question21();
        ListNode listNode = question.mergeTwoLists(l1, l2);
       while (listNode!=null){
           System.out.println(listNode.val);
           listNode=listNode.next;
       }
    }
}
