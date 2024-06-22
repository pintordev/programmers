package level3.solution_72415;

import java.util.ArrayDeque;
import java.util.Queue;

class Solution_72415 {

    public static void main(String[] args) {

        Solution_72415 s = new Solution_72415();

        int[][][] board = {
                {{1, 0, 0, 3}, {2, 0, 0, 0}, {0, 0, 0, 2}, {3, 0, 1, 0}},
                {{3, 0, 0, 2}, {0, 0, 1, 0}, {0, 1, 0, 0}, {2, 0, 0, 3}}
        };
        int[] r = {1, 0};
        int[] c = {0, 1};
        int[] result = {14, 16};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(board[i], r[i], c[i]) == result[i]);
        }
    }

    public static int[][] board;
    public static Node[][] cards;
    public static boolean[] exists;
    public static int cardTypes;
    public static int minMove;
    public static boolean[][] visited;
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    public int solution(int[][] board, int r, int c) {
        this.board = board;

        cards = new Node[7][2];
        exists = new boolean[7];
        cardTypes = 0;
        saveCardInfo();

        minMove = Integer.MAX_VALUE;
        dfs(0, 0, r, c);
        return minMove;
    }

    public void saveCardInfo() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int num = board[i][j];
                if (num == 0) continue;
                if (cards[num][0] == null) cards[num][0] = new Node(i, j);
                else cards[num][1] = new Node(i, j);
                exists[num] = true;
                cardTypes++;
            }
        }
        cardTypes >>= 1;
    }

    public void dfs(int num, int depth, int r, int c) {
        if (depth == cardTypes) {
            minMove = Math.min(minMove, num);
            return;
        }

        for (int i = 1; i <= cardTypes; i++) {
            if (!exists[i]) continue;
            exists[i] = false;
            for (int j = 0; j < 2; j++) {
                Node card1 = cards[i][j];
                Node card2 = cards[i][(j + 1) % 2];

                int mv = bfs(r, c, card1.r, card1.c)
                        + bfs(card1.r, card1.c, card2.r, card2.c)
                        + 2;

                board[card1.r][card1.c] = 0;
                board[card2.r][card2.c] = 0;

                dfs(num + mv, depth + 1, card2.r, card2.c);

                board[card1.r][card1.c] = i;
                board[card2.r][card2.c] = i;
            }
            exists[i] = true;
        }
    }

    public int bfs(int sr, int sc, int er, int ec) {
        visited = new boolean[4][4];

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(sr, sc, 0));
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.r == er && cur.c == ec) return cur.mv;

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4) continue;
                if (visited[nr][nc]) continue;

                q.add(new Node(nr, nc, cur.mv + 1));
                visited[nr][nc] = true;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.r;
                int nc = cur.c;

                while (true) {
                    nr += dr[i];
                    nc += dc[i];

                    if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4) {
                        nr -= dr[i];
                        nc -= dc[i];
                        break;
                    }

                    if (board[nr][nc] != 0) break;
                }

                if (visited[nr][nc]) continue;

                q.add(new Node(nr, nc, cur.mv + 1));
                visited[nr][nc] = true;
            }
        }

        return -1;
    }
}

class Node {
    int r;
    int c;
    int mv;

    public Node(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public Node(int r, int c, int mv) {
        this.r = r;
        this.c = c;
        this.mv = mv;
    }
}