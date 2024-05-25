package level3.solution_152995;

import java.util.Arrays;

class Solution_152995 {

    public static void main(String[] args) {

        Solution_152995 s = new Solution_152995();

        int[][] scores = {{2, 2}, {1, 4}, {3, 2}, {3, 2}, {2, 1}};
        int result = 4;

        System.out.println(s.solution(scores) == result);
    }

    public int solution(int[][] scores) {
        int[] me = scores[0];
        Arrays.sort(scores, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return b[0] - a[0];
        });

        int result = 1;
        int prev = scores[0][1];
        for (int i = 0, n = scores.length; i < n; i++) {
            if (prev > scores[i][1]) {
                if (scores[i][0] == me[0] && scores[i][1] == me[1]) return -1;
                continue;
            }
            prev = scores[i][1];
            if (scores[i][0] + scores[i][1] > me[0] + me[1]) result++;
        }
        return result;
    }
}