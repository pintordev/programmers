package level2.solution_77485;

import java.util.*;

class Solution_77485 {

    public static void main(String[] args) {

        Solution_77485 s = new Solution_77485();

        int[] rows = {6, 3, 100};
        int[] columns = {6, 3, 97};
        int[][][] queries = {{{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}},
                {{1, 1, 2, 2}, {1, 2, 2, 3}, {2, 1, 3, 2}, {2, 2, 3, 3}},
                {{1, 1, 100, 97}}};
        int[][] result = {{8, 10, 25},
                {1, 1, 5, 3},
                {1}};
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.equals(s.solution(rows[i], columns[i], queries[i]), result[i]));
        }
    }

    public int[] solution(int rows, int columns, int[][] queries) {

        int[][] map = new int[rows][columns];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = columns * i + j + 1;
            }
        }

        int[] result = new int[queries.length];
        int idx = 0;
        for (int[] query : queries) {
            int min = Integer.MAX_VALUE;

            int si = query[0] - 1, sj = query[1] - 1, ei = query[2] - 1, ej = query[3] - 1;
            int mode = 1, hold = map[si][sj], i = si, j = sj;
            while (mode < 5) {

                min = Math.min(min, hold);

                if (mode == 1 && ++j == ej) mode++;
                else if (mode == 2 && ++i == ei) mode++;
                else if (mode == 3 && --j == sj) mode++;
                else if (mode == 4 && --i == si) mode++;

                int temp = map[i][j];
                map[i][j] = hold;
                hold = temp;
            }

            result[idx++] = min;
        }

        return result;
    }
}