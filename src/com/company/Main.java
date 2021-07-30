package com.company;

import java.lang.ref.SoftReference;

public class Main {

    public static void main(String[] args) {
	    int[] arr = new int[]{0,7,23,6,3,6,35,7,8,6};
        //System.out.println(binaryRecursiveSearch(new int[]{0,1,2,3,4,5,6,7,8,11,45,100},0,11,45));
        //System.out.println(binaryIterativeSearch(new int[]{0,1,2,3,4,5,6,7,8,11,45,100},0,11,45));
        //System.out.println(nLargestElement(arr,10));
        //System.out.println(largestElement(arr));
        //selectionSort(arr);
        //insertionSort(arr);
        //bubbleSort(arr);
        //optimizedBubbleSort(arr);
        //merge(arr,8,10,14);
        //mergeSort(arr,0,arr.length-1);
        //System.out.println(partition(arr,0,arr.length-1));
        //quickSort(arr,0,arr.length-1);
        //countingSort(arr);
        shellSort(arr);

        for(int i : arr) System.out.print(i+" ");
    }


    public static void radixSort(int[] arr){}          /// to implement

    public static void shellSort(int[] arr){                                  //
        int interval = arr.length/2;                                          // compare elements interval apart
        while(interval >= 1){                                                 // keeps comparing elements till interval is equal to 1
            for(int i = 0; (i + interval) < arr.length; i++ ){                // loop runs form i = 0 till (i + interval) is less than arr.length
                if(arr[i] > arr[i+interval]) swap(arr,i,i+interval);        // if greater then swap
            }                                                                 //
            interval = interval/2;                                            //  keep halving the interval until its 1
        }                                                                     //  once the interval is 1, the adjacent elements are compared
    }                                                                         //

    public static int getIthDigit(int number,int digit){
        return (number/(int)Math.pow(10,digit-1)) % 10;                  // get nth digit ; formula : (number / 10 ^ ( digit-1) ) % 10
    }

    public static void countingSort(int[] arr){                          // O(n+k) ; k is the size of the freq[] or the largest element of arr[] and n is the size of arr[]
        int[] freq = new int[largestElement(arr)+1];                     // makes an array of length( largest element of arr + 1) so to represent all elements by its indexes and frequencies
        int[] newArr = new int[arr.length];                              // a temp array to store all sorted elements
        int sum = 0;                                                     // used to store the sum while cumulatively adding frequencies of all elements
        for(int i : arr) freq[i]++;                                      // calculate the frequencies, element i has its frequency stored in ith position of freq[]
                                                                         // for element i in original array, increment the freq[i], now element i's frequency is stored at i'th position in freq[]
        for(int i = 0; i < freq.length; i++){
            freq[i] += sum;                                              // calculate the cumulative frequencies
            sum = freq[i];
        }

        for(int i = freq.length-1; i > 0 ; i--) swap(freq, i-1, i);    // elements are shifted right by one position and last one is discarded
        freq[0] = 0;                                                      // now an 'i' element in arr[] has its starting position at the freq[i]

        for (int i : arr){
            int startingIndex = freq[i];                                  // element i in array arr[] has its startingIndex stored at ith position in freq i.e. freq[i]
            newArr[startingIndex] = i;                                    // now we store the element i at the startingIndex of the newArray
            freq[i]++;                                                    // increment the starting index, in case we encounter the same number again
        }                                                                 // so it wont be stored at the same position

        for(int i =0 ; i < arr.length; i++) arr[i] = newArr[i];           // copy all elements from newArray[] to the original array arr[]
    }

    public static void quickSort(int[] arr, int leftIndex, int rightIndex){ // O(n*log(n))
        if(leftIndex<rightIndex){                                           // exit condition : if the leftIndex >= rightIndex, i.e. array has only one or no elements
            int pivot = partition(arr,leftIndex,rightIndex);                // divide the array into two section, with pivot at its sorted position
            quickSort(arr,leftIndex,pivot-1);                      // keep calling itself recursively util there is one or no element
            quickSort(arr,pivot+1,rightIndex);                      // after which it returns, since all array is sorted, partition is only called if there at least two elements in array
        }                                                                   // the recursive quickSort call divides the array into arrays with left of pivot elements and the with right of pivot elements
    }                                                                       // and partition keeps getting called till there are less than 2 elements in the array, and sorting two elements is just comparing...

    private static int partition(int[] arr, int startIndex, int endIndex){  // divides the array into two sections, elements to left of pivot are smaller than pivot and
        int pivot = endIndex;                                              // to the right of pivot are larger than pivot, pivot is chosen randomly as rightmost element
        int leftIndex = startIndex;                                                //1. elements smaller than pivot are placed at this position and then this position is incremented
        for(int i = startIndex; i < endIndex; i++){                           // loop runs form leftMost index till 2nd rightMostIndex ( index left of pivot)
            if(arr[i]<arr[pivot]){                                           // if the element is smaller than pivot, then it is swapped with the leftIndex(see 1.)
                swap(arr,i,leftIndex);                                            // so all elements that were swapped with leftIndex are smaller than pivot
                leftIndex++;                                                      // leftIndex is increment after the swap to represent new position that the smaller than pivot elements
            }                                                                   // should be swapped with
        }                                                                  // since the loop runs only through non-pivot section of the array,
        swap(arr,leftIndex,pivot);                                      // the pivot is swapped with the leftIndex as all elements smaller than pivot are placed at to the left of incremented leftIndex
        return leftIndex;                                                 // this is the new pivot position ( leftIndex ), all elements to the left of leftIndex are smaller than pivot and
    }                                                                      // pivot is itself at leftIndex position, hence element greater than or equal are right of pivot

    public static void mergeSort(int[] arr, int leftIndex, int rightIndex){        // O(n*log(n))
        if(leftIndex<rightIndex){                               // exit condition : if both indexes are same
            int mid = ( leftIndex + rightIndex )/2;             // keeps calling itself recursively until there is only 1 element in subSection of array
            mergeSort(arr, leftIndex, mid);                     // i.e. leftIndex == rightIndex, after which it returns and merge is called subsequently
            mergeSort(arr,mid+1, rightIndex);           // merge sorts already sorted subsections, and since only a single element can be considered sorted
            merge(arr, leftIndex, mid, rightIndex);             // it just returns and compares 2 subsection with single element each
        }                                                       // and just swaps them, now they act as sorted subsections with 2 element each
    }                                                           // and merge can be used for sorting already sorted arrays

    private static void merge(int[] arr, int leftIndex, int mid, int rightIndex){
        int leftEnd = mid;                                                        // convert/merges 2 sorted arrays into a single sorted array
        int rightEnd = rightIndex;                                                // arr[] is sorted from leftIndex to mid
        int i = leftIndex;                                                        // and also sorted from mid + 1 to right index
        int j = mid + 1;                                                          // both these sorted sections are converted into a single sorted section
        int[] temp = new int[rightIndex+1];                                       // leftIndex to rightIndex are now a continuous sorted section
        int tempIter = 0;
        while(i <= leftEnd && j <= rightEnd){                                     // loop runs till either of the sorted arrays are exhausted
            if(arr[i] < arr[j]){                                                  // compares elements and puts the smaller element in the temp array
                temp[tempIter] = arr[i];
                i++;                                                              // increment the index of the smaller element
            }
            else{
                temp[tempIter] = arr[j];
                j++;
            }
            tempIter++;                                                           // increment the index for the temp array
        }

        while (i<=leftEnd){                                                      // the unexhausted array is copied from its ith or jth current index till last index
            temp[tempIter] = arr[i];
            i++;
            tempIter++;
        }

        while (j<=rightEnd) {
            temp[tempIter] = arr[j];
            j++;
            tempIter++;
        }
        for(int k = 0, l=leftIndex; k <= (temp.length - 1) && l <= rightIndex; k++,l++) arr[l] = temp[k]; // copy elements from temp[](0,temp.length-1) to arr[](leftIndex,rightIndex)
    }

    public static void bubbleSort(int[] arr){           // O(n^2)
        for(int i = 0; i < arr.length; i++){            // loop runs from i = 0 till lastIndex
            for(int j = 0; j < (arr.length-i-1); j++){  // loop runs from j = 0 till lastIndex-i for every i, as the largest element is shifted to the (lastIndex-i) position
                if(arr[j]>arr[j+1]) {
                    swap(arr, j, j + 1);             // compare with adjacent element, if greater, then swap
                }
            }
            for(int k : arr) System.out.print(k+" ");
            System.out.println();
        }
    }

    public static void optimizedBubbleSort(int[] arr){           // O(n^2)
        boolean isSorted ;
        for(int i = 0; i < arr.length; i++){            // loop runs from i = 0 till lastIndex
            isSorted = true;                            // array is assumed to be sorted on every iteration
            for(int j = 0; j < (arr.length-i-1); j++){  // loop runs from j = 0 till lastIndex-i for every i, as the largest element is shifted to the (lastIndex-i) position
                if(arr[j]>arr[j+1]) {
                    swap(arr, j, j + 1);               // compare with adjacent element, if greater, then swap
                    isSorted = false;                   // this is set to false only when elements arent in order i.e. unsorted
                }
            }
            if(isSorted)  break;                        // if this was not set to false, then it means no preceding element was greater than
            for(int k : arr) System.out.print(k+" ");   // its succeding element i.e. if(arr[j]>arr[j+1] wasnt triggered, break the loop
            System.out.println();                       // as the array is sorted, no more iterations are needed
        }
    }

    public static void insertionSort(int[] arr){           // O(n^2) time complexity
        for(int i = 1; i < arr.length; i++){               // loop runs from i = 1 till i = lastIndex
            for(int j = i; j > 0; j--){                    // loop runs from j = i till j = 1
                if(arr[j-1]>arr[j]) swap(arr,j-1,j);    // compares (j-1)th element to the jth element and swaps if greater
            }                                              // where j runs from i to 1. i.e. Keeps shifts smaller elements to leftmost
//            for(int k : arr) System.out.print(k+" ");
//            System.out.println();
        }
    }

    public static void selectionSort(int[] arr){                 // O(n^2) time complexity
        for(int i = 0; i<arr.length; i++){                       // outer loop runs from i = 0 till last index
            for(int j = i; j< arr.length ; j++){                 // inner loop runs from j = i till last index
                if(arr[i]>arr[j]) swap(arr,i,j);                 // arr[i] is compared to range(j = i,lastIndex) : arr[j] and smallest element
            }                                                    // is shifted to the ith position
        }
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

    public static int largestElement(int[] arr){
        int result = 0;
        for(int i =0;(i+1) < arr.length;i++){                      // loop runs from index 0 till (i+1) is the last index i.e. till second last position
            if(arr[i]>arr[i+1]) swap(arr,i, i+1);               // every iteration compares the i element with i+1 element and swaps it with i+1 if larger
        }
        result = arr[arr.length-1];
        return result;
    }

    public static int nLargestElement(int[] arr, int n){             // find the nth largest element, the largest element is the 1st largest element and (arr.length)th largest element is the smallest
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

    private static boolean isArraySorted(int[] arr){
        boolean isSorted = true;
        for(int i = 0; i< (arr.length-1); i++){
            if(arr[i] > arr[i+1]){
                isSorted = false;
                break;
            }
        }
        return isSorted;
    }
    private static void swap(int[] arr, int i ,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
