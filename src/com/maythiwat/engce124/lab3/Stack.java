package com.maythiwat.engce124.lab3;

import java.util.Arrays;

public class Stack {
    private final Object[] arr;
    private int top = -1;

    public Stack() {
        this(10);
    }

    public Stack(int s) {
        arr = new Object[s];
    }

    void push(Object item) {
        if (!isFull()) arr[++top] = item;
    }

    Object pop() {
        Object item = null;
        if (!isEmpty()) item = arr[top--];
        return item;
    }

    Object top() {
        return isEmpty() ? null : arr[top];
    }

    int size() {
        return top + 1;
    }

    boolean isEmpty() {
        return top < 0;
    }

    boolean isFull() {
        return top == arr.length - 1;
    }

    void showAll() {
        System.out.print("Stack(" + size() + "): ");
        for (int i = 0; i <= top; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public Object[] asArray() {
        return Arrays.copyOf(arr, size());
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i <= top; i++) {
            str.append(arr[i]);
        }
        return str.toString();
    }
}
