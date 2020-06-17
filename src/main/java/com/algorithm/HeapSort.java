package com.algorithm;

public class HeapSort {

    public static void main(String[] args) {

        int arr[] = {3,4,1,2,9,7,6,2};
        heapSort(arr);
        for (int i : arr) {
            System.out.print(i);
        }
    }


    public static void heapSort(int arr[]){

        //将无序数组初始化为大顶堆，建堆，从最后一个非叶子节点开始，而最后一个非叶子节点的下标为array.length/2-1
        for (int root=arr.length/2-1;root>=0;root--){
            adjustHeap(arr,arr.length,root);
        }

        int count = arr.length;//未排序的数量

        while (count>1){

            int temp = arr[count-1];//交换堆顶与未排序数组的最后一位
            arr[count-1] = arr[0];
            arr[0] = temp;

            count--;//完成一次排序，将最大的数排到数组最后，未排序的数量减一
            adjustHeap(arr,count,0);//调整过程自上而下，参数root=0
        }

    }


    public static void adjustHeap(int arr[],int count,int root){

        int maxChildIndex;

        while (root <= count / 2 - 1){//待调整子堆的根节点必须是非叶子节点

            if (root == count / 2 - 1 && count % 2 == 0){//如果当前根节点是最后一个非叶子节点，并且它没有右子节点（节点数为偶数）
                maxChildIndex = root * 2 + 1;
            }else {
               int leftChildIndex=root*2+1;
               int rightChildIndex=root*2+2;
               maxChildIndex=arr[leftChildIndex]>arr[rightChildIndex]?leftChildIndex:rightChildIndex;
            }

            if (arr[root]<arr[maxChildIndex]){//交换根节点与最大叶子节点
                int temp = arr[root];
                arr[root]=arr[maxChildIndex];
                arr[maxChildIndex]=temp;

                root=maxChildIndex;//将根节点指向最大子节点
            }else {
                return;
            }
        }
    }

}
