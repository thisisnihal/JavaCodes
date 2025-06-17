package com.nihal.abstractDemo;

// if a class contains one/more abstract method(s) then the class has to be abstract.
// note: we cannot have final abstract classes cause final keyword prevent inheritance. 
public abstract class Parent {

    int age;
    final int VALUE;
    public Parent () {
        VALUE = 12323;
    }
    public Parent (int age) {
        this.age = age;
        VALUE = 475945;
    }
    // these are the empty methods , therefore, the Subclass must override these methods.
    abstract void carrer(String name);      
    abstract void partner(String name, int age);

    static void hello () {
        System.out.println("Hello !, static method in abstract class");
    }
    void normal () {
        System.out.println("Normal !, non-static method in abstract class");
    }
}
