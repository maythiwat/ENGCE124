package com.maythiwat.engce124;

import com.maythiwat.engce124.lab2.practice.List;

public class Dummy {
    public static void main(String[] args) {
        List list = new List();
        list.add(10);
        list.add(20);
        list.add(30);
        list.show();
        list.insert(2, 40);
        list.show();
        list.add(50);
        list.add(60);
        list.show();
        list.remove(4);
        list.show();
    }
}
