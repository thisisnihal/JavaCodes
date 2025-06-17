package com.nihal.functions;

public class Scope {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        String name = "Nihal";

        {
            // int a = 99; //->error cause already declared outside the block so we can not declare it again. we can just update/change it value.
            a = 50; // reassign the original ref variable to some other value. we can also observe we have not created a new ref variable of that name. so all we can do is just modification 
            System.out.println("inside the block - value of a :"+ a);// 50 

            int c = 60;
        }
        // c = 76; //-> error; variable is declared inside the block so we cant use it outside the block.
        System.out.println("same scope as 'a' is declared - value of a :"+ a); // 50
    }
}
