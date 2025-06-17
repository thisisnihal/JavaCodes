package com.nihal.interfaces.temp1;

import java.util.HashMap;
import java.util.Map;

public class Main {
   public static void main(String[] args) {
    Map<String, Integer> map = new HashMap<>();
 
    map.put("vishal", 10);
    map.put("sachin", 30);
    map.put("vaibhav", 20);

    // Iterating over Map
    for (Map.Entry<String, Integer> e : map.entrySet())

        // Printing key-value pairs
        System.out.println(e.getKey() + " "
                           + e.getValue());
   }
}

