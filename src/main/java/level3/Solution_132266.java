package level3;

import java.util.*;

class Solution_132266 {

    public static void main(String[] args) {

        Solution_132266 s = new Solution_132266();

        int[] n = {3, 5};
        int[][][] roads = {{{1, 2}, {2, 3}},
                {{1, 2}, {1, 4}, {2, 4}, {2, 5}, {4, 5}}};
        int[][] sources = {{2, 3},
                {1, 3, 5}};
        int[] destination = {1, 5};
        int[][] result = {{1, 2},
                {2, -1, 0}};

        int t = n.length;
        for (int i = 0; i < t; i++) {
            System.out.println(Arrays.equals(s.solution(n[i], roads[i], sources[i], destination[i]), result[i]));
        }
    }

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {

        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            graph[road[0]].add(road[1]);
            graph[road[1]].add(road[0]);
        }

        return bfs(n, graph, sources, destination);
    }

    public int[] bfs(int n, List<Integer>[] graph, int[] sources, int destination) {
        int[] map = new int[n + 1];
        Arrays.fill(map, -1);

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(destination);
        map[destination] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph[cur]) {
                if (map[next] != -1) continue;
                map[next] = map[cur] + 1;
                queue.add(next);
            }
        }

        int s = sources.length;
        int[] result = new int[s];
        for (int i = 0; i < s; i++) {
            result[i] = map[sources[i]];
        }
        return result;
    }
}