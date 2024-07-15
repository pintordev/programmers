package level4.solution_49995;

class Solution_49995 {

    public static void main(String[] args) {

        Solution_49995 s = new Solution_49995();

        int[][] cookie = {
                {1, 1, 2, 3},
                {1, 2, 4, 5}
        };
        int[] result = {3, 0};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(cookie[i]) == result[i]);
        }
    }

    public int solution(int[] cookie) {
        int n = cookie.length, sum = 0;
        for (int i = 0; i < n - 1; i++) {
            int ldx = i, rdx = i + 1;
            int lSum = cookie[ldx], rSum = cookie[rdx];

            while (true) {
                if (lSum == rSum && sum < lSum) sum = lSum;
                else if (lSum <= rSum && ldx > 0) lSum += cookie[--ldx];
                else if (lSum > rSum && rdx < n - 1) rSum += cookie[++rdx];
                else break;
            }
        }
        return sum;
    }
}