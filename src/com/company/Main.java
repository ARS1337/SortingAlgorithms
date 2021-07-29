package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //System.out.println(binaryRecursiveSearch(new int[]{0,1,2,3,4,5,6,7,8,11,45,100},0,11,45));
        //System.out.println(binaryIterativeSearch(new int[]{0,1,2,3,4,5,6,7,8,11,45,100},0,11,45));
        //System.out.println(nLargestElement(new int[]{54,75,42,6,343,64,432,566,62362,5},10));
        System.out.println(largestElement(new int[]{54,75,42,6,343,64,432,566,62362,5}));
    }

    public static int linearSearch(int[] a,int fintInt) {
        int result = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == fintInt) {
                result = i;
                break;
            }
        }
        return result;
    }

    public static int binaryRecursiveSearch(int[] arr, int leftIndex, int rightIndex, int num){
        if(leftIndex>rightIndex) return -1;                                      // exit condition : if the leftIndex is greater than right i.e. element wasnt found
        int mid = (leftIndex + rightIndex)/2;
        //System.out.println(leftIndex+" "+" "+mid+" "+rightIndex);
        if(arr[mid] == num) return mid;                                          // exit condition : if the element was found
        else if(arr[mid] > num ) return binaryRecursiveSearch(arr,leftIndex,mid-1,num);
        else return binaryRecursiveSearch(arr,mid+1,rightIndex,num);
    }

    public static int binaryIterativeSearch(int[] arr, int leftIndex, int rightIndex, int num){
        int result = -1;
        while(leftIndex<=rightIndex){
            int mid = (rightIndex+leftIndex)/2;
            //System.out.println(leftIndex+" "+" "+mid+" "+rightIndex);
            if(arr[mid]==num){
                result = mid;
                break;                                                          // loop breaks after the element is found and the result is returned
            }
            else if(arr[mid]<num) leftIndex = mid+1;
            else rightIndex = mid - 1;
        }
        return result;
    }

    public static int largestElement(int arr[]){
        int result = 0;
        for(int i =0;(i+1) < arr.length;i++){                      // loop runs from index 0 till (i+1) is the last index i.e. till second last position
            if(arr[i]>arr[i+1]) swap(arr,i, i+1);               // every iteration compares the i element with i+1 element and swaps it with i+1 if larger
        }
        result = arr[arr.length-1];
        return result;
    }

    public static int nLargestElement(int[] arr, int n){
        int result = 0;                                              // n is the nth largest element
        for(int i = arr.length-1; i >= (arr.length-n); i--){         // loop runs from arr.length-1 i.e lastIndex till n from last i.e index = arr.length - n
            for(int j =0;(j+1)<=i;j++){                              // loop runs from first index i.e. 0 till nth element from last and keeps
                if(arr[j]>arr[j+1]) swap(arr, j, j+1);            // shifting the nthLargest element to the n position from last i.e. till i
            }                                                       // which is the last index in first iteration of outer loop
        }
        //for(int i : arr) System.out.println(i);
        result = arr[(arr.length-n)];
        return result;
    }

    private static void swap(int[] arr, int i ,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
