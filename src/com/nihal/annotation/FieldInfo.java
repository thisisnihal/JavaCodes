package com.nihal.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

// Create custom annotation

@Retention(RetentionPolicy.RUNTIME) // make it available at runtime
@Target(ElementType.FIELD)  // only valid on fields
public @interface FieldInfo {
    String name(); // custom name
    boolean required() default false; // optional metadata
}
