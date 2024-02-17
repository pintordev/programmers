package level2;

import java.util.*;

class Solution_12978 {

    public static void main(String[] args) {

        Solution_12978 s = new Solution_12978();

        int[] N = {5, 6};
        int[][][] road = {{{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}},
                {{1,2,1},{1,3,2},{2,3,2},{3,4,3},{3,5,2},{3,5,3},{5,6,1}}};
        int[] K = {3, 4};
        int[] result = {4, 4};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(N[i], road[i], K[i]) == result[i]);
        }
    }

    public int solution(int N, int[][] road, int K) {

        Map<Integer, Set<int[]>> map = new HashMap<>();
        for (int[] r : road) {
            for (int i = 0; i <= 1; i++) {
                Set<int[]> set = map.getOrDefault(r[i], new HashSet<>());
                set.add(new int[]{r[i == 1 ? 0 : 1], r[2]});
                map.put(r[i], set);
            }
        }

        boolean[] visited = new boolean[N];

        Queue<int[]> queue = new LinkedList<>();
        for (int[] r : map.get(1)) queue.add(new int[]{1, r[0], r[1], 0});
        visited[0] = true;

        int[] result = new int[N];
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            if (visited[now[1] - 1]) continue;
            if (now[2] == ++now[3]) {
                for (int[] r : map.getOrDefault(now[1], new HashSet<>())) queue.add(new int[]{now[1], r[0], r[1], 0});
                visited[now[1] - 1] = true;
                result[now[1] - 1] = result[now[0] - 1] + now[2];
            } else {
                queue.add(now);
            }
        }

        return (int) Arrays.stream(result).filter(i -> i <= K).count();
    }
}