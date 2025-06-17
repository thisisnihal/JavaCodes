package com.nihal.properties.inheritance;

public class Box {
    double l;
    double h;
    double w;

    Box () {
        this.h = -1;
        this.l = -1;
        this.w = -1;
    }

    Box (double side) {
        super();
        this.h = side;
        this.l = side;
        this.w = side;
    }

    Box (double l, double h, double w) {
        this.h = h;
        this.l = l;
        this.w = w;
    }

    Box (Box old) {
        this.h = old.h;
        this.l = old.l;
        this.w = old.w;
    }

    public void information () {
        System.out.println("Running the Box");
    }
    @Override
    public String toString() {
        return (""+ this.l+ " "+ this.w+ " "+this.h);
    }
}
