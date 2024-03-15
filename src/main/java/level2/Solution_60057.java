package level2;

class Solution_60057 {

    public static void main(String[] args) {

        Solution_60057 s = new Solution_60057();

        String[] ss = {"aabbaccc", "ababcdcdababcdcd", "abcabcdede", "abcabcabcabcdededededede", "xababcdcdababcdcd"};
        int[] result = {7, 9, 8, 14, 17};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(ss[i]) == result[i]);
        }
    }

    public int solution(String s) {

        int len = Integer.MAX_VALUE;
        for (int i = 1; i <= s.length(); i++) len = Math.min(len, comp(s, i));

        return len;
    }

    public int comp(String s, int n) {

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            if (i + n > s.length()) {
                sb.append(s.substring(i));
                break;
            }
            String ss = s.substring(i, i + n);
            int count = 0;
            while (i + n <= s.length() && ss.equals(s.substring(i, i + n))) {
                count++;
                i += n;
            }
            if (count > 1) sb.append(count);
            sb.append(ss);
        }

        return sb.length();
    }
}