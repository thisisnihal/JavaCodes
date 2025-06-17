package com.nihal.staticExample;

public class StaticBlock {

    static int a = 4;
    static int b;

    // Static block. It will run only once. It's used to intitialize static variables
    // it will run once, when the first obj is created .i.e when the class is loaded first

    static {
        System.out.println("I am in a static block.");
        b = a * 5;
    }

    public static void main(String[] args) {
        StaticBlock obj = new StaticBlock();
        System.out.println(obj.a + "  " + obj.b);

        StaticBlock.b += 3;
        System.out.println(obj.a + "  " + obj.b);

        StaticBlock obj2 = new StaticBlock();
        System.out.println(obj2.a + "  " + obj2.b);

        int a = (int) Math.pow(10.0, 5.2);
    }
}
