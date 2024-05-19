package level3;

import java.util.*;

class Solution_12987 {

    public static void main(String[] args) {

        Solution_12987 s = new Solution_12987();

        int[][] A = {{5, 1, 3, 7},
                {2, 2, 2, 2}};
        int[][] B = {{2, 2, 6, 8},
                {1, 1, 1, 1}};
        int[] result = {3, 0};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(A[i], B[i]) == result[i]);
        }
    }

    public int solution(int[] A, int[] B) {

        Arrays.sort(A);
        Arrays.sort(B);

        int score = 0, jdx = B.length - 1;
        for (int i = A.length - 1; i >= 0; i--) {
            if (B[jdx] > A[i]) {
                score++;
                jdx--;
            }
        }

        return score;
    }
}