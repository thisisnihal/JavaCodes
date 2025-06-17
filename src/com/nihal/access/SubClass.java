package com.nihal.access;


public class SubClass extends A {

    public SubClass(int n, int m) {
        super(n, m);
    }

    int a = super.publicVar;
    int b = super.protectedVar;
    int c = super.noModifierVar;
    int d = super.getPrivateVar(); // cause we cannot access privateVar !!
    // subclass can everything except private var

    public static void main(String[] args) {

        int val = 1115;
        A obj = new A(val, val);
        int a1 = obj.publicVar;
        int b1 = obj.protectedVar;
        int c1 = obj.noModifierVar;
        System.out.println(a1);
    }
}
