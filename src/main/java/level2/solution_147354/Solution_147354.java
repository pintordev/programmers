package level2.solution_147354;

import java.util.Arrays;

class Solution_147354 {

    public static void main(String[] args) {

        Solution_147354 s = new Solution_147354();

        int[][] data = {{2, 2, 6},
                {1, 5, 10},
                {4, 2, 9},
                {3, 8, 3}};
        int col = 2;
        int row_begin = 2;
        int row_end = 3;
        int result = 4;
        System.out.println(s.solution(data, col, row_begin, row_end) == result);
    }

    public int solution(int[][] data, int col, int row_begin, int row_end) {

        Arrays.sort(data, (o1, o2) -> {
            if (o1[col - 1] == o2[col - 1]) return o2[0] - o1[0];
            return o1[col - 1] - o2[col - 1];
        });

        int result = 0;
        for (int i = row_begin - 1; i < row_end; i++) {
            int sum = 0;
            for (int n : data[i]) sum += n % (i + 1);
            result ^= sum;
        }

        return result;
    }
}