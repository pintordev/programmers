package level3.solution_12920;

class Solution_12920 {

    public static void main(String[] args) {

        Solution_12920 s = new Solution_12920();

        int n = 6;
        int[] cores = {1, 2, 3};
        int result = 2;

        System.out.println(s.solution(n, cores) == result);
    }

    public int solution(int n, int[] cores) {
        int len = cores.length;
        if (n <= len) return n;

        int low = 0;
        int high = 10000 * n;
        while (low + 1 < high) {
            int mid = (low + high) >>> 1;

            int cnt = len;
            for (int i = 0; i < len; i++) {
                cnt += mid / cores[i];
            }

            if (cnt < n) {
                low = mid;
            } else {
                high = mid;
            }
        }

        n -= len;
        for (int i = 0; i < len; i++) {
            n -= (high - 1) / cores[i];
        }
        for (int i = 0; i < len; i++) {
            if (high % cores[i] == 0) n--;
            if (n == 0) return i + 1;
        }
        return -1;
    }
}