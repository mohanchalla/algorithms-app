package com.test;

public class MergeSort1 {

    public static void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int p, int r) {
        if (arr == null)
            return;
        if (p < r) {
            // Middle index of an array
            int q = (int) Math.floor((p + r) / 2);
            mergeSort(arr, p, q);
            mergeSort(arr, q + 1, r);
            merge(arr, p, q, r);
        }
    }

    private static void merge(int[] arr, int p, int q, int r) {
        // Length of Left subarray
        int n1 = q - p + 1;
        // Length of Right subarray
        int n2 = r - q;
        // left sub array
        int L[]= new int[n1+1];
        // right sub array
        int R[] = new int[n2+1];
        for(int i = 0; i < n1; i++)
            L[i] = arr[p+i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[q + 1 + j];
        L[n1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;
        int i = 0, j = 0, k = p;
        while (k <= r) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
    }
}
