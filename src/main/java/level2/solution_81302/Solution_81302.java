package level2.solution_81302;

import java.util.Arrays;

class Solution_81302 {

    public static void main(String[] args) {

        Solution_81302 s = new Solution_81302();

        String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        int[] result = {1, 0, 1, 1, 1};
        System.out.println(Arrays.equals(s.solution(places), result));
    }

    public int[] solution(String[][] places) {
        int[] result = new int[5];
        for (int i = 0; i < 5; i++) result[i] = check(places[i]);
        return result;
    }

    public int check(String[] place) {

        int[][] ds = {{0, -2}, {0, -1}, {0, 1}, {0, 2},
                {-2, 0}, {-1, 0}, {1, 0}, {2, 0},
                {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};

        char[][] c = new char[5][5];
        for (int i = 0; i < 5; i++) c[i] = place[i].toCharArray();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (c[i][j] != 'P') continue;
                for (int[] d : ds) {
                    if (!valid(i + d[0], j + d[1])) continue;
                    if (!judge(c, i, j, d[0], d[1])) return 0;
                }
            }
        }

        return 1;
    }

    public boolean valid(int x, int y) {
        return x >= 0 && x < 5 && y >= 0 && y < 5;
    }

    public boolean judge(char[][] c, int x, int y, int dx, int dy) {

        if (c[x + dx][y + dy] != 'P') return true;

        if (Math.abs(dx) == 1 && Math.abs(dy) == 1) return c[x + dx][y] == 'X' && c[x][y + dy] == 'X';
        else if (Math.abs(dx) == 2 || Math.abs(dy) == 2) return c[x + dx / 2][y + dy / 2] == 'X';
        else return false;
    }
}