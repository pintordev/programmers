package level2;

import java.util.LinkedList;
import java.util.Queue;

class Solution_169199 {

    public static void main(String[] args) {

        Solution_169199 s = new Solution_169199();

        String[][] board = {{"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."},
                {".D.R", "....", ".G..", "...D"}};
        int[] result = {7, -1};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(board[i]) == result[i]);
        }
    }

    public int solution(String[] board) {

        int[][] ds = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        char[][] map = new char[board.length][board[0].length()];
        int[] r = new int[2];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = board[i].charAt(j);
                if (map[i][j] == 'R') {
                    r[0] = i;
                    r[1] = j;
                }
            }
        }

        boolean[][] visited = new boolean[map.length][map[0].length];

        Queue<int[]> queue = new LinkedList<>();
        visited[r[0]][r[1]] = true;
        for (int[] d : ds) {
            if (!canGo(r[0] + d[0], r[1] + d[1], map)) continue;
            queue.add(new int[]{r[0] + d[0], r[1] + d[1], d[0], d[1], 1});
        }

        while (!queue.isEmpty()) {

            int[] now = queue.poll();

            int mx = now[0];
            int my = now[1];

            while (canGo(mx + now[2], my + now[3], map)) {
                mx += now[2];
                my += now[3];
            }

            if (map[mx][my] == 'G') return now[4];

            if (visited[mx][my]) continue;
            visited[mx][my] = true;
            for (int[] d : ds) {
                if (!canGo(mx + d[0], my + d[1], map)) continue;
                if (now[2] * -1 == d[0] && now[3] * -1 == d[1]) continue;
                queue.add(new int[]{mx + d[0], my + d[1], d[0], d[1], now[4] + 1});
            }
        }

        return -1;
    }

    public boolean canGo(int x, int y, char[][] map) {
        if (x < 0 || x >= map.length || y < 0 || y >= map[0].length) return false;
        if (map[x][y] == 'D') return false;
        return true;
    }
}