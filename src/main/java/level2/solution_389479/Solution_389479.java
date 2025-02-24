package level2.solution_389479;

class Solution_389479 {

    public static void main(String[] args) {

        Solution_389479 s = new Solution_389479();

        int[][] players = {
                {0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5},
                {0, 0, 0, 10, 0, 12, 0, 15, 0, 1, 0, 1, 0, 0, 0, 5, 0, 0, 11, 0, 8, 0, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 5, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1}
        };
        int[] m = {3, 5, 1};
        int[] k = {5, 1, 1};
        int[] result = {7, 11, 12};

        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(players[i], m[i], k[i]) == result[i]);
        }
    }

    public int solution(int[] players, int m, int k) {
        int len = players.length;
        int[] servers = new int[len];

        int cnt = 0;
        for (int i = 0; i < len; i++) {
            if (i > 0) servers[i] += servers[i - 1];

            int required = players[i] / m - servers[i];
            if (required <= 0) continue;

            cnt += required;
            servers[i] += required;
            if (i + k < len) servers[i + k] -= required;
        }
        return cnt;
    }
}