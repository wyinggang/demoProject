package com.algorithm;

public class QS {

    public static void main(String[] args) {

         int arr[] = {4,6,1,3,2,9};
         qs(arr,0,arr.length-1);
        for (int i : arr) {
            System.out.print(i);
        }
    }

    public static void qs(int arr[],int low,int high) {
        int i, j, temp;

        if (low>high){
            return;
        }

        i=low;
        j=high;
        temp=arr[low];

        while (i<j){

            while (temp<=arr[j]&&i<j){
                j--;
            }

            while (temp>=arr[i]&&i<j){
                i++;
            }

            if (i<j){
               int x = arr[i];
               int y = arr[j];

               arr[i] = y;
               arr[j] = x;
            }
        }

        arr[low] = arr[i];
        arr[i] = temp;

        qs(arr,low,i-1);
        qs(arr,i+1,high);
    }

}
