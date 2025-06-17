package com.nihal.interfaces;

public class Main {
    public static void main(String[] args) {
        Car car = new Car("Mustang");
        car.start();
        car.acc();
        car.brake();
        car.stop();
        int p = car.PRICE;

        Engine car2 = new Car("Mini Cooper");
        car2.start();   
        car2.acc();
        car2.stop();
        int p2 = car2.PRICE;

        Media carMedia = new Car("Rolls Royce");
        carMedia.start();      // we're not getting desired output : )
        // media.acc();  //-> error cause Media class have no acc method defined
        carMedia.stop();

        NiceCar car3 = new NiceCar();
        System.out.println("Nice Car created ..");
        car3.start();
        car3.startMusic();
        car3.upgradeEngine(new ElectricEngine());
        car3.start();
    }
}
/*
🔁 Methods go with the object (runtime),
📦 Variables go with the reference (compile time)

Core Concept: Reference Type vs. Object Type
Think of it like this:
Reference type = What Java thinks the object is at compile time
Object type = What the object actually is at runtime
Easy Analogy: TV Remote and TV
Reference = Remote control (what buttons it has)
Object = Actual TV (what it can do)
You can point a basic remote (Parent) at a smart TV (Child), but you'll only have access to the buttons the remote knows about.

 */
