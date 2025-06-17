package com.nihal.interfaces.temp1;

import java.io.*;

// Class must implement Serializable
class Person implements Serializable {
    String name;
    int age;
}

public class SerializeExample {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person p = new Person();
        p.name = "Alice";
        p.age = 30;

        // Serialize to a file
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.ser"));
        oos.writeObject(p);
        oos.close();

        // Deserialize from the file
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.ser"));
        Person deserializedPerson = (Person) ois.readObject();
        ois.close();

        System.out.println(deserializedPerson.name); // Output: Alice
        System.out.println(deserializedPerson.age);  // Output: 30
    }
}
