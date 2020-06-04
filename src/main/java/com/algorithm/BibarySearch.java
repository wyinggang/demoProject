package com.algorithm;

public class BibarySearch {


    public static void main(String[] args) {
         int arr[] = {1,2,3,4,5,6,7};
        int index = binarySearch(arr, 4, 0, arr.length - 1);
        System.out.println(index);
    }

    public static int binarySearch(int arr[],int key,int low,int high){
        if (key<arr[low]||key>arr[high]||low>high)
        return -1;

        int mid =(low+high)/2;

        if (key<arr[mid]){
            return binarySearch(arr,key,low,mid-1);
        }else if (key>arr[mid]){
            return binarySearch(arr,key,mid+1,high);
        }else {
            return mid;
        }
    }

}
