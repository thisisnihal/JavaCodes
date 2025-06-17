package com.nihal.interfaces;

public class Car implements Engine, Brake, Media {

    String carName;

    public Car(String carName) {
        this.carName = carName;
    }

    @Override
    public void brake() {
        System.out.println("Brake applied on "+ carName);
    }

    @Override
    public void start() {
        System.out.println(carName+" is starting");
    }

    @Override
    public void stop() {
        System.out.println(carName+" is stopped");
    }

    @Override
    public void acc() {
        System.out.println(carName+" is accelerating");
    }

    int x = Engine.PRICE;

}
