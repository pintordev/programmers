package level3.solution_72415;

import java.util.LinkedList;
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

    public int solution(int[][] board, int r, int c) {
        this.board = board;

        cards = new Node[7][2];
        exists = new boolean[7];
        saveCardInfo();

        minMove = Integer.MAX_VALUE;
        dfs(0, 0, 0, 0);
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
            exists[i] = true;
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
            exists[i] = false;
        }
    }

    public int bfs(int sr, int sc, int er, int ec) {
        boolean[][] visited = new boolean[4][4];
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(sr, sc));
        visited[sr][sc] = true;

        int move = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node cur = q.poll();
                if (cur.r == er && cur.c == ec) return move;

                for (int j = 0; j < 4; j++) {
                    int nr = cur.r + dir[j][0];
                    int nc = cur.c + dir[j][1];

                    if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4) continue;
                    if (visited[nr][nc]) continue;

                    q.add(new Node(nr, nc));
                    visited[nr][nc] = true;
                }

                for (int j = 0; j < 4; j++) {
                    for (int k = 1; k <= 3; k++) {
                        int nr = cur.r + dir[j][0] * k;
                        int nc = cur.c + dir[j][1] * k;

                        if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4) continue;
                        if (visited[nr][nc]) continue;

                        q.add(new Node(nr, nc));
                        visited[nr][nc] = true;
                    }
                }
            }
            move++;
        }
        return -1;
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