package com.test;

public class QucikSort {
    public static void main(String[] args) {
        int arr[] = {13, 19, 9, 5, 12, 8, 11, 7, 4, 21, 2, 6, 11};
        System.out.println("Before sorting....");
        printElements(arr);
        quickSort(arr, 0, arr.length-1);
        System.out.println("After sorting....");
        printElements(arr);
    }

    private static void quickSort(int[] arr, int p, int r) {
        if (p < r) {
            int q = partition(arr, p, r);
            quickSort(arr, p, q-1);
            quickSort(arr, q+1, r);
        }
    }

    private static void printElements(int[] arr) {
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    private static int partition(int[] arr, int p, int r) {
        int i = p-1;
        int pivot = arr[r];
        for (int j = p; j <= r-1; j++) {
            if (arr[j] <= pivot) {
                i = i+1;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[r];
        arr[r] = arr[i+1];
        arr[i+1] = temp;
        return i+1;
    }
}
