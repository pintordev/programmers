package level2.solution_250136;

import java.util.*;

class Solution_250136 {

    public static void main(String[] args) {

        Solution_250136 s = new Solution_250136();

        int[][][] land = {{{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}},
                {{1, 0, 1, 0, 1, 1}, {1, 0, 1, 0, 0, 0}, {1, 0, 1, 0, 0, 1}, {1, 0, 0, 1, 0, 0}, {1, 0, 0, 1, 0, 1}, {1, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1}}};
        int[] result = {9, 16};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(land[i]) == result[i]);
        }
    }

    public int solution(int[][] land) {

        int[][] ds = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        boolean[][] visited = new boolean[land.length][land[0].length];

        Map<Integer, Integer> oilMap = new HashMap<>();
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[i].length; j++) {
                if (land[i][j] == 0 || visited[i][j]) continue;

                Set<Integer> area = new HashSet<>();
                int amount = 0;

                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[]{i, j});
                area.add(j);
                amount++;
                visited[i][j] = true;

                while (!queue.isEmpty()) {
                    int[] now = queue.poll();
                    for (int k = 0; k < ds.length; k++) {
                        int mx = now[0] + ds[k][0];
                        int my = now[1] + ds[k][1];
                        if (canGo(mx, my, land) && !visited[mx][my]) {
                            queue.add(new int[]{mx, my});
                            area.add(my);
                            amount++;
                            visited[mx][my] = true;
                        }
                    }
                }

                for (Integer column : area) {
                    oilMap.put(column, oilMap.getOrDefault(column, 0) + amount);
                }
            }
        }

        int maxValue = 0;
        for (Integer value : oilMap.values()) maxValue = Math.max(value, maxValue);
        return maxValue;
    }

    public boolean canGo(int mx, int my, int[][] land) {
        if (mx < 0 || mx >= land.length || my < 0 || my >= land[0].length) return false;
        if (land[mx][my] == 0) return false;
        return true;
    }
}