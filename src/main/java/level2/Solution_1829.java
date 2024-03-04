package level2;

import java.util.*;

class Solution_1829 {

    public static void main(String[] args) {

        Solution_1829 s = new Solution_1829();

        int m = 6;
        int n = 4;
        int[][] picture = {{1, 1, 1, 0},
                {1, 2, 2, 0},
                {1, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 3},
                {0, 0, 0, 3}};
        int[] result = {4, 5};
        System.out.println(Arrays.equals(s.solution(m, n, picture), result));
    }

    public int[] solution(int m, int n, int[][] picture) {

        int[][] ds = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        boolean[][] visited = new boolean[m][n];

        int num = 0, max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (picture[i][j] == 0 || visited[i][j]) continue;

                num++;

                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[]{i, j});
                visited[i][j] = true;
                int area = 1, color = picture[i][j];

                while (!queue.isEmpty()) {
                    int[] now = queue.poll();
                    for (int k = 0; k < ds.length; k++) {
                        int mx = now[0] + ds[k][0];
                        int my = now[1] + ds[k][1];
                        if (canGo(mx, my, m, n, picture, color) && !visited[mx][my]) {
                            queue.add(new int[]{mx, my});
                            visited[mx][my] = true;
                            area++;
                        }
                    }
                }

                max = Math.max(max, area);
            }
        }

        return new int[]{num, max};
    }

    public boolean canGo(int mx, int my, int m, int n, int[][] picture, int color) {
        if (mx < 0 || mx >= m || my < 0 || my >= n) return false;
        if (picture[mx][my] != color) return false;
        return true;
    }
}