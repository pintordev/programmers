package level1;

import java.util.Arrays;

class Solution_12935 {

    public static void main(String[] args) {

        Solution_12935 s = new Solution_12935();

        int[][] arr = {{4, 3, 2, 1},
                {10}};
        int[][] result = {{4, 3, 2},
                {-1}};
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.equals(s.solution(arr[i]), result[i]));
        }
    }

    public int[] solution(int[] arr) {

        if (arr.length <= 1) return new int[]{-1};

        int min = arr[0];
        for (int num : arr) min = Math.min(min, num);

        int[] result = new int[arr.length - 1];
        int index = 0;
        for (int num : arr) {
            if (num != min) result[index++] = num;
        }

        return result;
    }
}