package level2;

import java.util.Arrays;

class Solution_12946 {

    public static void main(String[] args) {

        Solution_12946 s = new Solution_12946();

        int[] n = {2, 3};
        int[][][] result = {{{1, 2}, {1, 3}, {2, 3}},
                {{1, 3}, {1, 2}, {3, 2}, {1, 3}, {2, 1}, {2, 3}, {1, 3}}};
        for (int i = 0; i < result.length; i++) {
            boolean isEqual = true;
            for (int j = 0; j < result[i].length; j++) {
                if (!Arrays.equals(s.solution(n[i])[j], result[i][j])) isEqual = false;
            }
            System.out.println(isEqual);
        }
    }

    public int[][] solution(int n) {

        int[][][][][] dp = new int[n + 1][4][4][][];
        return hanoi(n, dp, 1, 2, 3);
    }

    public int[][] hanoi(int n, int[][][][][] dp, int s, int m, int e) {
        if (dp[n][s][e] != null) return dp[n][s][e];
        if (n == 1) {
            dp[n][s][e] = new int[][] {{s, e}};
        } else {
            int len = (int) Math.pow(2, n) - 1;
            dp[n][s][e] = new int[len][2];
            int[][] f = hanoi(n - 1, dp, s, e, m);
            for (int i = 0; i < f.length; i++) dp[n][s][e][i] = f[i];
            dp[n][s][e][len / 2] = new int[]{s, e};
            int[][] b = hanoi(n - 1, dp, m, s, e);
            for (int i = 0; i < b.length; i++) dp[n][s][e][len / 2 + 1 + i] = b[i];
        }
        return dp[n][s][e];
    }
}