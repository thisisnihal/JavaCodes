package dsa.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    static String encode(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                sb.append(freq[i]).append((char) (i + 'a'));
            }
        }
        return sb.toString();
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            String code = encode(s);
            if (!map.containsKey(code)) {
                map.put(code, new ArrayList<String>());
            }
            map.get(code).add(s);
        }
        List<List<String>> list = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    public static void main(String[] args) {
        String[] strs = { "eat", "tea", "tan", "ate", "nat", "bat" };
        List<List<String>> list = groupAnagrams(strs);

        System.out.println(list);
    }
}

// https://leetcode.com/problems/valid-anagram/

class ValidAnagrams {
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        if (s.length() != t.length())
            return false;
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : t.toCharArray()) {
            if (!map.containsKey(c) || map.get(c) == 0) {
                return false;
            } else {
                map.put(c, map.get(c) - 1);
            }
        }
        return true;
    }
}