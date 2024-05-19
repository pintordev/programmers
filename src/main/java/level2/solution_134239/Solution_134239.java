package level2.solution_134239;

import java.util.*;

class Solution_134239 {

    public static void main(String[] args) {

        Solution_134239 s = new Solution_134239();

        int[] k = {5, 3};
        int[][][] ranges = {{{0, 0}, {0, -1}, {2, -3}, {3, -3}},
                {{0, 0}, {1, -2}, {3, -3}}};
        double[][] result = {{33.0, 31.5, 0.0, -1.0},
                {47.0, 36.0, 12.0}};
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.equals(s.solution(k[i], ranges[i]), result[i]));
        }
    }

    public double[] solution(int k, int[][] ranges) {

        List<Double> collatz = new ArrayList<>();
        collatz.add((double) k);
        while (k != 1) {
            if (k % 2 == 0) k /=2;
            else k = 3 * k + 1;
            collatz.add((double) k);
        }

        double[] result = new double[ranges.length];
        int idx = 0;
        for (int[] range : ranges) {
            double area = 0;
            for (int i = range[0]; i < collatz.size() + range[1]; i++) area += collatz.get(i);
            if (area == 0) result[idx++] = -1.0;
            else {
                result[idx++] = area - (collatz.get(range[0]) + collatz.get(collatz.size() - 1 + range[1])) / 2;
            }
        }

        return result;
    }
}