package level3.solution_77886;

import java.util.Arrays;

class Solution_77886 {

    public static void main(String[] args) {

        Solution_77886 s = new Solution_77886();

        String[] _s = {"1110", "100111100", "0111111010"};
        String[] result = {"1101", "100110110", "0110110111"};

        System.out.println(Arrays.equals(s.solution(_s), result));
    }

    public String[] solution(String[] s) {

        for (int i = 0, n = s.length; i < n; i++) {
            s[i] = move110(s[i]);
        }
        return s;
    }

    public String move110(String s) {
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i = 0, n = s.length(); i < n; i++) {
            char c = s.charAt(i);
            sb.append(c);
            if (c != '0') continue;
            int len = sb.length();
            if (len >= 3 && sb.substring(len - 3).equals("110")) {
                sb.delete(len - 3, len);
                cnt++;
            }
        }

        int idx = sb.lastIndexOf("0");
        sb.insert(idx + 1, "110".repeat(cnt));
        return sb.toString();
    }
}