package com.company;

import org.w3c.dom.NodeList;

public class LeetCodeSolutions {

    public static void run(){


    }
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode temp = result;

        if(l1.val < l2.val){
            temp.val = l1.val;
            l1 = l1.next;
        }
        else{
            temp.val = l2.val;
            l2 = l2.next;
        }

        while (l1 != null && l2 != null){
            if(l1.val < l2.val){
                temp.next = new ListNode(l1.val);
                l1 = l1.next;
            }else {
                temp.next = new ListNode(l2.val);
                l2 = l2.next;
            }
        }

        while (l1 != null){
            temp.next = new ListNode(l1.val);
            l1 = l1.next;
        }
        while (l2 != null){
            temp.next = new ListNode(l2.val);
            l2 = l2.next;
        }
        return result;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
}
