package level3.solution_12907;

class Solution_12907 {

    public static void main(String[] args) {

        Solution_12907 s = new Solution_12907();

        int n = 5;
        int[] money = {1, 2, 5};
        int result = 4;
        System.out.println(s.solution(n, money) == result);
    }

    public int solution(int n, int[] money) {

        int[] memo = new int[n + 1];
        memo[0] = 1;

        int len = money.length;
        for (int i = 0; i < len; i++) {
            for (int j = money[i]; j <= n; j++) {
                memo[j] += memo[j - money[i]];
            }
        }
        return memo[n];
    }
}