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

        int len = 0, count = 0;
        String ss = "";
        for (int i = 0; i <= s.length(); i += n) {

            if (i + n > s.length()) {
                len += getLen(count) + ss.length() + s.length() - i;
                continue;
            }

            String sss = s.substring(i, i + n);
            if (!ss.equals(sss)) {
                len += getLen(count) + ss.length();
                ss = sss;
                count = 1;
            } else {
                count++;
            }
        }

        return len;
    }

    public int getLen(int count) {
        if (count <= 1) return 0;
        else return ("" + count).length();
    }
}