package com.nihal.functions;

public class Main {
    
    public static void main(String[] args) {
        
        //  
        // All non-primitive types are pass by reference;
        // 
        String name = "Nihal";
        changeName(name);
        System.out.println(name);
        
        StringBuilder sb = new StringBuilder();
        sb.append("Jake");

        System.out.println(sb.toString());
        // modifying value in StringBuilder object
        changeStringBuilder(sb);

        System.out.println(sb.toString());
    }

    private static void changeStringBuilder(StringBuilder sb) {
        sb.append(" Drake");
        // here we are modifying that object
    }

    private static void changeName(String naam) {
        naam = "Jake"; 
    //here we are creating a new object
    // we are not changing something to that object,we are creating a new objects
    }
}
