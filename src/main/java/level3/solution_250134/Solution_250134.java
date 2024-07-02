package level3.solution_250134;

class Solution_250134 {

    public static void main(String[] args) {

        Solution_250134 s = new Solution_250134();

        int[][][] maze = {
                {{1, 4}, {0, 0}, {2, 3}},
                {{1, 0, 2}, {0, 0, 0}, {5, 0, 5}, {4, 0, 3}},
                {{1, 5}, {2, 5}, {4, 5}, {3, 5}},
                {{4, 1, 2, 3}}
        };
        int[] result = {3, 7, 0, 0};
        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(maze[i]) == result[i]);
        }
    }

    public static int n;
    public static int m;
    public static int[][] maze;
    public static boolean[][][] visited;
    public static int[] dr = {0, 1, 0, -1};
    public static int[] dc = {1, 0, -1, 0};
    public static int cnt;

    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        this.maze = maze;
        visited = new boolean[n][m][2];

        int srr = 0;
        int src = 0;
        int sbr = 0;
        int sbc = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == 1) {
                    srr = i;
                    src = j;
                } else if (maze[i][j] == 2) {
                    sbr = i;
                    sbc = j;
                }
            }
        }

        visited[srr][src][0] = true;
        visited[sbr][sbc][1] = true;
        cnt = Integer.MAX_VALUE;
        dfs(new Maze(srr, src, sbr, sbc, 0));
        return cnt == Integer.MAX_VALUE ? 0 : cnt;
    }

    public void dfs(Maze cur) {
        if (cur.cnt >= cnt) return;

        boolean red = maze[cur.rr][cur.rc] == 3;
        boolean blue = maze[cur.br][cur.bc] == 4;

        if (red && blue) {
            cnt = Math.min(cnt, cur.cnt);
        }
        else if (!red && !blue) {
            for (int i = 0; i < 4; i++) {
                int nrr = cur.rr + dr[i];
                int nrc = cur.rc + dc[i];

                if (!canMove(nrr, nrc, 0)) continue;

                for (int j = 0; j < 4; j++) {
                    int nbr = cur.br + dr[j];
                    int nbc = cur.bc + dc[j];

                    if (!canMove(nbr, nbc, 1)) continue;
                    if (cur.rr == nbr && cur.rc == nbc && cur.br == nrr && cur.bc == nrc) continue;
                    if (nrr == nbr && nrc == nbc) continue;

                    visited[nrr][nrc][0] = true;
                    visited[nbr][nbc][1] = true;
                    dfs(new Maze(nrr, nrc, nbr, nbc, cur.cnt + 1));
                    visited[nrr][nrc][0] = false;
                    visited[nbr][nbc][1] = false;
                }
            }
        }
        else if (red && !blue) {
            for (int j = 0; j < 4; j++) {
                int nbr = cur.br + dr[j];
                int nbc = cur.bc + dc[j];

                if (!canMove(nbr, nbc, 1)) continue;
                if (cur.rr == nbr && cur.rc == nbc) continue;

                visited[nbr][nbc][1] = true;
                dfs(new Maze(cur.rr, cur.rc, nbr, nbc, cur.cnt + 1));
                visited[nbr][nbc][1] = false;
            }
        }
        else if (!red && blue) {
            for (int i = 0; i < 4; i++) {
                int nrr = cur.rr + dr[i];
                int nrc = cur.rc + dc[i];

                if (!canMove(nrr, nrc, 0)) continue;
                if (cur.br == nrr && cur.bc == nrc) continue;

                visited[nrr][nrc][0] = true;
                dfs(new Maze(nrr, nrc, cur.br, cur.bc, cur.cnt + 1));
                visited[nrr][nrc][0] = false;
            }
        }
    }

    public boolean canMove(int nr, int nc, int color) {
        if (nr < 0 || nr >= n || nc < 0 || nc >= m) return false;
        if (maze[nr][nc] == 5) return false;
        if (visited[nr][nc][color]) return false;
        return true;
    }
}

class Maze {
    int rr;
    int rc;
    int br;
    int bc;
    int cnt;

    public Maze(int rr, int rc, int br, int bc, int cnt) {
        this.rr = rr;
        this.rc = rc;
        this.br = br;
        this.bc = bc;
        this.cnt = cnt;
    }
}