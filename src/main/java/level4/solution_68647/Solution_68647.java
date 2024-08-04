package level4.solution_68647;

class Solution_68647 {

    public static void main(String[] args) {

        Solution_68647 s = new Solution_68647();

        int[][][] a = {
                {{0, 1, 0}, {1, 1, 1}, {1, 1, 0}, {0, 1, 1}},
                {{1, 0, 0}, {1, 0, 0}},
                {{1, 0, 0, 1, 1}, {0, 0, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 0, 1}}
        };
        int[] result = {6, 0, 72};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(a[i]) == result[i]);
        }
    }

    public int solution(int[][] a) {
        int mod = 10_000_019;
        int n = a.length;
        int m = a[0].length;

        long[][] comb = new long[n + 1][];
        comb[0] = new long[]{1};
        for (int i = 1; i <= n; i++) {
            comb[i] = new long[i + 1];
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % mod;
            }
        }

        int[] noo = new int[m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == 1) noo[j + 1]++;
            }
        }

        long[][] memo = new long[m + 1][n + 1];
        memo[1][n - noo[1]] = comb[n][n - noo[1]];
        for (int j = 2; j <= m; j++) {
            for (int i = 0; i <= n; i++) {
                if (memo[j - 1][i] == 0) continue;
                for (int k = 0; k <= noo[j]; k++) {
                    int ni = (i - k) + (noo[j] - k);
                    if (ni > n || i < k || n - i < noo[j] - k) continue;
                    long nc = comb[i][k] * comb[n - i][noo[j] - k] % mod;
                    memo[j][ni] += memo[j - 1][i] * nc % mod;
                    memo[j][ni] %= mod;
                }
            }
        }
        return (int) memo[m][n];
    }
}