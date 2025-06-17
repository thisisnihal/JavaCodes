OOPs_1_Introduction 


it works without this keyword also. but you have name the var name different..
`this` keyword gets replaced with reference variable name
`App.java`
```JAVA
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Student jake = new Student();
        System.out.println(jake);  // Student@4617c264 
        System.out.println(jake.rno);  // 0
        System.out.println(jake.name);  // null
        System.out.println(jake.marks);  // 0.0

        Student drake = new Student(23, "Drake", 58.5f);
        System.out.println(drake.name); // "Drake"

        Student peter = new Student();
        peter.name = "Peter";
        System.out.println(peter.name); // Peter
        
    }

}


class Student {
    int rno;
    String name;
    float marks;
    
    Student () {

    }

    Student (int roll, String n, float m) {
        rno = roll;
        name = n;
        marks = m;
    }
}
```

`App.java`
```JAVA
public class App {
    public static void main(String[] args) {

        Student jake = new Student();
        System.out.println(jake); // Student@4617c264 , some hashcode
        System.out.println(jake.rno); // 0
        System.out.println(jake.name); // null
        System.out.println(jake.marks); // 0.0

        Student drake = new Student(23, "Drake", 58.5f);
        System.out.println(drake.name); // "Drake"

        Student peter = new Student();
        peter.name = "Peter";
        System.out.println(peter.name); // Peter

        Student random = new Student(drake);
        System.out.println(random.name); // Drake

        Student random1 = new Student(new Student(3, "Silly Guy", 34.4f));
        System.out.println(random1.name); // Silly Guy

    }

}

class Student {
    int rno;
    String name;
    float marks;

    Student() {

    }

    Student(int roll, String n, float m) {
        rno = roll;
        name = n;
        marks = m;
    }

    Student(Student other) {
        this.name = other.name;
        this.rno = other.rno;
        this.marks = other.marks;
    }
}
```

Calling a constructor from other constructor using `this` keyword :
```JAVA
class Student {
    int rno;
    String name;
    float marks;

    Student() {
        // this.rno = -1;
        // this.name = "Default Person";
        // this.marks = 100.0f;

        // instead of above there lines you can use 'this' keyword
        // internally: new Student (-1, "Defalut Person", 100.0f);
        this (-1, "Defalut Person", 100.0f);
    }


    Student(int roll, String n, float m) {
        this.rno = roll;
        this.name = n;
        this.marks = m;
    }
```
while creating the object `'this'` keyword get replaced with `'new CLASS_NAME' ()`.

### Use of `final` keyword:

```JAVA
public class temp01 {
    public static void main(String[] args) {

        final int a = 10;
        // a = 20; // ->error, cannot reassign primitive data type a new value
        final A person = new A("Nihal");
        person.name = "Nihal Kumar"; // we CAN change or modify the attributes of the final non-primitive data type

        // person = new A("Jhonny Bravo"); //-> error
        // error, we cannot reassign new object to a final non-primitive data type

        A student = new A("Eddie");
        student = new A("Addo"); // OK .

        System.out.println(person); // it will print some hashcode.

        // To see garbage collector in action we're creating 10M objects.
        // Before garbage collector come and destroy it'll call the finalize method of
        // that object.
        for (int i = 0; i < 10000000; i++) {
            A obj = new A(String.valueOf(i));
        }

    }

}

class A {

    final int a = 10;
    String name;

    A(String name) {
        this.name = name;
    }

    // JAVA will call this method(i.e. finalize()) before deleting this object.
    @Override
    protected void finalize() throws Throwable {
        System.out.println(this.name + " has been destroyed !");
    }
}  
```

