package level3.solution_1837;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution_1837 {

    public static void main(String[] args) {

        Solution_1837 s = new Solution_1837();

        int[] n = {7, 7};
        int[] m = {10, 10};
        int[][][] edge_list = {
                {{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}},
                {{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}}
        };
        int[] k = {6, 6};
        int[][] gps_log = {
                {1, 2, 3, 3, 6, 7},
                {1, 2, 4, 6, 5, 7}
        };
        int[] result = {1, 0};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(n[i], m[i], edge_list[i], k[i], gps_log[i]) == result[i]);
        }
    }

    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edge_list) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int[][] memo = new int[k][n + 1];
        for (int[] me : memo) {
            Arrays.fill(me, Integer.MAX_VALUE);
        }
        memo[0][gps_log[0]] = 0;
        for (int i = 1; i < k; i++) {
            for (int j = 1; j <= n; j++) {
                if (memo[i - 1][j] == Integer.MAX_VALUE) continue;
                for (int next : graph[j]) {
                    int cost = memo[i - 1][j] + (gps_log[i] == next ? 0 : 1);
                    memo[i][next] = Math.min(memo[i][next], cost);
                }
            }
        }
        return memo[k - 1][gps_log[k - 1]] == Integer.MAX_VALUE ? -1 : memo[k - 1][gps_log[k - 1]];
    }
}