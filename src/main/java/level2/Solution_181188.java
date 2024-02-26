package level2;

import java.util.Arrays;

class Solution_181188 {

    public static void main(String[] args) {

        Solution_181188 s = new Solution_181188();

        int[][] targets = {{4, 5}, {4, 8}, {10, 14}, {11, 13}, {5, 12}, {3, 7}, {1, 4}};
        int result = 3;
        System.out.println(s.solution(targets) == result);
    }

    public int solution(int[][] targets) {

        Arrays.sort(targets, (o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });

        int te = 0;
        int result = 0;
        for (int[] target : targets) {
            if (te <= target[0]) {
                result++;
                te = target[1];
            } else {
                te = Math.min(te, target[1]);
            }
        }

        return result;
    }
}