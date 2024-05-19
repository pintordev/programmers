package level1;

import java.util.*;

class Solution_68644 {

    public static void main(String[] args) {

        Solution_68644 s = new Solution_68644();

        int[][] numbers = {{2, 1, 3, 4, 1},
                {5, 0, 2, 7}};
        int[][] result = {{2, 3, 4, 5, 6, 7},
                {2, 5, 7, 9, 12}};
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.equals(s.solution(numbers[i]), result[i]));
        }
    }

    public int[] solution(int[] numbers) {

        Set<Integer> comSet = new TreeSet<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                comSet.add(numbers[i] + numbers[j]);
            }
        }

        int[] result = new int[comSet.size()];
        int idx = 0;
        for (int n : comSet) result[idx++] = n;

        return result;
    }
}