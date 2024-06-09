package level3.solution_60063;

import java.util.ArrayDeque;
import java.util.Queue;

class Solution_60063 {

    public static void main(String[] args) {

        Solution_60063 s = new Solution_60063();

        int[][] board = {{0, 0, 0, 1, 1},
                {0, 0, 0, 1, 0},
                {0, 1, 0, 1, 1},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0}};
        int result = 7;

        System.out.println(s.solution(board) == result);
    }

    public static int n;
    public static int[][] board;
    public static boolean[][][] visited;
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
    public static int[][] rotateR = {{-1, 0, -1, 0}, {0, 0, 1, 1}};
    public static int[][] rotateC = {{0, 0, 1, 1}, {-1, 0, -1, 0}};
    public static int[][] checkR = {{0, 1, 0, 1}, {1, 1, -1, -1}};
    public static int[][] checkC = {{1, 1, -1, -1}, {0, 1, 0, 1}};

    public int solution(int[][] board) {
        n = board.length;
        this.board = board;
        visited = new boolean[n][n][2];

        return bfs();
    }

    public int bfs() {
        Queue<Robot> queue = new ArrayDeque<>();
        queue.add(new Robot(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Robot cur = queue.poll();

            if (isArrived(cur)) return cur.time;

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (!canMove(nr, nc, cur.dir)) continue;
                if (visited[nr][nc][cur.dir]) continue;

                visited[nr][nc][cur.dir] = true;
                queue.add(new Robot(nr, nc, cur.dir, cur.time + 1));
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + rotateR[cur.dir][i];
                int nc = cur.c + rotateC[cur.dir][i];
                int ndir = ~cur.dir & 1;

                if (!canMove(nr, nc, ndir)) continue;
                if (!canRotate(nr, nc, cur.dir, i)) continue;
                if (visited[nr][nc][ndir]) continue;

                visited[nr][nc][ndir] = true;
                queue.add(new Robot(nr, nc, ndir, cur.time + 1));
            }
        }

        return -1;
    }

    public boolean isArrived(Robot cur) {
        if (cur.dir == 0 && cur.r == n - 1 && cur.c + 1 == n - 1) return true;
        if (cur.dir == 1 && cur.r + 1 == n - 1 && cur.c == n - 1) return true;
        return false;
    }

    public boolean canMove(int nr, int nc, int dir) {
        if (nr < 0 || nr >= n || nc < 0 || nc >= n) return false;
        if (board[nr][nc] == 1) return false;
        if (dir == 0 && (nc + 1 >= n || board[nr][nc + 1] == 1)) return false;
        if (dir == 1 && (nr + 1 >= n || board[nr + 1][nc] == 1)) return false;
        return true;
    }

    public boolean canRotate(int nr, int nc, int dir, int i) {
        return board[nr + checkR[dir][i]][nc + checkC[dir][i]] == 0;
    }
}

class Robot {
    int r;
    int c;
    int dir;
    int time;

    public Robot(int r, int c, int dir, int time) {
        this.r = r;
        this.c = c;
        this.dir = dir;
        this.time = time;
    }
}