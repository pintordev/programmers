package level2.solution_86052;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution_86052 {

    public static void main(String[] args) {

        Solution_86052 s = new Solution_86052();

        String[][] grid = {{"SL", "LR"},
                {"S"},
                {"R", "R"}};
        int[][] result = {{16},
                {1, 1, 1, 1},
                {4, 4}};
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.equals(s.solution(grid[i]), result[i]));
        }
    }

    public int[] solution(String[] grid) {

        int m = grid.length, n = grid[0].length();
        boolean[][][] visited = new boolean[m][n][4];
        int[][] ds = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 4; k++) {
                    int count = 0;
                    int a = i, b = j, c = k;
                    while (!visited[a][b][c]) {
                        visited[a][b][c] = true;
                        count++;
                        a = (a + ds[c][0] + m) % m;
                        b = (b + ds[c][1] + n) % n;
                        c = rotate(c, grid[a].charAt(b));
                    }
                    if (count > 0) result.add(count);
                }
            }
        }

        return result.stream()
                .sorted()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public int rotate(int c, char node) {
        if (node == 'S') return c;
        else if (node == 'R') return (c + 1) % 4;
        else return (c - 1 + 4) % 4;
    }
}