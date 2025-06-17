package com.nihal.staticExample;

/*
 * comment one Test class and uncomment the other Test class to see the result.
 */

// outside class cannnot be static cause static class itself depend on non static classes
// class Test {
//     String name;

//     public Test(String name) {
//         this.name = name;
//     }
// }

public class InnerClassExample {
    
    // the below Test class is static wrt InnerClassExample. therefore it doesnt need instance
   static class Test {
        String name;

        public Test(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
        return name;
        }
    }
    public static void main(String[] args) {
        Test a =  new Test("Nihal");
        Test b = new Test("Jake");

        System.out.println(a.name);
        System.out.println(b.name);

        System.out.println(a);
    }


}

// -> error. outside class cant be static.
// static class A {

// }