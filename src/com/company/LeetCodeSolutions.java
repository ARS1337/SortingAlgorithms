package com.company;

import org.w3c.dom.NodeList;

import java.util.HashMap;

public class LeetCodeSolutions {

    public static void run(){
        ListNode node1 = new ListNode(9);
        node1.next = new ListNode(9);

        ListNode node2 = new ListNode(1);
        //node2.next = new ListNode(1);

        ListNode nodeRes = addTwoNumbers(node1,node2);
        while (nodeRes != null){
            System.out.println(nodeRes.val);
            nodeRes = nodeRes.next;
        }

    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2){
        ListNode result = new ListNode();
        ListNode temp = result;

        ListNode tempL1 = l1;
        ListNode tempL2 = l2;

        int carry = 0;
        int num = 0;

        while (l1 != null && l2 != null){
            num = carry + l1.val + l2.val;
            carry = num/10;
            num = num%10;
            temp.next = new ListNode(num);
            System.out.println("l1.val "+l1.val+" l2.val "+l2.val+" num "+num+" carry "+carry);
            l1 = l1.next;
            l2 = l2.next;
            temp = temp.next;
        }

        while (l1 != null ){
            num = carry + l1.val;
            carry = num/10;
            num = num%10;
            temp.next = new ListNode(num);
            l1 = l1.next;
            temp = temp.next;
        }

        while (l2 != null ){
            num = carry + l2.val;
            carry = num/10;
            num = num%10;
            temp.next = new ListNode(num);
            l2 = l2.next;
            temp = temp.next;
        }

        if(carry != 0) temp.next = new ListNode(carry);

        return result.next;
    }


    public static int roman2Integer(String s){
        int result = 0;
        char[] roman = s.toCharArray();
        int digit = 0;
        int prevDigit = 0;

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('D',500);
        map.put('C',100);
        map.put('M',1000);

        for(char i : roman){
            digit = map.get(i);
            if(prevDigit < digit) digit = digit - 2*prevDigit;
            prevDigit = digit;
            result += digit;
        }
        return result;
    }

    public static boolean palindromeInteger(int num){
        int leftDigit  = 0;
        int rightDigit = 0;
        int order = 0;                           // if the number is 284 then order is 2 i.e. ( 2.84 * 10 ^ 2 ) the order of 10 when number is expressed as less than 10
        int temp = num;                          // temporary store the num

        while(temp >= 10){                        //
            temp /= 10;                          // keep dividing the num until its less than 10
            order++;                             // and increment it until num < 10 i.e the order of that number
        }
        temp = num;

        for(int i = 0,j = order; i < j; i++,j--){
            leftDigit = ( temp / (int) Math.pow(10,j) ) % 10;
            rightDigit = ( temp / (int) Math.pow(10,i) ) % 10;
            System.out.println(leftDigit + " "+ rightDigit);
            if(leftDigit != rightDigit) return false;
        }
        return num >= 0 && true;
    }


    //Definition for singly-linked list.
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


}
