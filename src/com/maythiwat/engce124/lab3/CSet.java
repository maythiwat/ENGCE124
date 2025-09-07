package com.maythiwat.engce124.lab3;

import java.util.Arrays;

public class CSet {
    private char[] arr;
    private int count;

    public CSet() {
        this(5);
    }

    public CSet(int x) {
        arr = new char[x];
    }

    void add(char value) {
        int idx = search(value);

        if (idx == -1) {
            if (arr.length == count) {
                arr = Arrays.copyOf(arr, arr.length * 2);
            }

            arr[count] = value;
            count++;
        }
    }

    void remove(int idx) {
        if (idx >= 0 && idx < count) {
            arr[idx] = arr[count - 1];
            count--;
        }
    }

    int search(int value) {
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

    void show() {
        for (int i = 0; i < count; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    int size() {
        return count;
    }

    char[] getAll() {
        return Arrays.copyOf(arr, count);
    }
}
