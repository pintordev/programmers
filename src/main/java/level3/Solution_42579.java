package level3;

import java.util.*;

class Solution_42579 {

    public static void main(String[] args) {

        Solution_42579 s = new Solution_42579();

        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        int[] result = {4, 1, 3, 0};
        System.out.println(Arrays.equals(s.solution(genres, plays), result));
    }

    public int[] solution(String[] genres, int[] plays) {

        Map<String, Integer> genMap = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            genMap.put(genres[i], genMap.getOrDefault(genres[i], 0) + plays[i]);
        }

        int[][] info = new int[genres.length][3];
        for (int i = 0; i < genres.length; i++) {
            info[i] = new int[]{genMap.get(genres[i]), i, plays[i]};
        }

        Arrays.sort(info, (o1, o2) -> {
            if (o1[0] == o2[0] && o1[2] == o2[2]) return o1[1] - o2[1];
            else if (o1[0] == o2[0]) return o2[2] - o1[2];
            return o2[0] - o1[0];
        });

        int[] result = new int[genres.length];
        int count = 0, now = 0, idx = 0;
        for (int i = 0; i < info.length; i++) {
            if (info[i][0] != now) {
                now = info[i][0];
                count = 1;
            } else count++;
            if (count <= 2) result[idx++] = info[i][1];
        }

        return Arrays.copyOf(result, idx);
    }
}