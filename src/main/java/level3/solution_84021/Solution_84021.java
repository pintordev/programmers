package level3.solution_84021;

import java.util.*;

class Solution_84021 {

    public static void main(String[] args) {

        Solution_84021 s = new Solution_84021();

        int[][][] game_board = {
                {{1, 1, 0, 0, 1, 0}, {0, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 0, 1}, {1, 1, 0, 1, 1, 1}, {1, 0, 0, 0, 1, 0}, {0, 1, 1, 1, 0, 0}},
                {{0, 0, 0}, {1, 1, 0}, {1, 1, 1}}
        };
        int[][][] table = {
                {{1, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 1, 1}, {0, 0, 1, 0, 0, 0}, {1, 1, 0, 1, 1, 0}, {0, 1, 0, 0, 0, 0}},
                {{1, 1, 1}, {1, 0, 0}, {0, 0, 0}}
        };
        int[] result = {14, 0};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(game_board[i], table[i]) == result[i]);
        }
    }

    public static int n;
    public static List<Block> gameBlocks;
    public static List<Block> tableBlocks;
    public static int[] dr = {0, 1, 0, -1};
    public static int[] dc = {1, 0, -1, 0};

    public int solution(int[][] game_board, int[][] table) {
        n = game_board.length;
        gameBlocks = new ArrayList<>();
        tableBlocks = new ArrayList<>();
        boolean[][] gameVisited = new boolean[n][n];
        boolean[][] tableVisited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (game_board[i][j] == 0 && !gameVisited[i][j]) {
                    bfs(i, j, game_board, gameBlocks, gameVisited, 0);
                }
                if (table[i][j] == 1 && !tableVisited[i][j]) {
                    bfs(i, j, table, tableBlocks, tableVisited, 1);
                }
            }
        }

        return compare();
    }

    public void bfs(int sr, int sc, int[][] board, List<Block> blocks, boolean[][] visited, int checker) {
        List<Node> nodes = new ArrayList<>();

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(sr, sc));
        visited[sr][sc] = true;
        nodes.add(new Node(0, 0));
        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                if (visited[nr][nc] || board[nr][nc] != checker) continue;

                q.add(new Node(nr, nc));
                visited[nr][nc] = true;
                nodes.add(new Node(nr - sr, nc - sc));
            }
        }

        blocks.add(new Block(nodes));
    }

    public int compare() {
        int m = gameBlocks.size();
        boolean[] visited = new boolean[m];

        int cnt = 0;
        for (Block tableBlock : tableBlocks) {
            int idx = 0;
            for (Block gameBlock : gameBlocks) {
                if (visited[idx++]) continue;
                if (!gameBlock.matched(tableBlock)) continue;
                visited[--idx] = true;
                cnt += gameBlock.size();
                break;
            }
        }
        return cnt;
    }
}

class Block {
    List<Node> nodes;

    public Block(List<Node> nodes) {
        Collections.sort(nodes);
        this.nodes = nodes;
    }

    public int size() {
        return nodes.size();
    }

    public boolean matched(Block o) {
        if (equals(o)) return true;
        for (int i = 0; i < 3; i++) {
            rotate();
            if (equals(o)) return true;
        }
        return false;
    }

    public void rotate() {
        for (Node node : nodes) {
            int temp = node.r;
            node.r = node.c;
            node.c = -temp;
        }

        Collections.sort(nodes);

        int sr = nodes.get(0).r;
        int sc = nodes.get(0).c;
        for (Node node : nodes) {
            node.r -= sr;
            node.c -= sc;
        }
    }

    public boolean equals(Block o) {
        if (nodes.size() != o.size()) return false;
        for (int i = 0; i < nodes.size(); i++) {
            if (!nodes.get(i).equals(o.nodes.get(i))) return false;
        }
        return true;
    }
}

class Node implements Comparable<Node> {
    int r;
    int c;

    public Node(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public int compareTo(Node o) {
        if (this.r == o.r) return this.c - o.c;
        return this.r - o.r;
    }

    public boolean equals(Node o) {
        return this.r == o.r && this.c == o.c;
    }
}