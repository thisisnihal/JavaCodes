package com.nihal.Singleton;

/*
 * Singleton class used for one time object creation.
 */
public class Singleton {
    
    // use private to restrict from calling constructor
    private Singleton () {

    }
    private static Singleton instance;  // make static to access without object

    public static Singleton getInstance(){
        if (instance == null) {
            instance = new Singleton(); // here we can call constructor method.
        }
        return instance;
    }




    // you can make some test methods to check if all the obj points to ths same ref obj or not
    private int x;

    public int getX() {
        return x;
    }
    public void setX(int a) {
        x = a;
    }


}
