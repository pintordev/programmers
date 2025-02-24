package level4.solution_86054;

import java.util.Arrays;

class Solution_86054 {

    public static void main(String[] args) {

        Solution_86054 s = new Solution_86054();

        int[] a = {1, 1, 1, 1, 1, 1, 2, 5, 8, 2, 1, 1, 4, 8, 8, 8, 12, 6, 6};
        int[] _s = {4, 3, 1, 5, 6};
        int[] result = {6, 3, 1, 5, 9};

        System.out.println(Arrays.equals(s.solution(a, _s), result));
    }

    public int[] solution(int[] a, int[] s) {
        int sLen = s.length;

        int[] result = new int[sLen];
        int offset = 0;
        for (int i = 0; i < sLen; i++) {
            result[i] = count(Arrays.copyOfRange(a, offset, offset + s[i]));
            offset += s[i];
        }
        return result;
    }

    public static int mod = 1_000_000_007;

    public int count(int[] b) {
        int bLen = b.length;

        long[] ps = new long[bLen];
        ps[0] = b[0];
        for (int i = 1; i < bLen; i++) {
            ps[i] = ps[i - 1] + b[i];
        }

        return -1;
    }
}