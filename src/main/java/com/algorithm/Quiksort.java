package com.algorithm;

public class Quiksort {

    public static void main(String[] args) {

        int arr[] = {1,23,4445,656,76,5,743,78,99,3,546};

        quikSorted(arr,0,arr.length-1);

        for (int i : arr) {
            System.out.println(i);
        }

    }

    public static void quikSorted(int arr[],int low,int high){
        int i,j,temp;

        if (low>high){//在
            return;
        }

        i=low;
        j=high;
        temp=arr[low];//基准

        while (i<j){//i，j两个哨兵没有相遇

            while (arr[j]>=temp&&i<j){//从右向左找到小于基准的数
                j--;
            }

            while (arr[i]<=temp&&i<j){//从左向右找到大于基准的数
                i++;
            }

            if (i<j){//在找到两个数后，两个哨兵未相遇时，交换对应位置值
                int x= arr[i];
                int y=arr[j];

                arr[i]=y;
                arr[j]=x;
            }
        }

        //当两个哨兵相遇交换相遇时位置与基准
        arr[low]=arr[i];
        arr[i]=temp;

        quikSorted(arr,low,i-1);//递归左边数组
        quikSorted(arr,i+1,high);//递归右边数组

    }

}
