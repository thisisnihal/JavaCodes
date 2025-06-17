package com.nihal.properties.polymorphism;

public class Main extends Shapes{
    public static void main(String[] args) {
        Shapes shape = new Shapes();
        Circle circle = new Circle();
        Square square = new Square();
        Triangle triangle = new Triangle();

        circle.area();
    }
}
