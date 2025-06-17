package com.nihal.abstractDemo;

public class Main {
    public static void main(String[] args) {
        Son son = new Son(27);
        son.carrer("Doctor");
        son.partner("Shakira", 32);
        son.hello();
        son.normal();

        Daughter daughter = new Daughter(25);
        daughter.carrer("Journalist");
        daughter.partner("Heath Ledger", 24);

        // Parent dad = new Parent();   //-> you cannot create object of abstract class like this.
    }
}
