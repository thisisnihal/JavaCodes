package com.nihal.properties.inheritance;

public class BoxWeight extends Box {
     double weight;

     public BoxWeight () {
        this.weight = -1;
     }
     BoxWeight (BoxWeight other) {
        super(other); 
        this.weight = other.weight;
        // ctrl+click on the super. you'll notice it takes Box as type and we are passing BoxWeight type in the super.
        // Box box5 = new BoxWeight(2, 4, 6, 10);
        // Box (Box old) {
        //     this.h = old.h;
        //     this.l = old.l; 
        //     this.w = old.w;
        // }
        
     }
     BoxWeight (double l, double h, double w, double weight) {
        super(l,h,w);
        this.weight = weight;
       // super.h = 144; // we can access parent class attributes by using super keyword. it's similar to this keyword.

     }
}
