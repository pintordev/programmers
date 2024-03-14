package level3;

class Solution_42898 {

    public static void main(String[] args) {

        Solution_42898 s = new Solution_42898();

        int m = 4;
        int n = 3;
        int[][] puddles = {{2, 2}};
        int result = 4;
        System.out.println(s.solution(m, n, puddles) == result);
    }

    public int solution(int m, int n, int[][] puddles) {

        int[][] map = new int[m][n];
        for (int[] puddle : puddles) map[puddle[0] - 1][puddle[1] - 1] = -1;

        map[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == -1) continue;
                if (i > 0 && map[i - 1][j] != -1) map[i][j] += map[i - 1][j];
                if (j > 0 && map[i][j - 1] != -1) map[i][j] += map[i][j - 1];
                map[i][j] %= 1000000007;
            }
        }

        return map[m - 1][n - 1];
    }
}