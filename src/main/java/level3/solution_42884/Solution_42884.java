package level3;

import java.util.Arrays;
import java.util.Comparator;

class Solution_42884 {

    public static void main(String[] args) {

        Solution_42884 s = new Solution_42884();

        int[][] routes = {{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}};
        int result = 2;
        System.out.println(s.solution(routes) == result);
    }

    public int solution(int[][] routes) {

        Arrays.sort(routes, Comparator.comparingInt(o -> o[1]));

        int e = routes[0][1], result = 1;
        for (int[] route : routes) {
            if (e >= route[0] && e <= route[1]) continue;
            result++;
            e = route[1];
        }

        return result;
    }
}