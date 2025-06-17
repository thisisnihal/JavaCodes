package com.nihal.interfaces.nested;

import com.nihal.interfaces.nested.A;
// import com.nihal.interfaces.nested.A.NestedInterface;    // -> class B implements NestedInterface { } // no need of A.Nestedinterface

public class A {

    // nested interface
    public interface NestedInterface {
        boolean isOdd(int num);
    }
}

class B implements A.NestedInterface {
    @Override
    public boolean isOdd(int num) {
        return (num & 1) == 1;
    }
}
