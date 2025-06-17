package com.nihal.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        // ref var of parent class and object of child class.
        List<Integer> list2 = new LinkedList<>();
        
        list2.add(30);
        list2.add(23);
        System.out.println(list2);
    }
}
