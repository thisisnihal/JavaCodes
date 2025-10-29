package dsa;

import java.util.Set;
import java.util.TreeSet;

public class ExampleTest {
    public static void main(String[] args) {
        Set<Integer> ts = new TreeSet<>((a, b) -> b - a);
        ts.add(30);
        ts.add(10);
        ts.add(20);
        System.out.println(ts);

        Character c = 'B';
        System.out.println(Character.toLowerCase(c));

        String name = "Nihal";
        int age = 22;
        double score = 95.5;

        // Right align (default)
        System.out.printf("%10s %05d %08.2f%n", name, age, score);

        // Left align
        System.out.printf("%-10s %-5d %-8.2f%n", name, age, score);

        // Center align -> Java has no direct specifier; needs manual spaces

    }
}
