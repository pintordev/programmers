package level2.solution_258711;

import java.util.*;

class Solution_258711 {

    public static void main(String[] args) {

        Solution_258711 s = new Solution_258711();

        int[][][] edges = {{{2, 3}, {4, 3}, {1, 1}, {2, 1}},
                {{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}}};
        int[][] result = {{2, 1, 1, 0},
                {4, 0, 1, 2}};
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.equals(s.solution(edges[i]), result[i]));
        }
    }

    public int[] solution(int[][] edges) {

        int[] in = new int[1000001];
        int[] out = new int[1000001];

        int max = 0;
        for (int[] edge : edges) {
            out[edge[0]]++;
            in[edge[1]]++;
            max = Math.max(max, Math.max(edge[0], edge[1]));
        }

        int[] result = new int[4];
        for (int i = 1; i <= max; i++) {
            if (in[i] == 0 && out[i] >= 2) result[0] = i;
            else if (out[i] == 0) result[2]++;
            else if (in[i] >= 2 && out[i] == 2) result[3]++;
        }

        result[1] = out[result[0]] - result[2] - result[3];

        return result;
    }
}