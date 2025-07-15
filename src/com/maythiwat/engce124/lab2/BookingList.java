package com.maythiwat.engce124.lab2;

import java.util.Arrays;

public class BookingList {
    Booking[] arr;
    int count;

    public BookingList() {
        this(5);
    }

    public BookingList(int x) {
        arr = new Booking[x];
    }

    Booking get(int idx) {
        if (idx >= 0 && idx < count) {
            return arr[idx];
        }
        return null;
    }

    void add(Booking item) {
        if (arr.length == count) {
            arr = Arrays.copyOf(arr, arr.length * 2);
        }
        arr[count] = item;
        count++;
    }

    void remove(int idx) {
        if (idx >= 0 && idx < count) {
            for (int i = idx; i < count - 1; i++) {
                arr[i] = arr[i + 1];
            }
            count--;
        }
    }

    int searchById(int value) {
        int idx = -1;
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                if (arr[i].id == value) {
                    idx = i;
                    break;
                }
            }
        }
        return idx;
    }

    void show() {
        for (int i = 0; i < count; i++) {
            System.out.println("[" + i + "]: " + arr[i]);
        }
    }

    int size() {
        return count;
    }
}
