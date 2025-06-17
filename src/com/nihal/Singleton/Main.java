package com.nihal.Singleton;

public class Main {
    public static void main(String[] args) {
        Singleton obj1 = Singleton.getInstance();

        Singleton obj2 = Singleton.getInstance();

        Singleton obj3 = Singleton.getInstance();
        
        // all 3 ref variables are pointing to just one object.
        // if you only want to have one object of a particular thing then use singleton class.



        System.err.println("obj1 = "+obj1.getX() + ", obj2 = " + obj2.getX() + ", obj3 = " + obj3.getX());


        obj1.setX(5);
        System.err.println("obj1 = "+obj1.getX() + ", obj2 = " + obj2.getX() + ", obj3 = " + obj3.getX());

        obj2.setX(10);
        System.err.println("obj1 = "+obj1.getX() + ", obj2 = " + obj2.getX() + ", obj3 = " + obj3.getX());

        obj3.setX(20);
        System.err.println("obj1 = "+obj1.getX() + ", obj2 = " + obj2.getX() + ", obj3 = " + obj3.getX());

    }
}
