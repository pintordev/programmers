package level3.solution_86053;

class Solution_86053 {

    public static void main(String[] args) {

        Solution_86053 s = new Solution_86053();

        int[] a = {10, 90};
        int[] b = {10, 500};
        int[][] g = {
                {100},
                {70, 70, 0}
        };
        int[][] _s = {
                {100},
                {0, 0, 500}
        };
        int[][] w = {
                {7},
                {100, 100, 2}
        };
        int[][] _t = {
                {10},
                {4, 8, 1}
        };
        long[] result = {50, 499};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(a[i], b[i], g[i], _s[i], w[i], _t[i]) == result[i]);
        }
    }

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long low = 0;
        long high = (a + b) * 2L * 100_000;
        while (low + 1 < high) {
            long mid = (low + high) >> 1;

            long gold = 0;
            long silver = 0;
            long carried = 0;
            for (int i = 0, n = t.length; i < n; i++) {
                long subCarried = Math.round((double) mid / t[i] / 2) * w[i];
                gold += Math.min(g[i], subCarried);
                silver += Math.min(s[i], subCarried);
                carried += Math.min(g[i] + s[i], subCarried);
            }

            if (a <= gold && b <= silver && a + b <= carried) high = mid;
            else low = mid;
        }
        return high;
    }
}