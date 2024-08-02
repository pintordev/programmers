package level4.solution_1839;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution_1839 {

    public static void main(String[] args) {

        Solution_1839 s = new Solution_1839();

        int[] m = {3, 4, 5};
        int[] n = {3, 6, 5};
        int[] _s = {150, 25, 12};
        int[][][] time_map = {
                {{0, 2, 99}, {100, 100, 4}, {1, 2, 0}},
                {{0, 1, 1, -1, 2, 4}, {-1, 7, 2, 1, 5, 7}, {-1, 1, -1, 1, 6, 3}, {-1, 1, -1, -1, 7, 0}},
                {{0, 1, 1, 1, 1}, {9, 9, 9, 1, 9}, {1, 1, 1, 1, 9}, {1, 1, 5, 9, 9}, {1, 1, 1, 1, 0}}
        };
        int[][] result = {
                {4, 103},
                {8, 15},
                {12, 11}
        };

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(Arrays.equals(s.solution(m[i], n[i], _s[i], time_map[i]), result[i]));
        }
    }

    public static int[] dr = {0, 0, 1, -1};
    public static int[] dc = {1, -1, 0, 0};

    public int[] solution(int m, int n, int s, int[][] time_map) {
        long[][] time = new long[m][n];
        for (long[] t : time) {
            Arrays.fill(t, Long.MAX_VALUE);
        }
        time[0][0] = 0;

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0, 0));
        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.r == m - 1 && cur.c == n - 1) {
                return new int[]{cur.d, (int) time[cur.r][cur.c]};
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                if (time_map[nr][nc] == -1) continue;

                long nTime = time[cur.r][cur.c] + time_map[nr][nc];
                if (nTime > s) continue;
                if (time[nr][nc] <= nTime) continue;

                time[nr][nc] = nTime;
                q.add(new Node(nr, nc, cur.d + 1));
            }
        }
        return null;
    }
}

class Node {
    int r;
    int c;
    int d;

    public Node(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }
}