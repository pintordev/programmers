package level3.solution_1836;

import java.util.Map;
import java.util.TreeMap;

class Solution_1836 {

    public static void main(String[] args) {

        Solution_1836 s = new Solution_1836();

        int[] m = {3, 2, 4, 2};
        int[] n = {3, 4, 4, 2};
        String[][] board = {
                {"DBA", "C*A", "CDB"},
                {"NRYN", "ARYA"},
                {".ZI.", "M.**", "MZU.", ".IU."},
                {"AB", "BA"}
        };
        String[] result = {"ABCD", "RYAN", "MUZI", "IMPOSSIBLE"};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(m[i], n[i], board[i]).equals(result[i]));
        }
    }

    public static Map<Character, int[][]> coords;
    public static char[][] map;

    public String solution(int m, int n, String[] board) {
        coords = new TreeMap<>();
        map = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = board[i].charAt(j);
                map[i][j] = c;
                if (c < 65) continue;
                int[][] coord = coords.putIfAbsent(c, new int[][]{{i, j}, {0, 0}});
                if (coord != null) coord[1] = new int[]{i, j};
            }
        }

        StringBuilder sb = new StringBuilder();
        L:
        while (!coords.isEmpty()) {
            int before = coords.size();
            for (char key : coords.keySet()) {
                if (!canDelete(key)) continue;
                delete(key);
                sb.append(key);
                continue L;
            }
            if (coords.size() == before) return "IMPOSSIBLE";
        }
        return sb.toString();
    }

    public boolean canDelete(char key) {
        int[][] coord = coords.get(key);
        int r1 = coord[0][0];
        int r2 = coord[1][0];
        int c1 = coord[0][1];
        int c2 = coord[1][1];
        int minC = Math.min(c1, c2);
        int maxC = Math.max(c1, c2);

        if (r1 == r2) return checkH(minC, maxC, r1, key);
        if (c1 == c2) return checkV(r1, r2, c1, key);

        boolean u = checkH(minC, maxC, r1, key);
        boolean d = checkH(minC, maxC, r2, key);
        boolean l = checkV(r1, r2, minC, key);
        boolean r = checkV(r1, r2, maxC, key);

        if (c1 < c2) return (u && r) || (d && l);
        return (u && l) || (d && r);
    }

    public boolean checkH(int c1, int c2, int r, char key) {
        for (int j = c1; j <= c2; j++) {
            if (map[r][j] == '.' || map[r][j] == key) continue;
            return false;
        }
        return true;
    }

    public boolean checkV(int r1, int r2, int c, char key) {
        for (int i = r1; i <= r2; i++) {
            if (map[i][c] == '.' || map[i][c] == key) continue;
            return false;
        }
        return true;
    }

    public void delete(char key) {
        int[][] coord = coords.get(key);
        map[coord[0][0]][coord[0][1]] = '.';
        map[coord[1][0]][coord[1][1]] = '.';
        coords.remove(key);
    }
}