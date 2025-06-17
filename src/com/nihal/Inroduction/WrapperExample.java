package com.nihal.Inroduction;

import java.util.Scanner;

public class WrapperExample {
    public static void main(String[] args) {

        final int a = 10;
        // a = 20; // error, cannot reassign primitive data type a new value
        final A person = new A("Nihal");
        person.name = "Nihal Kumar"; // we can change or modify the attributes of the final non-primitive data type

        // person = new A("Jhonny Bravo"); //-> error
        // error, we cannot reassign new object to a final non-primitive data type

        A student = new A("Eddie");
        student = new A("Addo"); // OK .

        System.out.println(person); // it will print some hashcode.

        // To see garbage collector in action we're creating 10M objects.
        // Before garbage collector come and destroy it'll call the finalize method of
        // that object.
        for (int i = 0; i < 909099; i++) {
            A obj = new A(String.valueOf(i));
        }

        Scanner sc  = new Scanner(System.in);
        
    }

}

class A {

    final int a = 10;
    String name;

    A(String name) {
        this.name = name;
    }

    // JAVA will call this method(i.e. finalize()) before deleting this object.
    @Override
    protected void finalize() throws Throwable {
        System.out.println(this.name + " has been destroyed !");
    }
}