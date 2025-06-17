public class App {
    public static void main(String[] args) throws Exception {

        Student jake = new Student();
        System.out.println(jake); // Student@4617c264
        System.out.println(jake.rno); // 0
        System.out.println(jake.name); // null
        System.out.println(jake.marks); // 0.0

        Student drake = new Student(23, "Drake", 58.5f);
        System.out.println(drake.name); // "Drake"

        Student peter = new Student();
        peter.name = "Peter";
        System.out.println(peter.name);

        Student random = new Student(drake);
        System.out.println(random.name);

        Student random1 = new Student(new Student(3, "Silly Guy", 34.4f));
        System.out.println(random1.name);

    }

}

class Student {
    int rno;
    String name;
    float marks;

    Student() {
        // this.rno = -1;
        // this.name = "Default Person";
        // this.marks = 100.0f;
        this (-1, "Defalut Person", 100.0f);
    }


    Student(int roll, String n, float m) {
        this.rno = roll;
        this.name = n;
        this.marks = m;
    }

    Student(Student other) {
        this.name = other.name;
        this.rno = other.rno;
        this.marks = other.marks;
    }
}