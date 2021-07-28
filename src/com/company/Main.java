package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println(binaryRecursiveSearch(new int[]{0,1,2,3,4,5,6,7,8,11,45,100},0,11,45));
        System.out.println(binaryIterativeSearch(new int[]{0,1,2,3,4,5,6,7,8,11,45,100},0,11,45));
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
        System.out.println(leftIndex+" "+" "+mid+" "+rightIndex);
        if(arr[mid] == num) return mid;                                          // exit condition : if the element was found
        else if(arr[mid] > num ) return binaryRecursiveSearch(arr,leftIndex,mid-1,num);
        else return binaryRecursiveSearch(arr,mid+1,rightIndex,num);
    }

    public static int binaryIterativeSearch(int[] arr, int leftIndex, int rightIndex, int num){
        int result = -1;
        while(leftIndex<=rightIndex){
            int mid = (rightIndex+leftIndex)/2;
            System.out.println(leftIndex+" "+" "+mid+" "+rightIndex);
            if(arr[mid]==num){
                result = mid;
                break;                                                          // loop breaks after the element is found and the result is returned
            }
            else if(arr[mid]<num) leftIndex = mid+1;
            else rightIndex = mid - 1;
        }
        return result;
    }
}
