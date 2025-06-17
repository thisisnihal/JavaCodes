package com.nihal.generics.comparing;

import java.util.ArrayList;
import java.util.function.Consumer;

public class LambdaFunction {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            list.add(i);
        }
        System.out.println("Printing the Squares of the Integers in the list");
        list.forEach((item) -> System.out.print(item*item+ " "));

        System.out.println("\nPrinting the Squares of the Integers in the list by creating Consumer object first ");
        Consumer<Integer> fun = (item) -> System.out.print(item*item+ " ");
        list.forEach(fun);
        // so basically .forEach is taking a generic object of Consumer type 

        System.out.println();

        Operation sum = (a, b) -> a+b;
        Operation sub = (a, b) -> a-b;
        Operation prod = (a, b) -> a*b;
        Operation div = (a, b) ->  a/b;


        LambdaFunction calc = new LambdaFunction();
        int x = calc.operate(12, 6, sum);
        System.out.println("x = "+x);

        int x2 = calc.operate(12, 6, div);
        System.out.println("x2 = "+x2);
    }
    private int operate (int a, int b, Operation op) {
        return op.operation(a, b);
    }
}

interface Operation {
    int operation(int a, int b);
}
