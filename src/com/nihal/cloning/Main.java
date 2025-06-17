package com.nihal.cloning;


import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Human nihal =  new Human(19, "Nihal Kumar");
        // Human twin = new Human(nihal); // cloning object like this is a little slow cause of new keyword
    
        // so we'll use a clone function provided by the Object class 
        Human twin = (Human)nihal.clone(); // while using this we have to declare throws exception.
        System.out.println(twin.age+" "+twin.name);


        twin.arr[0] = 327;
        System.out.println(Arrays.toString(twin.arr));

        System.out.println(Arrays.toString(nihal.arr));

    }
}
