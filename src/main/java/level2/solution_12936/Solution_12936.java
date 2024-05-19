package level2.solution_12936;

import java.util.*;

class Solution_12936 {

    public static void main(String[] args) {

        Solution_12936 s = new Solution_12936();

        int[] n = {3, 20};
        long[] k = {5, 2432902008176640000L};
        int[][] result = {{3, 1, 2}, {20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1}};
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.equals(s.solution(n[i], k[i]), result[i]));
        }
    }

    public int[] solution(int n, long k) {

        long[] f = new long[n + 1];
        f[0] = 1;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < f.length - 1; i++) {
            f[i + 1] = f[i] * (i + 1);
            list.add(i + 1);
        }

        int[] result = new int[n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (k <= (i + 1) * f[n - 1]) {
                k -= i * f[n - 1];
                result[idx++] = list.get(i);
                list.remove(i);
                n--;
                i = -1;
            }
        }

        return result;
    }
}