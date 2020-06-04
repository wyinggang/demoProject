package com.algorithm;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

public class HS {

    public static void main(String[] args) {

        int arr[] = {5,2,6,1,3,7,9};

        hs(arr);

        for (int i : arr) {
            System.out.println(i);
        }

         new AnnotationConfigApplicationContext();

        System.out.println(System.currentTimeMillis()/1000-1587617031>=2*60*60);
        System.out.println(30*24*60*60);
        System.out.println(System.currentTimeMillis()/1000);
        System.out.println(System.currentTimeMillis()/1000-30*24*60*60);
        System.out.println(System.currentTimeMillis()/1000-2*60*60);
    }

    public static void hs(int arr[]) {

        for (int root = arr.length/2-1;root>=0;root--){
            adjustHeap(arr,arr.length,root);
        }

        int count=arr.length;
        while (count>1){
            int temp = arr[0];
            arr[0] = arr[count-1];
            arr[count-1] = temp;

            count--;
            adjustHeap(arr,count,0);
        }

    }

    public static void adjustHeap(int arr[],int count,int root){
        int maxChildIndex;
        while (root<=count/2-1){

            if (root==count/2-1&&count%2==0){
                maxChildIndex=root*2+1;
            }else {
                int leftChildIndex = root*2+1;
                int rightChildIndex = root*2+2;
                maxChildIndex=arr[leftChildIndex]>arr[rightChildIndex]?leftChildIndex:rightChildIndex;
            }

            if (arr[root]<arr[maxChildIndex]){
                int temp = arr[root];
                arr[root] = arr[maxChildIndex];
                arr[maxChildIndex] = temp;

                root=maxChildIndex;
            }else {
                return;
            }

        }
    }

}
