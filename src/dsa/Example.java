package dsa;

enum Color {
    RED, GREEN, BLUE;
}

public class Example {
    public static void main(String[] args) {
        // Using values() method to iterate through enum constants
        System.out.println("Colors:");
        for (Color color : Color.values()) {
            System.out.println(color);
        }

        // Using valueOf() method to get an enum constant by name
        Color myColor = Color.valueOf("GREEN");
        System.out.println("My favorite color: " + myColor);
        System.out.println(Color.BLUE.ordinal());

        int num = -16;
        int result = num >>> 2;
        System.out.println(result);
    }
}
