package com.nihal.interfaces.extendDemo;

// a interface can 'extends' another interface.
// but a class can 'implements' another interface.
public interface B extends A {
    void sayHello();
}

// it is similar to the below code.
// public interface B {
//     void sayHello();
//     void greet ()
// }