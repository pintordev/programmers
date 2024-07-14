package level4.solution_17685;

import java.util.Arrays;

class Solution_17685 {

    public static void main(String[] args) {

        Solution_17685 s = new Solution_17685();

        String[][] words = {
                {"go", "gone", "guild"},
                {"abc", "def", "ghi", "jklm"},
                {"word", "war", "warrior", "world"}
        };
        int[] result = {7, 4, 15};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(words[i]) == result[i]);
        }
    }

    public int solution(String[] words) {
        Arrays.sort(words);

        int n = words.length;
        int[] typing = new int[n];
        for (int i = 0; i < n - 1; i++) {
            String cur = words[i];
            String next = words[i + 1];

            int comLen = getComLen(cur, next);

            if (comLen == cur.length()) typing[i] = Math.max(typing[i], comLen);
            else typing[i] = Math.max(typing[i], comLen + 1);
            typing[i + 1] = Math.max(typing[i + 1], comLen + 1);
        }


        int cnt = 0;
        for (int t : typing) {
            cnt += t;
        }
        return cnt;
    }

    public int getComLen(String cur, String next) {
        int len = 0;
        for (int i = 0, t = Math.min(cur.length(), next.length()); i < t; i++) {
            if (cur.charAt(i) == next.charAt(i)) len++;
            else break;
        }
        return len;
    }
}