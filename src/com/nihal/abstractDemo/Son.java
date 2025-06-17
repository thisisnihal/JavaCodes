package com.nihal.abstractDemo;

public class Son extends Parent {

    public Son (int age) {
        super(age);
    }
    @Override
    void carrer(String name) {
        System.out.println("I am going to be a " + name + " !! ");
    }

    @Override
    void partner(String name, int age) {
        System.out.println("I love " + name + ". She is " + age );
    }

}
