package level2;

import java.util.*;

class Solution_135807 {

    public static void main(String[] args) {

        Solution_135807 s = new Solution_135807();

        int[][] arrayA = {{10, 17},
                {10, 20},
                {14, 35, 119}};
        int[][] arrayB = {{5, 20},
                {5, 17},
                {18, 30, 102}};
        int[] result = {0, 10, 7};
        for (int i = 0; i <result.length; i++) {
            System.out.println(s.solution(arrayA[i], arrayB[i]) == result[i]);
        }
    }

    public int solution(int[] arrayA, int[] arrayB) {
        return Math.max(
                judge(canList(arrayA[0], arrayB[0]), arrayA, arrayB),
                judge(canList(arrayB[0], arrayA[0]), arrayB, arrayA)
        );
    }

    public List<Integer> canList(int A, int B) {
        List<Integer> canList = new ArrayList<>();
        for (int i = 1; i * i <= A; i++) {
            if (A % i == 0) {
                if (B % i != 0) canList.add(i);
                if (B % (A / i) != 0) canList.add(A / i);
            }
        }
        canList.sort(Comparator.reverseOrder());
        return canList;
    }

    public int judge(List<Integer> canA, int[] arrayA, int[] arrayB) {
        for (int A : canA) {
            boolean b = true;
            for (int i = 0; i < arrayA.length; i++) {
                if (arrayA[i] % A != 0 || arrayB[i] % A == 0) {
                    b = false;
                    break;
                }
            }
            if (b) return A;
        }
        return 0;
    }
}