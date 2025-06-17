package com.nihal.interfaces.extendDemo2;

public class Main implements A, B {
    public static void main(String[] args) {
      A.greeting(); 
      Main obj = new Main();
      obj.defaultFunction();
      obj.func();
      obj.greet();
        
    }

    @Override
    public void greet() {
        
    }

    @Override
    public void func() {
      System.out.println("Nothing interesting");
    }

    // func cannot be private cause in interface it was no-modifier there it cannot be more restrictive than before.

    // @Override
    // private void func() {
    //   System.out.println("Nothing interesting");
    // }

}
