package level4.solution_43236;

import java.util.Arrays;

class Solution_43236 {

    public static void main(String[] args) {

        Solution_43236 s = new Solution_43236();

        int distance = 25;
        int[] rocks = {2, 14, 11, 21, 17};
        int n = 2;
        int result = 4;

        System.out.println(s.solution(distance, rocks, n) == result);
    }

    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);

        int low = 0, high = distance + 1;
        while (low + 1 < high) {
            int mid = (low + high) >> 1;

            int cnt = 0, cur = 0;
            for (int i = 0, len = rocks.length; i < len; i++) {
                if (rocks[i] - cur < mid) cnt++;
                else cur = rocks[i];
            }
            if (distance - cur < mid) cnt++;

            if (cnt > n) high = mid;
            else low = mid;
        }
        return low;
    }
}