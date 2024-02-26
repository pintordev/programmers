package level2;

import java.util.Arrays;

class Solution_12923 {

    public static void main(String[] args) {

        Solution_12923 s = new Solution_12923();

        long begin = 1;
        long end = 10;
        int[] result = {0, 1, 1, 2, 1, 3, 1, 4, 3, 5};
        System.out.println(Arrays.equals(s.solution(begin, end), result));
    }

    public int[] solution(long begin, long end) {

        int[] result = new int[(int) (end - begin + 1)];
        for (long i = begin; i <= end; i++) {
            result[(int) (i - begin)] = find(i);
        }

        return result;
    }

    public int find(long n) {

        if (n == 1) return 0;

        long r = 1;
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                if (n / i <= 10000000) return (int) (n / i);
                r = i;
            }
        }

        return (int) r;
    }
}