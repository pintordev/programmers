package level4.solution_12929;

class Solution_12929 {

    public static void main(String[] args) {

        Solution_12929 s = new Solution_12929();

        int[] n = {2, 3};
        int[] result = {2, 5};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(n[i]) == result[i]);
            System.out.println(s.solution2(n[i]) == result[i]);
        }
    }

    public int solution(int n) {
        int[] memo = new int[n + 1];
        memo[0] = 1;
        memo[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                memo[i] += memo[i - j] * memo[j - 1];
            }
        }
        return memo[n];
    }

    public static int cnt;

    public int solution2(int n) {
        cnt = 0;
        dfs(2 * n, 1, 1);
        return cnt;
    }

    public void dfs(int limit, int depth, int sum) {
        if (sum < 0) return;
        if (depth == limit) {
            if (sum == 0) cnt++;
            return;
        }
        dfs(limit, depth + 1, sum + 1);
        dfs(limit, depth + 1, sum - 1);
    }
}