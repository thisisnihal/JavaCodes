package com.nihal.properties.inheritance;



public class Main {
    public static void main(String[] args) {
        Box box = new Box(4.6, 5.8, 9.3);
        System.out.println("Box: "+ box.l+ " "+ box.w+ " "+box.h);

        Box box1 = new Box(box);
        System.out.println("Box1: "+box1.l+ " "+ box1.w+ " "+box1.h);

        BoxWeight box3 = new BoxWeight();
        System.out.println("Box3: "+box3.l+ " "+ box3.w+ " "+box3.h);

        BoxWeight box4 = new BoxWeight(2, 3, 4, 8);
        System.out.println("Box4: "+box4.l+ " "+ box4.w+ " "+box4.h+" "+ box4.weight);


        // box5 is the reference type of Box      [for more check the interfaces folders readme file]
        // and it is refrencing to an object of type BoxWeight.
        // its like parent class referencing child class. (e.g. PTM meeting)
        Box box5 = new BoxWeight(2, 4, 6, 10);
        System.out.println(box5.w + "   "+ box5.h); // ok 
        // System.out.println(box5.weight); //-> error
        // we have only access to objects defined in the super class i.e Box class.
        // in other word box5 have only access to the variables that are in reference type Box.
          

        //
        // BoxWeight box6  = new Box(2, 3, 5);
         
    }    
}
