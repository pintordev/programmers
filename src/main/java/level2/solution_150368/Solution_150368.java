package level2.solution_150368;

import java.util.Arrays;

class Solution_150368 {

    public static void main(String[] args) {

        Solution_150368 s = new Solution_150368();

        int[][][] users = {{{40, 10000}, {25, 10000}},
                {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}}};
        int[][] emoticons = {{7000, 9000},
                {1300, 1500, 1600, 4900}};
        int[][] result = {{1, 5400},
                {4, 13860}};
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.equals(s.solution(users[i], emoticons[i]), result[i]));
        }
    }

    public int[] solution(int[][] users, int[] emoticons) {

        int[] discounts = new int[emoticons.length];
        int[] max = new int[2];
        dp(users, emoticons, discounts, max, 0);

        return max;
    }

    public void dp(int[][] users, int[] emoticons, int[] discounts, int[] max, int count) {

        if (discounts.length == count) {
            int join = 0, earn = 0;
            for (int[] user : users) {
                int pay = 0;
                for (int i = 0; i < discounts.length; i++) {
                    if (discounts[i] >= user[0]) {
                        pay += (100 - discounts[i]) * emoticons[i];
                    }
                }
                if (pay / 100 >= user[1]) join++;
                else earn += pay / 100;
            }

            if (max[0] < join) {
                max[0] = join;
                max[1] = earn;
            } else if (max[0] == join && max[1] < earn) {
                max[1] = earn;
            }

            return;
        }

        for (int i = 10; i <= 40; i += 10) {
            discounts[count] = i;
            dp(users, emoticons, discounts, max, count + 1);
        }
    }
}