package level2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution_87377 {

    public static void main(String[] args) {

        Solution_87377 s = new Solution_87377();

        int[][][] line = {{{2, -1, 4}, {-2, -1, 4}, {0, -1, 1}, {5, -8, -12}, {5, 8, 12}},
                {{0, 1, -1}, {1, 0, -1}, {1, 0, 1}},
                {{1, -1, 0}, {2, -1, 0}},
                {{1, -1, 0}, {2, -1, 0}, {4, -1, 0}}};
        String[][] result = {{"....*....", ".........", ".........", "*.......*", ".........", ".........", ".........", ".........", "*.......*"},
                {"*.*"},
                {"*"},
                {"*"}};
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.equals(s.solution(line[i]), result[i]));
        }
    }

    public String[] solution(int[][] line) {

        Set<long[]> points = new HashSet<>();
        long xl = Long.MIN_VALUE, xr = Long.MAX_VALUE, yu = Long.MIN_VALUE, yd = Long.MAX_VALUE;
        for (int i = 0; i < line.length - 1; i++) {
            for (int j = i + 1; j < line.length; j++) {
                long[] point = cross(line[i], line[j]);
                if (point == null) continue;
                points.add(point);
                xl = Math.max(xl, point[0]);
                xr = Math.min(xr, point[0]);
                yu = Math.max(yu, point[1]);
                yd = Math.min(yd, point[1]);
            }
        }

        char[][] result = new char[(int) (yu - yd + 1)][(int) (xl - xr + 1)];
        for (int i = 0; i < result.length; i++) Arrays.fill(result[i], '.');
        for (long[] point : points) result[(int) (yu - point[1])][(int) (point[0] - xr)] = '*';

        return Arrays.stream(result)
                .map(chars -> new String(chars))
                .toArray(String[]::new);
    }

    public long[] cross(int[] e1, int[] e2) {

        long a = e1[0], b = e1[1], e = e1[2];
        long c = e2[0], d = e2[1], f = e2[2];

        long base = a * d - b * c;
        if (base == 0) return null;

        long xn = b * f - e * d;
        long yn = e * c - a * f;
        if (xn % base != 0 || yn % base != 0) return null;

        return new long[]{xn / base, yn / base};
    }
}