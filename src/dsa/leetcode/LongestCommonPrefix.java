package dsa.leetcode;

public class LongestCommonPrefix {
    static class Solution {
        public String longestCommonPrefix(String[] strs) {
            StringBuilder sb = new StringBuilder();
            int mnlen = Integer.MAX_VALUE;
            for (String str : strs) {
                mnlen = Math.min(mnlen, str.length());
            }
            boolean flag = false;
            for (int i = 0; i < mnlen; i++) {
                sb.append(strs[0].charAt(i));
                for (String str : strs) {
                    if (str.charAt(i) != sb.charAt(i)) {
                        sb.deleteCharAt(i);
                        flag = true;
                        break;
                    }
                }
                if (flag)
                    break;
            }

            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String[] strs = { "flower", "flow", "flight" };
        String ans = s.longestCommonPrefix(strs);
        System.out.println(ans);
    }
}
