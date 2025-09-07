package com.maythiwat.engce124.lab3;

import java.util.Arrays;
import java.util.Scanner;

public class Postfix {
    private static int precedence(char op) {
        return switch (op) {
            case '+', '-' -> 1;
            case '*', '/', '%' -> 2;
            case '^' -> 3;
            default -> -1;
        };
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^';
    }

    public static String fromInfix(String infix) {
        String s = infix.replace(" ", "");
        Stack op = new Stack(s.length() / 2);
        StringBuilder out = new StringBuilder();

        System.out.println("Infix: " + infix);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // -- numerics
            if (Character.isDigit(c)) {
                int j = i + 1;
                while (j < s.length() && Character.isDigit(s.charAt(j))) j++;
                out.append(s, i, j);
                i = j - 1;
            }
            // -- letter
            else if (Character.isLetter(c)) {
                out.append(c);
            }
            // -- open
            else if (c == '(') {
                op.push(c);
            }
            // -- close
            else if (c == ')') {
                // -- find other operators, pop until find open bracket
                while (!op.isEmpty() && (char) op.top() != '(') {
                    out.append((char) op.pop());
                }
                // -- invalid
                if (op.isEmpty()) {
                    System.out.println("[!] Skipping invalid bracket: " + c);
                    continue;
                }
                // -- pop open bracket
                op.pop();
            }
            // -- operator
            else if (isOperator(c)) {
                while (!op.isEmpty()) {
                    char t = (char) op.top();
                    if (!isOperator(t)) break; // impossible, if good input

                    int pInput = precedence(c), pStack = precedence(t);
                    // boolean doPop = c == '^' ? (pInput < pStack) : (pInput <= pStack);

                    // -- do pop only if input P equal or less than stack P
                    if (pInput <= pStack) out.append((char) op.pop());
                    else break;
                }
                // -- whatever happens, just push
                op.push(c);
            }
            // -- junk
            else {
                System.out.println("[!] Skipping invalid char: " + c);
                continue;
            }

            System.out.println("IN: " + c + ", S: " + op + ", OUT: " + out);
        }

        // เท operator ที่เหลือ
        while (!op.isEmpty()) {
            char t = (char) op.pop();
            if (t == '(' || t == ')') {
                System.out.println("[!] Skipping invalid bracket: " + t);
                continue;
            }
            out.append(t);
        }

        return out.toString();
    }

    private static int findIndex(char[] operands, char target) {
        for (int i = 0; i < operands.length; i++) {
            if (operands[i] == target) return i;
        }
        return -1;
    }

    public static float evaluate(String postfix) {
        Scanner sc = new Scanner(System.in);

        CSet charSet = new CSet(postfix.length() - (postfix.length() / 2));
        for (char c : postfix.toCharArray()) {
            if (Character.isLetter(c)) charSet.add(c);
        }

        char[] operands = new char[charSet.size()];
        float[] values = new float[charSet.size()];

        System.out.println();

        int i = 0;
        for (char v : charSet.getAll()) {
            operands[i] = v;
            System.out.print("Enter value for " + v + " : ");
            values[i] = sc.nextFloat();
            i++;
        }

        return Postfix.evaluate(postfix, operands, values);
    }

    public static float evaluate(String postfix, char[] operands, float[] values) {
        Stack stack = new Stack(postfix.length());
        StringBuilder num = new StringBuilder();

        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                num.append(c);
            }
            // -- end of number
            else {
                if (!num.isEmpty()) {
                    // -- clear number, and push to stack
                    float val = Float.parseFloat(num.toString());
                    stack.push(val);
                    num.setLength(0);
                }

                if (isOperator(c)) {
                    // -- top is right, lower is left
                    float b = (float) stack.pop();
                    float a = (float) stack.pop();

                    switch (c) {
                        case '+':
                            stack.push(a + b);
                            break;
                        case '-':
                            stack.push(a - b);
                            break;
                        case '*':
                            stack.push(a * b);
                            break;
                        case '/':
                            stack.push(a / b);
                            break;
                        case '%':
                            stack.push(a % b);
                            break;
                        case '^':
                            stack.push((float) Math.pow(a, b));
                            break;
                    }
                }
                // -- replace operand with value
                else if (Character.isLetter(c)) {
                    int idx = findIndex(operands, c);
                    if (idx != -1) {
                        stack.push(values[idx]);
                    }
                }
            }

            System.out.println("IN: " + c + ", S: " + Arrays.toString(stack.asArray()) + ", OUT: " + stack.top());
        }

        // -- remaining number
        if (!num.isEmpty()) {
            float val = Float.parseFloat(num.toString());
            stack.push(val);
        }

        return (float) stack.pop();
    }
}
