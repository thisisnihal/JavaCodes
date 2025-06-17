package com.nihal.generics;

public class Main implements GenericInterface<String>{
    public static void main(String[] args) {
        Main m = new Main();
        m.display("hola");
    }

    @Override
    public void display(String value) {
        System.out.println(value + "  This is how generic interface works !!");
    }
}
