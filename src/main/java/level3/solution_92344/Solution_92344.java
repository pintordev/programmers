package level3.solution_92344;

class Solution_92344 {

    public static void main(String[] args) {

        Solution_92344 s = new Solution_92344();

        int[][][] board = {{{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}},
                {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}};
        int[][][] skill = {{{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}},
                {{1, 1, 1, 2, 2, 4}, {1, 0, 0, 1, 1, 2}, {2, 2, 0, 2, 0, 100}}};
        int[] result = {10, 6};

        int t = board.length;
        for (int i = 0; i < t; i++) {
            System.out.println(s.solution(board[i], skill[i]) == result[i]);
        }
    }

    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        int[][] memo = new int[n + 2][m + 2];

        for (int[] s : skill) {
            int damage = s[0] == 1 ? -s[5] : s[5];
            memo[s[1] + 1][s[2] + 1] += damage;
            memo[s[1] + 1][s[4] + 2] += -damage;
            memo[s[3] + 2][s[2] + 1] += -damage;
            memo[s[3] + 2][s[4] + 2] += damage;
        }

        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                memo[i][j] += memo[i - 1][j] + memo[i][j - 1] - memo[i - 1][j - 1];
                if (board[i - 1][j - 1] + memo[i][j] > 0) cnt++;
            }
        }
        return cnt;
    }
}