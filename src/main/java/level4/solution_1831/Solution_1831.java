package level4.solution_1831;

class Solution_1831 {

    public static void main(String[] args) {

        Solution_1831 s = new Solution_1831();

        int[] n = {15, 24, 41, 2147483647};
        int[] result = {1, 0, 2, 1735};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(n[i]) == result[i]);
        }
    }

    public static int result;

    public int solution(int n) {
        result = 0;
        dfs(n - 2, 2);
        return result;
    }

    public void dfs(int n, int p) {
        if (n < 1 || log(3, n) < p / 2) return;
        if (n == 1 && p == 0) {
            result++;
            return;
        }
        if (n % 3 == 0 && p >= 2) dfs(n / 3, p - 2);
        dfs(n - 1, p + 1);
    }

    public int log(int base, int exp) {
        return (int) (Math.log(exp) / Math.log(base));
    }
}