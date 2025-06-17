package com.nihal.generics.comparing;

public class Student implements Comparable<Student> {
    int rollno;
    float marks;

    public Student(int rollno, float marks) {
        this.rollno = rollno;
        this.marks = marks;
    }

    @Override
    public int compareTo(Student o) {
        int diff = (int) (this.marks - o.marks);
        // if diff == 0 means both have same values
        // if diff > 0 means this.marks > o.marks
        // if diff < 0 means this.marks < o.marks

        return diff;
    }
    @Override
    public String toString() {
        return "rollno:"+rollno+" marks:"+marks+"";
    }

}
