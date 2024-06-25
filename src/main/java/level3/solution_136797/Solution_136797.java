package level3.solution_136797;

class Solution_136797 {

    public static void main(String[] args) {

        Solution_136797 s = new Solution_136797();

        String[] numbers = {"1756", "5123"};
        int[] result = {10, 8};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(numbers[i]) == result[i]);
        }
    }

    public int[][] cost = {
            {1, 7, 6, 7, 5, 4, 5, 3, 2, 3},
            {7, 1, 2, 4, 2, 3, 5, 4, 5, 6},
            {6, 2, 1, 2, 3, 2, 3, 5, 4, 5},
            {7, 4, 2, 1, 5, 3, 2, 6, 5, 4},
            {5, 2, 3, 5, 1, 2, 4, 2, 3, 5},
            {4, 3, 2, 3, 2, 1, 2, 3, 2, 3},
            {5, 5, 3, 2, 4, 2, 1, 5, 3, 2},
            {3, 4, 5, 6, 2, 3, 5, 1, 2, 4},
            {2, 5, 4, 5, 3, 2, 3, 2, 1, 2},
            {3, 6, 5, 4, 5, 3, 2, 4, 2, 1}
    };
    public int len;
    public char[] toPress;
    public int[][][] memo;

    public int solution(String numbers) {
        len = numbers.length();
        toPress = numbers.toCharArray();
        memo = new int[len][10][10];
        for (int i = 0; i < len; i++)
            for (int j = 0; j < 10; j++)
                for (int k = 0; k < 10; k++)
                    memo[i][j][k] = -1;

        return dfs(0, 4, 6);
    }

    public int dfs(int depth, int l, int r) {
        if (depth == len) return 0;
        if (memo[depth][l][r] != -1) return memo[depth][l][r];

        int now = toPress[depth] - '0';
        int nowCost = Integer.MAX_VALUE;
        if (now != r) nowCost = Math.min(nowCost, dfs(depth + 1, now, r) + cost[l][now]);
        if (now != l) nowCost = Math.min(nowCost, dfs(depth + 1, l, now) + cost[r][now]);

        return memo[depth][l][r] = nowCost;
    }
}