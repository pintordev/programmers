package level3.solution_1832;

class Solution_1832 {

    public static void main(String[] args) {

        Solution_1832 s = new Solution_1832();

        int[] m = {3, 3};
        int[] n = {3, 6};
        int[][][] cityMap = {{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
                {{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}}};
        int[] result = {6, 2};

        for (int i = 0, t = m.length; i < t; i++) {
            System.out.println(s.solution(m[i], n[i], cityMap[i]) == result[i]);
        }
    }

    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int[][][] memo = new int[2][m + 1][n + 1];
        memo[0][1][1] = 1;
        memo[1][1][1] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int f = cityMap[i - 1][j - 1];
                if (f == 2) {
                    memo[0][i][j] = memo[0][i - 1][j];
                    memo[1][i][j] = memo[1][i][j - 1];
                } else if (f == 0) {
                    memo[0][i][j] += (memo[0][i - 1][j] + memo[1][i][j - 1]) % MOD;
                    memo[1][i][j] += (memo[0][i - 1][j] + memo[1][i][j - 1]) % MOD;
                }
            }
        }
        return memo[0][m][n];
    }
}