package level1;

import java.util.Arrays;

class Solution_12940 {

    public static void main(String[] args) {

        Solution_12940 s = new Solution_12940();

        int[] n = {3,2};
        int[] m = {12,5};
        int[][] result = {{3,12}, {1,10}};
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.equals(s.solution(n[i], m[i]), result[i]));
        }
    }

    public int[] solution(int n, int m) {

        int count = 1;
        while (m * count % n != 0) {
            count++;
        }

        return new int[]{n / count, m * count};
    }
}