package org.example;

import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Stack<String> e = new Stack();
        e.push("5");
        e.push("8");
        e.push("hello duha");

        e.pop();
        System.out.println(e.printStack());

        System.out.println("size: "+ e.size());
        System.out.println("last element: " + e.peek());
        }
    }
