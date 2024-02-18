package level2;

import java.util.*;

class Solution_159993 {

    public static void main(String[] args) {

        Solution_159993 s = new Solution_159993();

        String[][] maps = {{"SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"},
                {"LOOXS", "OOOOX", "OOOOO", "OOOOO", "EOOOO"}};
        int[] result = {16, -1};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(maps[i]) == result[i]);
        }
    }

    public int solution(String[] maps) {

        int[][] map = new int[maps.length][maps[0].length()];
        int[] s = new int[2], l = new int[2], e = new int[2];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = maps[i].charAt(j) != 'X' ? 0 : -1;
                if (maps[i].charAt(j) == 'S') {
                    s[0] = i;
                    s[1] = j;
                } else if (maps[i].charAt(j) == 'L') {
                    l[0] = i;
                    l[1] = j;
                } else if (maps[i].charAt(j) == 'E') {
                    e[0] = i;
                    e[1] = j;
                }
            }
        }

        move(map, s, l);
        if (map[l[0]][l[1]] == 0) return -1;
        move(map, l, e);
        return map[e[0]][e[1]] == 0 ? -1 : map[e[0]][e[1]];
    }

    public void move(int[][] map, int[] s, int[] e) {

        int[][] ds = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        boolean[][] visited = new boolean[map.length][map[0].length];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(s);
        visited[s[0]][s[1]] = true;

        while(!queue.isEmpty()) {

            int[] now = queue.poll();

            for (int i = 0; i < ds.length; i++) {
                int mx = now[0] + ds[i][0];
                int my = now[1] + ds[i][1];
                if (canGo(mx, my, map) && !visited[mx][my]) {
                    queue.add(new int[]{mx, my});
                    visited[mx][my] = true;
                    map[mx][my] = map[now[0]][now[1]] + 1;
                    if (mx == e[0] && my == e[1]) return;
                }
            }

        }
    }

    public boolean canGo(int mx, int my, int[][] map) {
        if (mx < 0 || mx >= map.length || my < 0 || my >= map[0].length) return false;
        if (map[mx][my] == -1) return false;
        return true;
    }
}