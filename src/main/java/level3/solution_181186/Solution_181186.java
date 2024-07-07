package level3.solution_181186;

class Solution_181186 {

    public static void main(String[] args) {

        Solution_181186 s = new Solution_181186();

        int[] n = {2, 3};
        int[] result = {3, 10};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(n[i]) == result[i]);
            System.out.println(s.solution2(n[i]) == result[i]);
        }
    }

    public int solution(int n) {
        int mod = 1_000_000_007;
        long[] memo = new long[n + 1];
        long[] sub = new long[n + 1];

        memo[0] = 1;
        if (n == 1) return 1;
        if (n == 2) return 3;
        if (n == 3) return 10;

        memo[1] = 1;
        memo[2] = 3;
        memo[3] = 10;
        for (int i = 4; i <= n; i++) {
            sub[(i - 4) % 3] = (sub[(i - 4) % 3] + memo[i - 4]) % mod;
            memo[i] = (memo[i - 1] + 2 * memo[i - 2] + 5 * memo[i - 3]
                    + 2 * (sub[0] + sub[1] + sub[2] + sub[i % 3])) % mod;
        }
        return (int) memo[n];
    }

    public int solution2(int n) {
        int mod = 1_000_000_007;
        long[] memo = new long[n + 1];

        memo[0] = 1;
        if (n == 1) return 1;
        if (n == 2) return 3;
        if (n == 3) return 10;
        if (n == 4) return 23;
        if (n == 5) return 62;

        memo[1] = 1;
        memo[2] = 3;
        memo[3] = 10;
        memo[4] = 23;
        memo[5] = 62;
        for (int i = 6; i <= n; i++) {
            memo[i] = (memo[i - 1] + 2 * memo[i - 2] + 6 * memo[i - 3]
                    + memo[i - 4] - memo[i - 6] + mod) % mod;
        }
        return (int) memo[n];
    }
}