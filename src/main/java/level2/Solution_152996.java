package level2;

import java.util.Arrays;

class Solution_152996 {

    public static void main(String[] args) {

        Solution_152996 s = new Solution_152996();

        int[] weights = {100, 180, 360, 100, 270};
        long result = 4;
        System.out.println(s.solution(weights) == result);
    }

    public long solution(int[] weights) {

        Arrays.sort(weights);

        int[] ws = new int[1001];
        long count = 0;
        for (int i = 0; i < weights.length; i++) {

            count += ws[weights[i]];
            if (weights[i] % 2 == 0) count += ws[weights[i] / 2];
            if (2 * weights[i] % 3 == 0) count += ws[2 * weights[i] / 3];
            if (3 * weights[i] % 4 == 0) count += ws[3 * weights[i] / 4];

            ws[weights[i]]++;
        }

        return count;
    }
}