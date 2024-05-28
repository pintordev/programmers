package level3.solution_92343;

import java.util.ArrayList;
import java.util.List;

class Solution_92343 {

    public static void main(String[] args) {

        Solution_92343 s = new Solution_92343();

        int[][] info = {{0,0,1,1,1,0,1,0,1,0,1,1},
                {0,1,0,1,1,0,1,0,0,1,0}};
        int[][][] edges = {{{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}},
                {{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{3,7},{4,8},{6,9},{9,10}}};
        int[] result = {5, 5};

        int t = info.length;
        for (int i = 0; i < t; i++) {
            System.out.println(s.solution(info[i], edges[i]) == result[i]);
        }
    }

    public static int n;
    public static int[] info;
    public static List<Integer>[] graph;
    public static boolean[] visited;
    public static int maxSheep;

    public int solution(int[] info, int[][] edges) {
        n = info.length;
        this.info = info;
        graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] edge : edges) graph[edge[0]].add(edge[1]);
        visited = new boolean[1 << n];

        dfs(1, 1, 0);
        return maxSheep;
    }

    public void dfs(int bitMask, int sheep, int wolf) {
        visited[bitMask] = true;
        maxSheep = Math.max(maxSheep, sheep);

        for (int i = 0; i < n; i++) {
            if ((bitMask & (1 << i)) == 0) continue;
            for (int next : graph[i]) {
                int nBitMask = bitMask | (1 << next);
                int nSheep = info[next] == 0 ? sheep + 1 : sheep;
                int nWolf = info[next] == 1 ? wolf + 1 : wolf;
                if (nSheep > nWolf && !visited[nBitMask]) {
                    dfs(nBitMask, nSheep, nWolf);
                }
            }
        }
    }
}