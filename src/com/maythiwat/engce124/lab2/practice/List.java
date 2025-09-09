package com.maythiwat.engce124.lab2.practice;

import java.util.Arrays;

public class List {
    int[] arr;
    int count;

    public List() {
        this(5);
    }

    public List(int x) {
        arr = new int[x];
    }

    public void add(int value) {
        if (arr.length == count) {
            arr = Arrays.copyOf(arr, arr.length * 2);
        }
        arr[count] = value;
        count++;
    }

    public void remove(int idx) {
        if (idx >= 0 && idx < count) {
            for (int i = idx; i < count - 1; i++) {
                arr[i] = arr[i + 1];
            }
            count--;
        }
    }

    public int search(int value) {
        int idx = -1;
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                if (arr[i] == value) {
                    idx = i;
                    break;
                }
            }
        }
        return idx;
    }

    public void show() {
        for (int i = 0; i < count; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public int size() {
        return count;
    }

    public void insert(int k, int item) {
        if (k >= 0 && k <= count) {
            if (count == arr.length) {
                arr = Arrays.copyOf(arr, arr.length * 2);
            }

            int j = count - 1; // last idx
            while (j >= k) { // until k
                arr[j + 1] = arr[j]; // send to right
                j--;
            }

            // insert new item to k
            arr[k] = item;
            count++;
        }
    }
}
