package com.maythiwat.engce124.lab2;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int currentId = 0;
        BookingList list = new BookingList();
        Scanner scanner = new Scanner(System.in);

        System.out.println("***** CPE Premium Restaurant *****");
        System.out.println("( Available commands: add, cancel, check, exit )");

        while (true) {
            System.out.print("Command: ");
            String cmd = scanner.nextLine();

            if (cmd.equals("add")) {
                currentId = addBooking(scanner, list, currentId);
            } else if (cmd.equals("cancel")) {
                cancelBooking(scanner, list);
            } else if (cmd.equals("check")) {
                checkBooking(scanner, list);
            } else if (cmd.equals("exit")) {
                System.exit(0);
            } else {
                System.out.println("Invalid command, please try again.");
            }
        }
    }

    static int addBooking(Scanner scanner, BookingList list, int currentId) {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("No.: ");
        int no = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Date: ");
        String date = scanner.nextLine();
        System.out.print("Time: ");
        String time = scanner.nextLine();

        boolean isDupe = false;
        for (int i = 0; i < list.size(); i++) {
            Booking item = list.get(i);
            if (name.equals(item.name) && no == item.no && date.equals(item.date) && time.equals(item.time)) {
                isDupe = true;
                break;
            }
        }

        if (isDupe) {
            System.out.println("You can not reserve.");
            return currentId;
        }

        currentId++;
        Booking booking = new Booking(currentId, name, no, date, time);
        list.add(booking);

        System.out.println();
        System.out.println("ID: #" + booking.id);
        System.out.println("No.: " + booking.no);
        System.out.println("Date: " + booking.date);
        System.out.println("Time: " + booking.time);
        System.out.println();

        return currentId;
    }

    static void checkBooking(Scanner scanner, BookingList list) {
        System.out.print("No.: ");
        int no = scanner.nextInt();
        scanner.nextLine();

        int count = 0;
        boolean dupeBook = false;
        boolean doubleBook = false;
        int firstIdx = -1;

        for (int i = 0; i < list.size(); i++) {
            Booking item = list.get(i);
            if (item.no == no) {
                firstIdx = i;
                count++;
                System.out.println("Current Queue @ ID: #" + item.id + ", Name: " + item.name);
                break;
            }
        }

        if (count > 0) {
            for (int j = firstIdx + 1; j < list.size(); j++) {
                Booking item1 = list.get(firstIdx);
                Booking item2 = list.get(j);
                if (item2.no == no) {
                    if (item2.name.equals(item1.name)) {
                        dupeBook = true;
                    } else {
                        doubleBook = true;
                    }
                    count++;
                }
            }

            if (dupeBook) {
                System.out.println("Duplicate booking");
            } else if (doubleBook) {
                System.out.println("Double booking");
            }
        } else {
            System.out.println("This table has no any reservations.");
        }
    }

    static void cancelBooking(Scanner scanner, BookingList list) {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        int idx = list.searchById(id);
        list.remove(idx);

        System.out.println("ID: #" + id + " Canceled.\n");
    }
}
