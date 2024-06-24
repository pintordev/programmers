package level3.solution_number;

import java.util.*;

class Solution_1833 {

    public static void main(String[] args) {

        Solution_1833 s = new Solution_1833();

        int n = 4;
        int[][] data = {{0, 0}, {1, 1}, {0, 2}, {2, 0}};
        int result = 3;

        System.out.println(s.solution(n, data) == result);
        System.out.println(s.solution2(n, data) == result);
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

    public int solution2(int n, int[][] data) {
        Set<Integer> xSet = new TreeSet<>();
        Set<Integer> ySet = new TreeSet<>();
        for (int[] d : data) {
            xSet.add(d[0]);
            ySet.add(d[1]);
        }

        Map<Integer, Integer> xMap = toCompressedMap(xSet);
        Map<Integer, Integer> yMap = toCompressedMap(ySet);

        int xLen = xSet.size();
        int yLen = ySet.size();
        int[][] map = new int[xLen + 1][yLen + 1];
        for (int[] d : data) {
            d[0] = xMap.get(d[0]);
            d[1] = yMap.get(d[1]);
            map[d[0]][d[1]]++;
        }

        for (int i = 1; i <= xLen; i++) {
            for (int j = 1; j <= yLen; j++) {
                map[i][j] += map[i - 1][j] + map[i][j - 1] - map[i - 1][j - 1];
            }
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (data[i][0] == data[j][0] || data[i][1] == data[j][1]) continue;
                int r1 = Math.min(data[i][0], data[j][0]);
                int r2 = Math.max(data[i][0], data[j][0]) - 1;
                int c1 = Math.min(data[i][1], data[j][1]);
                int c2 = Math.max(data[i][1], data[j][1]) - 1;
                int diff = map[r2][c2] - map[r1][c2] - map[r2][c1] + map[r1][c1];
                if (diff == 0) cnt++;
            }
        }
        return cnt;
    }

    public Map<Integer, Integer> toCompressedMap(Set<Integer> set) {
        Map<Integer, Integer> map = new HashMap<>();
        int idx = 1;
        for (int s : set) {
            map.put(s, idx++);
        }
        return map;
    }
}