package com.nihal.interfaces.extendDemo2;

public interface A {
    
    // static interface methods should always have a body cause static stuff cannot be overriden and cannot be inherited.
    static void greeting() {
        System.out.println("Hello from interface A");
    }

    // default function in interface have a body
    default void defaultFunction() {
        System.out.println("Defalut Function: I am in Interface A");
    }

    // private method also requires a body
    private void privateMethod () {
        System.out.println("inside of a private function in interface A");
    }

    abstract void func ();
   
}
