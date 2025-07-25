import java.io.*;
import java.util.*;

public class LargeNumberMultiplication {

    static final int CHUNK_SIZE = 4; // 4-digit chunks for simplicity
    static final int BASE = (int) Math.pow(10, CHUNK_SIZE);

    public static void main(String[] args) throws IOException {
        String num1 = readNumberFromFile("num1.txt");
        String num2 = readNumberFromFile("num2.txt");

        List<Integer> chunks1 = chunkify(num1);
        List<Integer> chunks2 = chunkify(num2);

        Map<Integer, Long> partialSums = new HashMap<>();

        // Map step: simulate all pairwise chunk multiplications
        for (int i = 0; i < chunks1.size(); i++) {
            for (int j = 0; j < chunks2.size(); j++) {
                long product = (long) chunks1.get(i) * chunks2.get(j);
                int position = i + j;

                partialSums.put(position, partialSums.getOrDefault(position, 0L) + product);
            }
        }

        // Reduce step: combine and handle carry
        List<Integer> resultChunks = new ArrayList<>();
        long carry = 0;
        int maxPosition = Collections.max(partialSums.keySet());

        for (int i = 0; i <= maxPosition; i++) {
            long sum = partialSums.getOrDefault(i, 0L) + carry;
            resultChunks.add((int) (sum % BASE));
            carry = sum / BASE;
        }

        // Handle leftover carry
        while (carry > 0) {
            resultChunks.add((int) (carry % BASE));
            carry /= BASE;
        }

        // Convert result to string
        StringBuilder result = new StringBuilder();
        for (int i = resultChunks.size() - 1; i >= 0; i--) {
            if (i == resultChunks.size() - 1)
                result.append(resultChunks.get(i));
            else
                result.append(String.format("%0" + CHUNK_SIZE + "d", resultChunks.get(i)));
        }

        System.out.println("Final Result: " + result.toString());
    }

    // Reads the number from a text file
    public static String readNumberFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line.trim());
        }
        reader.close();
        return sb.toString().replaceFirst("^0+(?!$)", ""); // remove leading zeros
    }

    // Splits a string number into chunks from least significant to most
    public static List<Integer> chunkify(String number) {
        List<Integer> chunks = new ArrayList<>();
        for (int i = number.length(); i > 0; i -= CHUNK_SIZE) {
            int start = Math.max(0, i - CHUNK_SIZE);
            int end = i;
            chunks.add(Integer.parseInt(number.substring(start, end)));
        }
        return chunks;
    }
}
