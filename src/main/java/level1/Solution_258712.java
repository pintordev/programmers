package level1;

import java.util.HashMap;
import java.util.Map;

class Solution_258712 {

    public static void main(String[] args) {

        Solution_258712 s = new Solution_258712();

        String[][] friends = {{"muzi", "ryan", "frodo", "neo"},
                {"joy", "brad", "alessandro", "conan", "david"},
                {"a", "b", "c"}};
        String[][] gifts = {{"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"},
                {"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"},
                {"a b", "b a", "c a", "a c", "a c", "c a"}};
        int[] result = {2, 4, 0};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(friends[i], gifts[i]) == result[i]);
        }
    }

    public int solution(String[] friends, String[] gifts) {

        int n = friends.length;
        int[] rei = new int[n];
        int[][] gdx = new int[n][n];

        Map<String, Integer> fmap = new HashMap<>();
        for (int i = 0; i < n; i++) fmap.put(friends[i], i);

        for (String gift : gifts) {
            String[] bits = gift.split(" ");
            int g = fmap.get(bits[0]), r = fmap.get(bits[1]);
            rei[g]++;
            rei[r]--;
            gdx[g][r]++;
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if (j == i) continue;
                if (gdx[i][j] > gdx[j][i]) sum++;
                else if (gdx[i][j] == gdx[j][i] && rei[i] > rei[j]) sum++;
            }
            count = Math.max(count, sum);
        }

        return count;
    }
}