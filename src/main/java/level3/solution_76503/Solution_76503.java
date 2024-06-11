package level3.solution_76503;

import java.util.ArrayList;
import java.util.List;

class Solution_76503 {

    public static void main(String[] args) {

        Solution_76503 s = new Solution_76503();

        int[][] a = {{-5, 0, 2, 1, 2},
                {0, 1, 0}};
        int[][][] edges = {{{0, 1}, {3, 4}, {2, 3}, {0, 3}},
                {{0, 1}, {1, 2}}};
        int[] result = {9, -1};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(a[i], edges[i]) == result[i]);
        }
    }

    public static long cnt;
    public static List<Integer>[] graph;
    public static boolean[] visited;
    public static long[] a;

    public long solution(int[] a, int[][] edges) {
        cnt = 0;
        int n = a.length;
        graph = new List[n];
        visited = new boolean[n];
        this.a = new long[n];

        long sum = 0;
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            this.a[i] = a[i];
            sum += a[i];
        }

        if (sum != 0) return -1;

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        dfs(0);

        return cnt;
    }

    public void dfs(int node) {
        visited[node] = true;

        for (int i = 0, n = graph[node].size(); i < n; i++) {
            int next = graph[node].get(i);
            if (visited[next]) continue;
            dfs(next);
            a[node] += a[next];
        }

        cnt += Math.abs(a[node]);
    }
}