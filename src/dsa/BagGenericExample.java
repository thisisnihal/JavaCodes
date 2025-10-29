package dsa;

import java.util.*;

// Common Interface for Note and Coin
interface Money {
    void setValue(int val);
    int getValue();
}

// Coin class
class Coin implements Money {
    private int val;
    public void setValue(int val) { this.val = val; }
    public int getValue() { return val; }
}

// Note class
class Note implements Money {
    private int val;
    public void setValue(int val) { this.val = val; }
    public int getValue() { return val; }
}

// Generic Bag class
class Bag<T extends Money> {
    private List<T> items = new ArrayList<>();
    public void add(T t) { items.add(t); }
    public List<T> getItems() { return items; }
}

public class BagGenericExample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); sc.nextLine(); // Number of items
        Bag<Coin> coinsBag = new Bag<>();
        Bag<Note> notesBag = new Bag<>();
        for (int i = 0; i < N; i++) {
            String[] parts = sc.nextLine().split(" ");
            String type = parts[0];
            int val = Integer.parseInt(parts[1]);
            if (type.equalsIgnoreCase("Coin")) {
                Coin coin = new Coin();
                coin.setValue(val);
                coinsBag.add(coin);
            } else if (type.equalsIgnoreCase("Note")) {
                Note note = new Note();
                note.setValue(val);
                notesBag.add(note);
            }
        }
        // Output format
        System.out.println("Coins :");
        for (Coin coin : coinsBag.getItems()) {
            System.out.println(coin.getValue());
        }
        System.out.println("Notes :");
        for (Note note : notesBag.getItems()) {
            System.out.println(note.getValue());
        }
    }
}
