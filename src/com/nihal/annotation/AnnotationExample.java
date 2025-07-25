package com.nihal.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationExample {

    public static void main(String[] args) {
        Product product = new Product("101", "Laptop", 999.99);
        DataProcessor.process(product);
    }

}

class Product {

    @FieldInfo(name = "product_id", required = true)
    private String id;

    @FieldInfo(name = "product_name")
    private String name;

    private double price; // No annotation

    private int extraField;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public double getPrice() { return price; }

    private String getName() {return name; }
}

class DataProcessor {
    public static void process(Object obj) {
        Class<?> clazz = obj.getClass();
        System.out.println("Processing: " + clazz.getSimpleName());


        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println("\nMethods in Product class --- ");
            System.out.println(method.getName());
        }

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true); // to access private fields
            String fieldName = field.getName();

            FieldInfo annotation = field.getAnnotation(FieldInfo.class);

            // print field name
            if (annotation != null) {
                System.out.println("Annotated Field:");
                System.out.println("  Name: " + annotation.name()); // print annotated field name
                System.out.println("  Required: " + annotation.required());
            } else {
                System.out.println("Non-annotated Field:");
                System.out.println("  Name: " + fieldName);    // if no annotation is found on field then print it uses feild.getName()
            }

            try {
                Object value = field.get(obj);
                System.out.println("  Value: " + value);
            } catch (IllegalAccessException e) {
                System.out.println("  Could not access value.");
            }

            System.out.println();
        }
    }
}
