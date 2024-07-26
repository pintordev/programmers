package level4.solution_1843;

class Solution_1843 {

    public static void main(String[] args) {

        Solution_1843 s = new Solution_1843();

        String[][] arr = {
                {"1", "-", "3", "+", "5", "-", "8"},
                {"5", "-", "3", "+", "1", "+", "2", "-", "4"}
        };
        int[] result = {1, 3};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(arr[i]) == result[i]);
        }
    }

    public static int[][][] memo;
    public static boolean[] op;

    public int solution(String arr[]) {
        int n = (arr.length >> 1) + 1;
        int[] num = new int[n];
        op = new boolean[n - 1];
        int ndx = 0, odx = 0;
        for (String term : arr) {
            if (term.equals("+")) op[odx++] = true;
            else if (term.equals("-")) op[odx++] = false;
            else num[ndx++] = Integer.parseInt(term);
        }

        memo = new int[n][n][2];
        for (int i = 0; i < n; i++) {
            memo[i][i][0] = num[i];
            memo[i][i][1] = num[i];
        }

        for (int d = 1; d < n; d++) {
            for (int s = 0; s + d < n; s++) {
                cal(s, s + d);
            }
        }
        return memo[0][n - 1][1];
    }

    public void cal(int s, int e) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = s; i < e; i++) {
            if (op[i]) {
                min = Math.min(min, memo[s][i][0] + memo[i + 1][e][0]);
                max = Math.max(max, memo[s][i][1] + memo[i + 1][e][1]);
            } else {
                min = Math.min(min, memo[s][i][0] - memo[i + 1][e][1]);
                max = Math.max(max, memo[s][i][1] - memo[i + 1][e][0]);
            }
        }
        memo[s][e][0] = min;
        memo[s][e][1] = max;
    }
}