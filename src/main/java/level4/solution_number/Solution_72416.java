package level4.solution_number;

import java.util.ArrayList;
import java.util.List;

class Solution_72416 {

    public static void main(String[] args) {

        Solution_72416 s = new Solution_72416();

        int[][] sales = {
                {14, 17, 15, 18, 19, 14, 13, 16, 28, 17},
                {5, 6, 5, 3, 4},
                {5, 6, 5, 1, 4},
                {10, 10, 1, 1}
        };
        int[][][] links = {
                {{10, 8}, {1, 9}, {9, 7}, {5, 4}, {1, 5}, {5, 10}, {10, 6}, {1, 3}, {10, 2}},
                {{2, 3}, {1, 4}, {2, 5}, {1, 2}},
                {{2, 3}, {1, 4}, {2, 5}, {1, 2}},
                {{3, 2}, {4, 3}, {1, 4}}
        };
        int[] result = {44, 6, 5, 2};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(sales[i], links[i]) == result[i]);
        }
    }

    public static int n;
    public static List<Integer>[] tree;
    public static int[] sales;
    public static int[][] memo;

    public int solution(int[] sales, int[][] links) {
        n = sales.length;
        tree = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int[] link : links) {
            tree[link[0]].add(link[1]);
        }
        this.sales = sales;

        memo = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            memo[i][0] = -1;
            memo[i][1] = -1;
        }
        return Math.min(dfs(1, 0), dfs(1, 1));
    }

    public int dfs(int node, int pt) {
        if (memo[node][pt] != -1) return memo[node][pt];

        int sum = 0;
        int minDiff = Integer.MAX_VALUE;
        boolean childPt = false;
        for (int child : tree[node]) {
            int wPt = dfs(child, 0);
            int woPt = dfs(child, 1);

            if (wPt < woPt) {
                sum += wPt;
                minDiff = Math.min(minDiff, woPt - wPt);
            } else {
                sum += woPt;
                childPt = true;
            }
        }

        if (pt == 1) sum += sales[node - 1];
        else if (!childPt && minDiff != Integer.MAX_VALUE) sum += minDiff;

        return memo[node][pt] = sum;
    }
}