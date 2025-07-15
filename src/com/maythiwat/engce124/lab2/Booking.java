package com.maythiwat.engce124.lab2;

public class Booking {
    int id;
    String name;
    int no;
    String date;
    String time;

    public Booking() {
        this(0, "", 0, "", "");
    }

    public Booking(int id, String name, int no, String date, String time) {
        this.id = id;
        this.name = name;
        this.no = no;
        this.date = date;
        this.time = time;
    }
}
