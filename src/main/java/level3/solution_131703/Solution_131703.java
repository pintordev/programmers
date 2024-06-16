package level3.solution_131703;

class Solution_131703 {

    public static void main(String[] args) {

        Solution_131703 s = new Solution_131703();

        int[][][] beginning = {{{0, 1, 0, 0, 0}, {1, 0, 1, 0, 1}, {0, 1, 1, 1, 0}, {1, 0, 1, 1, 0}, {0, 1, 0, 1, 0}},
                {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}};
        int[][][] target = {{{0, 0, 0, 1, 1}, {0, 0, 0, 0, 1}, {0, 0, 1, 0, 1}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}},
                {{1, 0, 1}, {0, 0, 0}, {0, 0, 0}}};
        int[] result = {5, -1};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(beginning[i], target[i]) == result[i]);
        }
    }

    public static int m;
    public static int n;
    public static int[][] beginning;
    public static int[][] target;
    public static int[][] now;

    public int solution(int[][] beginning, int[][] target) {
        this.m = beginning.length;
        this.n = beginning[0].length;
        this.beginning = beginning;
        this.target = target;

        int min = Integer.MAX_VALUE;
        L: for (int mask = 0; mask < 1 << m; mask++) {
            deepClone();

            int cnt = 0;
            for (int i = 0; i < m; i++) {
                if ((mask & 1 << i) <= 0) continue;
                flipRow(i);
                cnt++;
            }

            for (int j = 0; j < n; j++) {
                int f = canSame(j);
                if (f < 0) continue L;
                cnt += f;
            }

            min = Math.min(min, cnt);
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public int canSame(int j) {
        if (isSame(j)) return 0;
        flipCol(j);
        if (isSame(j)) return 1;
        return -1;
    }

    public boolean isSame(int j) {
        for (int i = 0; i < m; i++) {
            if (now[i][j] != target[i][j]) return false;
        }
        return true;
    }

    public void deepClone() {
        now = new int[m][n];
        for (int i = 0; i < m; i++) {
            now[i] = beginning[i].clone();
        }
    }

    public void flipRow(int i) {
        for (int col = 0; col < n; col++) {
            now[i][col] ^= 1;
        }
    }

    public void flipCol(int j) {
        for (int row = 0; row < m; row++) {
            now[row][j] ^= 1;
        }
    }
}