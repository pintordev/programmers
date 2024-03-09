package level2;

class Solution_12905 {

    public static void main(String[] args) {

        Solution_12905 s = new Solution_12905();

        int[][][] board = {{{0, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {0, 0, 1, 0}},
                {{0, 0, 1, 1}, {1, 1, 1, 1}},
                {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}};
        int[] result = {9, 4, 1};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(board[i]) == result[i]);
        }
    }

    public int solution(int[][] board) {

        int[][] dp = new int[board.length][board[0].length];
        boolean[][] visited = new boolean[board.length][board[0].length];

        int len = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                len = Math.max(len, square(board, i, j, dp, visited));
            }
        }

        return len * len;
    }

    public int square(int[][] board, int i, int j, int[][] dp, boolean[][] visited) {

        if (visited[i][j]) return dp[i][j];

        visited[i][j] = true;

        if (i == 0 || j == 0) {
            dp[i][j] = board[i][j];
            return dp[i][j];
        }

        dp[i][j] = board[i][j] * Math.min(Math.min(square(board, i - 1, j - 1, dp, visited),
                square(board, i - 1, j, dp, visited)),
                square(board, i, j - 1, dp, visited)) + board[i][j];

        return dp[i][j];
    }
}