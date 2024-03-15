package level1;

import java.util.Arrays;

class Solution_12910 {

    public static void main(String[] args) {

        Solution_12910 s = new Solution_12910();

        int[][] arr = {{5, 9, 7, 10},
                {2, 36, 1, 3},
                {3, 2, 6}};
        int[] divisor = {5, 1, 10};
        int[][] result = {{5, 10},
                {1, 2, 3, 36},
                {-1}};
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.equals(s.solution(arr[i], divisor[i]), result[i]));
        }
    }

    public int[] solution(int[] arr, int divisor) {

        Arrays.sort(arr);

        int[] result = new int[arr.length];
        int idx = 0;
        for (int n : arr) {
            if (n % divisor == 0) result[idx++] = n;
        }

        return idx > 0 ? Arrays.copyOf(result, idx) : new int[]{-1};
    }
}