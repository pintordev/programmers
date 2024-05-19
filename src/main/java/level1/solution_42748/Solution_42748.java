package level1;

import java.util.Arrays;

class Solution_42748 {

    public static void main(String[] args) {

        Solution_42748 s = new Solution_42748();

        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3},
                {4, 4, 1},
                {1, 7, 3}};
        int[] result = {5, 6, 3};
        System.out.println(Arrays.equals(s.solution(array, commands), result));
    }

    public int[] solution(int[] array, int[][] commands) {

        int[] result = new int[commands.length];
        for (int i = 0; i < commands.length; i++) {
            int[] copy = Arrays.copyOfRange(array, commands[i][0] - 1, commands[i][1]);
            Arrays.sort(copy);
            result[i] = copy[commands[i][2] - 1];
        }

        return result;
    }
}