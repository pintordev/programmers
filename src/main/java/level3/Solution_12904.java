package level3;

class Solution_12904 {

    public static void main(String[] args) {

        Solution_12904 s = new Solution_12904();

        String[] _s = {"abcdcba", "abacde"};
        int[] result = {7, 3};

        int t = _s.length;
        for (int i = 0; i < t; i++) {
            System.out.println(s.solution(_s[i]) == result[i]);
        }
    }

    public int solution(String s) {
        s = expand(s);

        int len = s.length();
        int max = 0;
        for (int i = 0; i < len; i++) {
            max = Math.max(max, palindrome(s, i, Math.min(i, len - i - 1)));
        }

        return max;
    }

    public int palindrome(String s, int i, int r) {
        int len = s.charAt(i) == '#' ? 0 : 1;
        for (int j = 1; j <= r; j++) {
            if (s.charAt(i - j) == '#') continue;
            if (s.charAt(i - j) == s.charAt(i + j)) len += 2;
            else break;
        }
        return len;
    }

    public String expand(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            sb.append(s.charAt(i));
            sb.append("#");
        }
        return sb.toString();
    }
}