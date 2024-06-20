package level3.solution_87391;

class Solution_87391 {

    public static void main(String[] args) {

        Solution_87391 s = new Solution_87391();

        int[] n = {2, 2};
        int[] m = {2, 5};
        int[] x = {0, 0};
        int[] y = {0, 1};
        int[][][] queries = {
                {{2, 1}, {0, 1}, {1, 1}, {0, 1}, {2, 1}},
                {{3, 1}, {2, 2}, {1, 1}, {2, 3}, {0, 1}, {2, 1}}
        };
        long[] result = {4, 2};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(n[i], m[i], x[i], y[i], queries[i]) == result[i]);
        }
    }

    public long solution(int n, int m, int x, int y, int[][] queries) {
        long r1 = x;
        long r2 = x;
        long c1 = y;
        long c2 = y;

        for (int i = queries.length - 1; i >= 0; i--) {
            int dir = queries[i][0];
            int dist = queries[i][1];

            switch (dir) {
                case 0:
                    if (c1 != 0) c1 += dist;
                    c2 = Math.min(m - 1, c2 + dist);
                    break;
                case 1:
                    if (c2 != m - 1) c2 -= dist;
                    c1 = Math.max(0, c1 - dist);
                    break;
                case 2:
                    if (r1 != 0) r1 += dist;
                    r2 = Math.min(n - 1, r2 + dist);
                    break;
                case 3:
                    if (r2 != n - 1) r2 -= dist;
                    r1 = Math.max(0, r1 - dist);
                    break;
            }

            if (c1 > c2 || r1 > r2) return 0;
        }

        return (r2 - r1 + 1) * (c2 - c1 + 1);
    }
}