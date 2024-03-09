package level2;

import java.util.Arrays;

class Solution_169198 {

    public static void main(String[] args) {

        Solution_169198 s = new Solution_169198();

        int m = 10;
        int n = 10;
        int startX = 3;
        int startY = 7;
        int[][] balls = {{7, 7}, {2, 7}, {7, 3}};
        int[] result = {52, 37, 116};
        System.out.println(Arrays.equals(s.solution(m, n, startX, startY, balls), result));
    }

    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {

        int[] result = new int[balls.length];
        for (int i = 0; i < balls.length; i++) {

            int len = Integer.MAX_VALUE;
            for (int j = 0; j < 4; j++) {

                int sx = startX, sy = startY, bx = balls[i][0], by = balls[i][1];
                if (j == 0) {
                    sy = n - sy;
                    by = n - by;
                } else if (j == 1) {
                    sx = m - sx;
                    bx = m - bx;
                }

                if (j % 2 == 0 && bx == sx && by < sy) continue;
                if (j % 2 == 1 && by == sy && bx < sx) continue;

                if (j % 2 == 0) len = Math.min(len, (bx - sx) * (bx - sx) + (by + sy) * (by + sy));
                else if (j % 2 == 1) len = Math.min(len, (bx + sx) * (bx + sx) + (by - sy) * (by - sy));
            }

            result[i] = len;
        }

        return result;
    }
}