package com.nihal.functions;

import java.util.Arrays;

public class VarArgs {
    public static void main(String[] args) {
        
        fun();
        fun(2, 4, 6, 3, 5);
        fun2(2, 5, "Nihal", "Vishal");

    }

    // this function takes variable length argument (0 to n)
    // internally it takes all the argument and it converts it into an array.
    
    static void fun(int ...v) {
        System.out.println(Arrays.toString(v));
    }

    // variable length argument should be always be declared at last.
    static void fun2 (int a, int b, String ...names) {
        System.out.println("a: "+a+ " b: "+ b);
        System.out.print("names args = ");
        System.out.println(Arrays.toString(names));
    }
}
