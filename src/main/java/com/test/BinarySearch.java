package com.test;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int arry[] = {-150, 1, 4, 7, 7, 11};
        int arry1[] = {-2, 4, 7, 9, 11, 12};
        System.out.println(Arrays.binarySearch(arry1, -1));
        System.out.println(binarySearch(arry, 7));
    }

    /**
     * returns first matched index of the key
     *
     * @param arr
     * @param key
     * @return
     */
    public static int binarySearch(int arr[], int key, boolean searchFirst) {
        int low = 0;
        int high = arr.length - 1;
        int mid, result = -1;
        while (low <= high) {
            mid = (int) Math.floor((low + high) / 2);
            if (arr[mid] == key) {
                result = mid;
                if (searchFirst)
                    high = mid - 1;
                else
                    low = mid + 1;
            }
            else if (arr[mid] < key)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return result;
    }

    public static int binarySearch(int arr[], int key) {
        int min = 0, max = arr.length - 1;
        int mid;
        while (min <= max) {
            System.out.println("iteration...");
            mid = (int) Math.floor((float) (min + max) / 2);
            if (arr[mid] == key) return mid;

            if (arr[mid] < key) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return -1;
    }
}
