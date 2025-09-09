package com.maythiwat.engce124.lab3;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter infix: ");
        String infix = sc.nextLine().replace(" ", "");

        String postfix = Postfix.fromInfix(infix);
        System.out.println("Postfix: " + postfix);

        float result = Postfix.evaluate(postfix);

        System.out.println();
        System.out.println("Infix: " + infix);
        System.out.println("Postfix: " + postfix);
        System.out.println("Result : " + result);
    }
}
