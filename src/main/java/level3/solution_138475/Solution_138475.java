package level3.solution_138475;

import java.util.Arrays;

class Solution_138475 {

    public static void main(String[] args) {

        Solution_138475 s = new Solution_138475();

        int e = 8;
        int[] starts = {1, 3, 7};
        int[] result = {6, 6, 8};

        System.out.println(Arrays.equals(s.solution(e, starts), result));
    }

    public int[] solution(int e, int[] starts) {

        int[] div = new int[e + 1];
        for (int i = 1; i <= e; i++) {
            for (int j = 1; j <= e / i; j++) {
                div[i * j]++;
            }
        }

        int max = 0;
        int[] mdx = new int[e + 1];
        for (int i = e; i > 0; i--) {
            if (max <= div[i]) {
                max = div[i];
                mdx[i] = i;
            } else {
                mdx[i] = mdx[i + 1];
            }
        }

        for (int i = 0, t = starts.length; i < t; i++) {
            starts[i] = mdx[starts[i]];
        }
        return starts;
    }
}