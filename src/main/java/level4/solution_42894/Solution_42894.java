package level4.solution_42894;

import java.util.HashSet;
import java.util.Set;

class Solution_42894 {

    public static void main(String[] args) {

        Solution_42894 s = new Solution_42894();

        int[][] board = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                {0, 0, 0, 0, 0, 4, 4, 0, 0, 0},
                {0, 0, 0, 0, 3, 0, 4, 0, 0, 0},
                {0, 0, 0, 2, 3, 0, 0, 0, 5, 5},
                {1, 2, 2, 2, 3, 3, 0, 0, 0, 5},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 5}
        };
        int result = 2;

        System.out.println(s.solution(board) == result);
    }

    public static int n;
    public static int[][] board;
    public static int[][] dr = {
            {1, 1, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {1, 2, 2, 0, 1},
            {2, 2, 1, 0, 1}
    };
    public static int[][] dc = {
            {-1, 0, 1, -1, 1},
            {0, 1, 2, 1, 2},
            {-2, -1, 0, -2, -1},
            {0, 0, 1, 1, 1},
            {-1, 0, 0, -1, -1}
    };
    public static int[][] bc = {
            {0, -1, 1, 1},
            {0, 0, 1, 2},
            {0, -2, 1, 0},
            {0, 0, 2, 1},
            {0, -1, 2, 0}
    };

    public int solution(int[][] board) {
        n = board.length;
        this.board = board;
        Set<Integer> checked = new HashSet<>();

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int num = board[i][j];
                if (num == 0 || checked.contains(num)) continue;

                int type = getType(i, j, num);
                if (type == -1) {
                    checked.add(num);
                    continue;
                }

                if (!canRemove(i, j, type)) continue;
                removeBlock(i, j, type);
                cnt++;
                j = -1;
            }
        }
        return cnt;
    }

    public int getType(int r, int c, int num) {
        for (int i = 0; i < 5; i++) {
            if (r + bc[i][0] < 0 || r + bc[i][2] >= n) continue;
            if (c + bc[i][1] < 0 || c + bc[i][3] >= n) continue;
            if (isRemovable(r, c, num, i)) return i;
        }
        return -1;
    }

    public boolean isRemovable(int r, int c, int num, int type) {
        for (int j = 0; j < 3; j++) {
            if (board[r + dr[type][j]][c + dc[type][j]] == num) continue;
            return false;
        }
        return true;
    }

    public boolean canRemove(int r, int c, int type) {
        for (int i = 3; i < 5; i++) {
            int nr = r + dr[type][i];
            int nc = c + dc[type][i];
            for (int j = 0; j <= nr; j++) {
                if (board[j][nc] != 0) return false;
            }
        }
        return true;
    }

    public void removeBlock(int r, int c, int type) {
        board[r][c] = 0;
        for (int i = 0; i < 3; i++) {
            board[r + dr[type][i]][c + dc[type][i]] = 0;
        }
    }
}