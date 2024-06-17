package level3.solution_258705;

class Solution_258705 {

    public static void main(String[] args) {

        Solution_258705 s = new Solution_258705();

        int[] n = {4, 2, 10};
        int[][] tops = {
                {1, 1, 0, 1},
                {0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int[] result = {149, 11, 7704};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(n[i], tops[i]) == result[i]);
        }
    }

    public int solution(int n, int[] tops) {
        int mod = 10007;

        int[][] memo = new int[n][2];
        memo[0][0] = tops[0] + 2;
        memo[0][1] = 1;

        for (int i = 1; i < n; i++) {
            memo[i][0] = (memo[i - 1][0] * (tops[i] + 2) + memo[i - 1][1] * (tops[i] + 1)) % mod;
            memo[i][1] = (memo[i - 1][0] + memo[i - 1][1]) % mod;
        }

        return (memo[n - 1][0] + memo[n - 1][1]) % mod;
    }
}