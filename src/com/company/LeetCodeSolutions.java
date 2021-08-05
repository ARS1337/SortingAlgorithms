package com.company;


import java.nio.charset.StandardCharsets;
import java.util.*;

public class LeetCodeSolutions {

    public static void run(){
        removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4});

    }

    public static int removeDuplicates(int[] nums) {
        int result = 0;

        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i] == nums[i+1]) nums[i] = 1000+i;
            else result++;
        }

        Arrays.stream(nums).forEach(s-> System.out.print(s+" "));
        System.out.println("   "+result);
        return result;
    }

    public static boolean isValidSudoku(char[][] board) {
        boolean result = true;
        int[] nums = new int[10];
        int startRow = 0;
        int startColoums = 0;

        for(int i = 0; i<9; i++){
            for(int j = 0; j<9; j++){
                if(board[i][j]==46) continue;
                else{
                    if(nums[board[i][j]-48]>0){
                        //System.out.println("first loop");
                        return false;
                    }
                    else nums[board[i][j]-48]++;
                }
            }
            Arrays.fill(nums,0);
        }
        Arrays.fill(nums,0);
        for(int i = 0; i<9; i++){
            for(int j = 0; j<9; j++){
                if(board[j][i]==46) continue;
                else{
                    if(nums[board[j][i]-48]>0){
                        //System.out.println("second loop"+j+" "+i+" "+nums[board[j][i]]);
                        return false;
                    }
                    else nums[board[j][i]-48]++;
                }
            }
            Arrays.fill(nums,0);
        }
        Arrays.fill(nums,0);

        while (startColoums<9&&startRow<9){
            for(int i = startRow; i<startRow+3; i++){
                for(int j = startColoums; j<startColoums+3; j++){
                    if(board[i][j]==46) continue;
                    else{
                        if(nums[board[i][j]-48]>0){
                            for(int i12 : nums) System.out.print(i12+" ");
                            //  System.out.println("last loop "+j+" "+i+" "+nums[board[j][i]]);
                            return false;
                        }
                        else nums[board[i][j]-48]++;
                    }
                }
            }
            Arrays.fill(nums,0);
            startRow+=3;
            if(startRow==9){
                if(startColoums == 9) break;
                startRow = 0;
                startColoums +=3;
            }
        }

        return result;

    }


    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String,List<String>> map = new HashMap<>();

        for(String s : strs){
            byte[] b = s.getBytes();
            quickSort(b,0,b.length-1);
            String key = new String(b);
            List<String> value = map.get(key);
            if(value == null){
                List<String> temp = new ArrayList<>();
                temp.add(s);
                map.put(key,temp);
            }
            else {
                value.add(s);
            }
        }
//        map.values().stream().forEach(s->{
//            s.stream().forEach(j-> System.out.print(j+" "));
//            System.out.println();
//        });
        map.values().stream().forEach(s->result.add(s));

        return result;
    }

    public static void quickSort(byte[] arr, int leftIndex, int rightIndex){ // O(n*log(n))
        if(leftIndex<rightIndex){                                           // exit condition : if the leftIndex >= rightIndex, i.e. array has only one or no elements
            int pivot = partition(arr,leftIndex,rightIndex);                // divide the array into two section, with pivot at its sorted position
            quickSort(arr,leftIndex,(byte) (pivot-1));                      // keep calling itself recursively util there is one or no element
            quickSort(arr,(byte)(pivot+1),rightIndex);                      // after which it returns, since all array is sorted, partition is only called if there at least two elements in array
        }                                                                   // the recursive quickSort call divides the array into arrays with left of pivot elements and the with right of pivot elements
    }                                                                       // and partition keeps getting called till there are less than 2 elements in the array, and sorting two elements is just comparing...

    private static int partition(byte[] arr, int startIndex, int endIndex){  // divides the array into two sections, elements to left of pivot are smaller than pivot and
        int pivot = endIndex;                                              // to the right of pivot are larger than pivot, pivot is chosen randomly as rightmost element
        int leftIndex = startIndex;                                                //1. elements smaller than pivot are placed at this position and then this position is incremented
        for(int i = startIndex; i < endIndex; i++){                           // loop runs form leftMost index till 2nd rightMostIndex ( index left of pivot)
            if(arr[i]<arr[pivot]){                                           // if the element is smaller than pivot, then it is swapped with the leftIndex(see 1.)
                Main.swap(arr,i, leftIndex);                                            // so all elements that were swapped with leftIndex are smaller than pivot
                leftIndex++;                                                      // leftIndex is increment after the swap to represent new position that the smaller than pivot elements
            }                                                                   // should be swapped with
        }                                                                  // since the loop runs only through non-pivot section of the array,
        Main.swap(arr,leftIndex, (byte) pivot);                                      // the pivot is swapped with the leftIndex as all elements smaller than pivot are placed at to the left of incremented leftIndex
        return leftIndex;                                                 // this is the new pivot position ( leftIndex ), all elements to the left of leftIndex are smaller than pivot and
    }

    public static boolean isValid(String s){
        boolean result = false;
        if(s.isEmpty()) return result;
        char[] strChar = s.toCharArray();
        char isOpen = 0;
        Stack<Character> stack = new Stack<Character>();
        stack.push(strChar[0]);

        for(int i = 1; i < strChar.length; i++){
            if(!stack.isEmpty()){
                isOpen = stack.peek();
            }
            char isClose = strChar[i];
            if(isPaired(isOpen,isClose)) stack.pop();
            else stack.push(isClose);
            isOpen = 0;
        }
        result = stack.isEmpty();
        return result;
    }

    private static boolean isPaired(char open, char close){
        return open == '(' && close == ')';
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
