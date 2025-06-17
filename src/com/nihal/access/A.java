package com.nihal.access;

public class A {

    public int publicVar = 86;
    protected int protectedVar = 56;
    private int privateVar = 43;
    int noModifierVar = 1243;

    public A(int n, int m) {
        this.publicVar = n;
        this.privateVar = m;
    }
    public int getPrivateVar () {
        return privateVar;
    }
}
