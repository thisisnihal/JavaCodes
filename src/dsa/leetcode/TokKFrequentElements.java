package dsa.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TokKFrequentElements {
    static void print2dArray(List<List<Integer>> matrix) {
        int row = matrix.size();
        int col = matrix.get(0).size();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print("" + matrix.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public static int[] topKFrequent(int[] nums, int k) {
        int[] topK = new int[k];
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums)
            freq.put(num, freq.getOrDefault(num, 0) + 1);

        List<List<Integer>> bucket = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) {
            bucket.add(new ArrayList<>());
        }

        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            bucket.get(e.getValue()).add(e.getKey());
        }
        System.out.println(freq);
        print2dArray(bucket);

        for (int i = bucket.size() - 1, j = 0; i > 0 && j < k; i--) {
            if (bucket.get(i).size() > 0) {
                for (int num : bucket.get(i)) {
                    if (j >= k)
                        break;
                    topK[j] = num;
                    j++;
                }
            }
        }

        return topK;

    }

    public static void main(String[] args) {
        int[] nums = { 1, 1, 1, 2, 2, 3 };
        int k = 2;
        int[] ans = topKFrequent(nums, k);
        System.out.println(Arrays.toString(ans));

    }
}
