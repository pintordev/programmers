package level2;

import java.util.Arrays;

class Solution_172927 {

    public static void main(String[] args) {

        Solution_172927 s = new Solution_172927();

        int[][] picks = {{1, 3, 2},
                {0, 1, 1}};
        String[][] minerals = {{"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"},
                {"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"}};
        int[] result = {12, 50};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(picks[i], minerals[i]) == result[i]);
        }
    }

    public int solution(int[] picks, String[] minerals) {

        int len = 0;
        for (int pick : picks) len += pick;

        int[][] _minerals = new int[len][3];
        for (int i = 0; i < _minerals.length; i++) {
            int[] sub = new int[3];
            for (int j = 5 * i; j < minerals.length && j < 5 * (i + 1); j++) {
                if (minerals[j].equals("diamond")) sub[0]++;
                else if (minerals[j].equals("iron")) sub[1]++;
                else sub[2]++;
            }
            _minerals[i] = sub;
        }

        Arrays.sort(_minerals, (o1, o2) -> {
            if (o1[0] == o2[0] && o1[1] == o2[1]) return o2[2] - o1[2];
            else if (o1[0] == o2[0]) return o2[1] - o1[1];
            else return o2[0] - o1[0];
        });

        int fatigue = 0;
        for (int i = 0; i < _minerals.length; i++) {
            if (picks[0]-- > 0) fatigue += cal(_minerals[i], 0);
            else if (picks[1]-- > 0) fatigue += cal(_minerals[i], 1);
            else if (picks[2]-- > 0) fatigue += cal(_minerals[i], 2);
        }

        return fatigue;
    }

    public int cal(int[] mineral, int p) {
        int[][] factor = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
        int sum = 0;
        for (int i = 0; i < factor[p].length; i++) sum += factor[p][i] * mineral[i];
        return sum;
    }
}