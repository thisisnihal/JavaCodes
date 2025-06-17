package com.nihal.functions;

public class Shadowing {
    
    static int x = 10;

    static int y = 2140;
    public static void main(String[] args) {
        System.out.println(x); // 10
        int x = 99; // the class variable at line 5 is shadowed by this.
        System.out.println(x); // 99
        fun(); // prints 10


        int y;
        // System.out.println(y); // error, cause shadowing begin when local variable is declared as well as initialised.
        y = 12;

    }
    static void fun() {
        System.out.println(x); // 10
    }
}
