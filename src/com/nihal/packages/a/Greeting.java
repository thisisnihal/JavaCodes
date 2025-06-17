package com.nihal.packages.a;

// import public static
import static com.nihal.packages.b.Message.message;


public class Greeting {
    public static void main(String[] args) {
        System.out.println("Hiii, This Greeting.java file is located in com.nihal.packages.a folder\n");

        message(); // calling a static function located in different package.
    }
}
