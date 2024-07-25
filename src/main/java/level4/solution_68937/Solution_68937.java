package level4.solution_68937;

import java.util.ArrayList;
import java.util.List;

class Solution_68937 {

    public static void main(String[] args) {

        Solution_68937 s = new Solution_68937();

        int[] n = {4, 5};
        int[][][] edges = {
                {{1, 2}, {2, 3}, {3, 4}},
                {{1, 5}, {2, 5}, {3, 5}, {4, 5}}
        };
        int[] result = {2, 2};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(n[i], edges[i]) == result[i]);
        }
    }

    public static int n;
    public static List<Integer>[] graph;
    public static boolean[] visited;
    public static int max;
    public static int mdx;
    public static int cnt;

    public int solution(int n, int[][] edges) {
        this.n = n;

        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        run(1);

        run(mdx);
        if (cnt > 1) return max;

        run(mdx);
        return cnt > 1 ? max : max - 1;
    }

    public void run(int s) {
        visited = new boolean[n + 1];
        max = -1;
        dfs(s, 0);
    }

    public void dfs(int idx, int depth) {
        visited[idx] = true;
        if (max == depth) cnt++;
        if (max < depth) {
            max = depth;
            mdx = idx;
            cnt = 1;
        }

        for (int next : graph[idx]) {
            if (visited[next]) continue;
            dfs(next, depth + 1);
        }
    }
}