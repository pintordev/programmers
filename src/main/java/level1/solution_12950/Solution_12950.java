package level1;

import java.util.Arrays;

class Solution_12950 {

    public static void main(String[] args) {

        Solution_12950 s = new Solution_12950();

        int[][][] arr1 = {{{1, 2}, {2, 3}},
                {{1}, {2}}};
        int[][][] arr2 = {{{3, 4}, {5, 6}},
                {{3}, {4}}};
        int[][][] result = {{{4, 6}, {7, 9}},
                {{4}, {6}}};
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.deepEquals(s.solution(arr1[i], arr2[i]), result[i]));
        }
    }

    public int[][] solution(int[][] arr1, int[][] arr2) {

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[0].length; j++) {
                arr1[i][j] += arr2[i][j];
            }
        }

        return arr1;
    }
}