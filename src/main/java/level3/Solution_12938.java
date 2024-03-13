package level3;

import java.util.Arrays;

class Solution_12938 {

    public static void main(String[] args) {

        Solution_12938 s = new Solution_12938();

        int[] n = {2, 2, 2, 3};
        int[] _s = {9, 1, 8, 8};
        int[][] result = {{4, 5},
                {-1},
                {4, 4},
                {2, 3, 3}};
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.equals(s.solution(n[i], _s[i]), result[i]));
        }
    }

    public int[] solution(int n, int s) {

        if (s < n) return new int[]{-1};

        int[] set = new int[n];
        int q = s / n, r = s % n;
        for (int i = n - 1; i >= 0; i--) {
            set[i] += q + (r-- > 0 ? 1 : 0);
        }

        return set;
    }
}