package level3.solution_92345;

class Solution_92345 {

    public static void main(String[] args) {

        Solution_92345 s = new Solution_92345();

        int[][][] board = {
                {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}},
                {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}},
                {{1, 1, 1, 1, 1}},
                {{1}}
        };
        int[][] aloc = {
                {1, 0},
                {1, 0},
                {0, 0},
                {0, 0}
        };
        int[][] bloc = {
                {1, 2},
                {1, 2},
                {0, 4},
                {0, 0}
        };
        int[] result = {5, 4, 4, 0};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(board[i], aloc[i], bloc[i]) == result[i]);
        }
    }

    public static int n;
    public static int m;
    public static int[][] board;
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        n = board.length;
        m = board[0].length;
        this.board = board;

        return dfs(new Node(aloc[0], aloc[1]), new Node(bloc[0], bloc[1]), 0).cnt;
    }

    public Match dfs(Node a, Node b, int cnt) {
        boolean won = false;
        if (board[a.r][a.c] == 0) return new Match(false, cnt);

        int winCnt = Integer.MAX_VALUE;
        int loseCnt = cnt;
        for (int i = 0; i < 4; i++) {
            int nr = a.r + dr[i];
            int nc = a.c + dc[i];

            if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
            if (board[nr][nc] == 0) continue;

            board[a.r][a.c] = 0;
            Match match = dfs(b, new Node(nr, nc), cnt + 1);
            board[a.r][a.c] = 1;

            won |= !match.won;
            if (!match.won) winCnt = Math.min(winCnt, match.cnt);
            else loseCnt = Math.max(loseCnt, match.cnt);
        }

        return new Match(won, won ? winCnt : loseCnt);
    }
}

class Match {
    boolean won;
    int cnt;

    public Match(boolean won, int cnt) {
        this.won = won;
        this.cnt = cnt;
    }
}

class Node {
    int r;
    int c;

    public Node(int r, int c) {
        this.r = r;
        this.c = c;
    }
}