package com.nihal.enumExamples;


public class Main {
    enum Week implements A {
        Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday;
        // these are enum constants
        // public, static and final
        // since its a final you can't create child enums
        // type its Week

        // this constructor is not public or protected, only private or default. 
        // why? we do not want to create new objects because this's not the enum concept, thats why
        Week () {
            System.out.println("Constructor called for "+ this);
        }

        

        // internally : public static final Week Friday = new Week();
        // when constructor is being called.
        @Override
        public void hello() {
            System.out.println("hello from enum");
        }

        // void display ();  // abstract is not allowed . method body is required.


    }
    public static void main(String[] args) {
        Week week;
        week = Week.Friday;
        week.hello();

        System.out.println(week.valueOf("Monday"));  // valueof return the enum constants

        // Week weekDay = week.valueOf("Saturday");
        // System.out.println(weekDay);

        // System.out.println(week);
        // System.out.println(week.ordinal());

        // iterating through all values in the enum Week
        // for (Week day : Week.values()) {
        //         System.out.println(day);
        // }
    }
}
