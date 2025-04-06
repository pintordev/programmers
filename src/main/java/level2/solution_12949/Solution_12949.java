package level2.solution_12949;

import java.util.Arrays;

class Solution_12949 {

    public static void main(String[] args) {

        Solution_12949 s = new Solution_12949();

        int[][][] arr1 = {
                {{1, 4}, {3, 2}, {4, 1}},
                {{2, 3, 2}, {4, 2, 4}, {3, 1, 4}}
        };
        int[][][] arr2 = {
                {{3, 3}, {3, 3}},
                {{5, 4, 3}, {2, 4, 1}, {3, 1, 1}}
        };
        int[][][] result = {
                {{15, 15}, {15, 15}, {15, 15}},
                {{22, 22, 11}, {36, 28, 18}, {29, 20, 14}}
        };

        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.deepEquals(s.solution(arr1[i], arr2[i]), result[i]));
        }
    }

    public int[][] solution(int[][] arr1, int[][] arr2) {

        int[][] result = new int[arr1.length][arr2[0].length];

        int idx = 0, jdx = 0;
        while(true) {
            for (int i = 0; i < arr2.length; i++) {
                result[idx][jdx] += arr1[idx][i] * arr2[i][jdx];
            }
            jdx++;

            if (jdx == arr2[0].length) {
                jdx = 0;
                idx++;
            }

            if (idx == arr1.length) break;
        }

        return result;
    }
}