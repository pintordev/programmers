package level3;

class Solution_12971 {

    public static void main(String[] args) {

        Solution_12971 s = new Solution_12971();

        int[][] sticker = {{14, 6, 5, 11, 3, 9, 2, 10},
                {1, 3, 2, 5, 4},
                {4, 3, 2, 9, 4}};
        int[] result = {36, 8, 13};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(sticker[i]) == result[i]);
        }
    }

    public int solution(int sticker[]) {

        int n = sticker.length;
        if (n == 1) return sticker[0];

        int[] dp0 = new int[n];
        int[] dp1 = new int[n];

        dp0[0] = sticker[0];
        dp0[1] = Math.max(dp0[0], sticker[1]);
        dp1[1] = sticker[1];

        for (int i = 2; i < n; i++) {
            dp0[i] = Math.max(dp0[i - 1], dp0[i - 2] + sticker[i]);
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + sticker[i]);
        }

        return Math.max(dp0[n - 2], dp1[n - 1]);
    }
}