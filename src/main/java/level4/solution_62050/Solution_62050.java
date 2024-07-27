package level4.solution_62050;

import java.util.PriorityQueue;

class Solution_62050 {

    public static void main(String[] args) {

        Solution_62050 s = new Solution_62050();

        int[][][] land = {
                {{1, 4, 8, 10}, {5, 5, 5, 5}, {10, 10, 10, 10}, {10, 10, 10, 20}},
                {{10, 11, 10, 11}, {2, 21, 20, 10}, {1, 20, 21, 11}, {2, 1, 2, 1}}
        };
        int[] height = {3, 1};
        int[] result = {15, 18};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(land[i], height[i]) == result[i]);
        }
    }

    public static int[] dr = {0, 0, 1, -1};
    public static int[] dc = {1, -1, 0, 0};

    public int solution(int[][] land, int height) {
        int n = land.length;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0, 0));

        boolean[][] visited = new boolean[n][n];
        int cost = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visited[cur.r][cur.c]) continue;

            visited[cur.r][cur.c] = true;
            cost += cur.cost;

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                if (visited[nr][nc]) continue;

                int diff = Math.abs(land[cur.r][cur.c] - land[nr][nc]);
                if (diff > height) pq.add(new Node(nr, nc, diff));
                else pq.add(new Node(nr, nc, 0));
            }
        }
        return cost;
    }
}

class Node implements Comparable<Node> {
    int r;
    int c;
    int cost;

    public Node(int r, int c, int cost) {
        this.r = r;
        this.c = c;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}