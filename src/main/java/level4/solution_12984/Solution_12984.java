package level4.solution_12984;

import java.util.Arrays;

class Solution_12984 {

    public static void main(String[] args) {

        Solution_12984 s = new Solution_12984();

        int[][][] land = {
                {{1, 2}, {2, 3}},
                {{4, 4, 3}, {3, 2, 2}, {2, 1, 0}}
        };
        int[] P = {3, 5};
        int[] Q = {2, 3};
        long[] result = {5, 33};

        System.out.println("===== solution 1 =====");
        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(land[i], P[i], Q[i]) == result[i]);
        }

        System.out.println("===== solution 2 =====");
        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution2(land[i], P[i], Q[i]) == result[i]);
        }
    }

    public static int n;
    public static int[][] land;
    public static int P;
    public static int Q;

    public long solution(int[][] land, int P, int Q) {
        n = land.length;
        this.land = land;
        this.P = P;
        this.Q = Q;

        long low = 1_000_000_000L;
        long high = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                low = Math.min(low, land[i][j]);
                high = Math.max(high, land[i][j]);
            }
        }

        low--;
        long result = 0;
        while (low + 1 < high) {
            long mid = (low + high) >> 1;

            long left = cal(mid);
            long right = cal(mid + 1);
            if (left > right) low = mid;
            else high = mid;

            result = Math.min(left, right);
        }
        return result;
    }

    public long cal(long h) {
        long cost = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] > h) cost += (land[i][j] - h) * Q;
                else cost += (h - land[i][j]) * P;
            }
        }
        return cost;
    }

    public long solution2(int[][] land, int P, int Q) {
        int n = land.length;
        int[] block = new int[n * n];
        long total = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                block[i * n + j] = land[i][j];
                total += land[i][j];
            }
        }

        Arrays.sort(block);
        long cost = Long.MAX_VALUE;
        long cumSum = 0;
        for (int i = 0, len = n * n; i < len; i++) {
            cumSum += block[i];
            long add = (long) (i + 1) * block[i] - cumSum;
            long del = total - cumSum - (long) (len - i - 1) * block[i];
            long nCost = add * P + del * Q;
            if (cost >= nCost) cost = nCost;
            else return cost;
        }
        return cost;
    }
}