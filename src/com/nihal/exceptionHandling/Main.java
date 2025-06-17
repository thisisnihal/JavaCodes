package com.nihal.exceptionHandling;

public class Main {
    public static void main(String[] args) {
        int a = 5;
        int b = 0;
        try {
            // uncomment one of three line to see outputs
            // int c = a / b;
            // divide(a, b);
            // throw new Exception("just for fun");
            String name = "Nihal";
            if (name.equals("Nihal")) {
                throw new MyException("name is Nihal");
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        } 
        catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }  catch (Exception e) {
            System.out.println("normal exception");
        }
         finally {
            System.out.println("this will always execute");
            // finally can be used to close files etc.
        }
        
         
    }
    static int divide (int a, int b) throws ArithmeticException {
        if (b==0) {
            throw new ArithmeticException("please do not divide by zero");
        }
        return a/b;
    }
}
