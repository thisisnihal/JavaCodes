package com.nihal.cloning;

// if you do not implement the Cloneable interface it will throw the CloneNotSupportedException.
// since Cloneable is an empty interface why we are implementing it?
// ans - it is a way to tell the JVM that this class allows to create a clone of this object.
public class Human implements Cloneable {
    
    int age; // primitives-
    String name; // non-primitives
    int arr[];  // non-primitives
    
    public Human(int age, String name) {
        this.age = age;
        this.name = name;
        this.arr = new int[] {1, 2, 3, 4, 5};
    }


    // public Human(Human other) {
    //     this.age = other.age;
    //     this.name = other.name;
    //     this.arr = other.arr;
    // }

    // @Override    // 
    // public Object clone() throws CloneNotSupportedException {
    //     return super.clone(); //->> creates a shallow copy of this Object, not the deep copy.
    //     // https://stackoverflow.com/questions/5279256/is-clone-in-java-shallow-copy
        
    //     // visit the below site to learn how to create deep copy
    //     // https://www.edureka.co/blog/shallow-and-deep-copy-java/

    //      // ctrl + click on clone() . you'll find out 
    //      // that this method throws an exception of type `CloneNotSupportedException` so you also here in that clone function have 
    //      // specify which Exception it might throw.
    //      // try removing that `throws CloneNotSupportedException` part .

    // }



    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        
        Human twin = (Human)super.clone(); // this is actually shallow copy

        // make a deep copy
        twin.arr = new int[twin.arr.length];
        for (int i = 0; i < twin.arr.length; i++) {
            twin.arr[i] = this.arr[i];
        }
        // enchanced way 
        // System.arraycopy(this.arr, 0, twin, 0, twin.arr.length);

        return twin;
    }
    
}
