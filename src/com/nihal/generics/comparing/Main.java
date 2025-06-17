package com.nihal.generics.comparing;

import java.io.StreamCorruptedException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Student nihal = new Student(2, 50.2f);
        Student jhon = new Student(4, 14.3f);
        Student jake = new Student(5, 65.3f);

        if (nihal.compareTo(jhon) > 0) {
            System.out.println("Nihal have more marks than Jhon.");
        } else if (nihal.compareTo(jhon) < 0) {
            System.out.println("Nihal have less marks than Jhon.");
        } else if (nihal.compareTo(jhon) == 0) {
            System.out.println("Nihal have same marks as Jhon.");
        }
        // marks -> 50.2, 65.3, 14.3
        Student[] list = { nihal, jake, jhon };
        System.out.println(Arrays.toString(list));

        System.out.println("\nSorting in increasing order of marks.");
        // sort in increasing order of marks.
        Arrays.sort(list); 
        System.out.println(Arrays.toString(list));

       System.out.println("\nSorting in increasing order of rollno.");


        // sort in increasing order of rollno.
        Arrays.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return (int) (o1.rollno - o2.rollno);
            }

        });
        System.out.println(Arrays.toString(list));

        System.out.println("\nSorting in decreasing order of rollno.");
        // sort in decreasing order of rollno.
        Arrays.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return -(int) (o1.rollno - o2.rollno);
            }

        });
        System.out.println(Arrays.toString(list));

        // using lambda function
        System.out.println("\nSorting in increasing order of marks. using lambda function");
        Arrays.sort(list, (o1, o2) -> (int)(o1.marks-o2.marks)); 
        System.out.println(Arrays.toString(list));

    }
}
