package level3.solution_133500;

import java.util.ArrayList;
import java.util.List;

class Solution_133500 {

    public static void main(String[] args) {

        Solution_133500 s = new Solution_133500();

        int[] n = {8, 10};
        int[][][] lighthouse = {
                {{1, 2}, {1, 3}, {1, 4}, {1, 5}, {5, 6}, {5, 7}, {5, 8}},
                {{4, 1}, {5, 1}, {5, 6}, {7, 6}, {1, 2}, {1, 3}, {6, 8}, {2, 9}, {9, 10}}
        };
        int[] result = {2, 3};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(n[i], lighthouse[i]) == result[i]);
        }
    }

    public static List<Integer>[] graph;
    public static int[][] memo;
    public static boolean[] visited;

    public int solution(int n, int[][] lighthouse) {
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] lh : lighthouse) {
            graph[lh[0]].add(lh[1]);
            graph[lh[1]].add(lh[0]);
        }

        memo = new int[n + 1][2];
        visited = new boolean[n + 1];
        dfs(1);
        return Math.min(memo[1][0], memo[1][1]);
    }

    public void dfs(int n) {
        visited[n] = true;
        memo[n][1] = 1;
        for (int next : graph[n]) {
            if (visited[next]) continue;
            dfs(next);
            memo[n][0] += memo[next][1];
            memo[n][1] += Math.min(memo[next][0], memo[next][1]);
        }
    }
}