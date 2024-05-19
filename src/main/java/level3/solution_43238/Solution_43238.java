package level3;

class Solution_43238 {

    public static void main(String[] args) {

        Solution_43238 s = new Solution_43238();

        int n = 6;
        int[] times = {7, 10};
        int result = 28;
        System.out.println(s.solution(n, times) == result);
    }

    public long solution(int n, int[] times) {
        int tLen = times.length;

        long low = 0;
        long high = (long) n * times[tLen - 1];
        while (low + 1 < high) {
            long mid = (low + high) >> 1;

            long sum = 0;
            for (int i = 0; i < tLen && sum < n; i++) {
                sum += mid / times[i];
            }

            if (sum < n) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return high;
    }
}