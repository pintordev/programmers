package level3.solution_number;

import java.util.Arrays;

class Solution_1833 {

    public static void main(String[] args) {

        Solution_1833 s = new Solution_1833();

        int n = 4;
        int[][] data = {{0, 0}, {1, 1}, {0, 2}, {2, 0}};
        int result = 3;

        System.out.println(s.solution(n, data) == result);
    }

    public int solution(int n, int[][] data) {
        Arrays.sort(data, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (data[i][0] == data[j][0] || data[i][1] == data[j][1]) continue;
                if (!isInSquare(data, i, j)) cnt++;
            }
        }
        return cnt;
    }

    public boolean isInSquare(int[][] data, int i, int j) {
        for (int k = i + 1; k < j; k++) {
            int r1 = data[i][0];
            int r2 = data[j][0];
            int c1 = Math.min(data[i][1], data[j][1]);
            int c2 = Math.max(data[i][1], data[j][1]);
            if (data[k][0] == r1 || data[k][0] == r2) continue;
            if (data[k][1] <= c1 || data[k][1] >= c2) continue;
            return true;
        }
        return false;
    }
}