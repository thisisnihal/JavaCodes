import java.util.Scanner;
import java.lang.String;

public class Main {
    public static void main(String[] args) {
      Int a = new Int(47);
      Int b = new Int(58);
      System.out.printf("a: %s b: %s\n", a, b);
      Int.swap(a, b);
      System.out.printf("After swap:\na: %s b: %s\n", a, b);
      a.swap(b);
      System.out.printf("After swap:\na: %s b: %s\n", a, b);
    
    
    }

}

class Int {
    private int num;
    Int(int num) {
        this.num = num;
    }
    public int getInt() {
        return num;
    }
    public void setInt(int num) {
        this.num = num;
    }
    @Override
    public String toString() {
       return String.valueOf(num);
    }
    public static void swap(Int a, Int b) {
        int temp = a.getInt();
        a.setInt(b.getInt());
        b.setInt(temp);
    }
    public void swap(Int a) {
        int temp = a.getInt();
        a.setInt(this.num);
        this.num = temp;
    }
}
