package level3.solution_118669;

import java.util.*;

class Solution_118669 {

    public static void main(String[] args) {

        Solution_118669 s = new Solution_118669();

        int[] n = {6, 7, 7, 5};
        int[][][] paths = {{{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}},
                {{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}},
                {{1, 2, 5}, {1, 4, 1}, {2, 3, 1}, {2, 6, 7}, {4, 5, 1}, {5, 6, 1}, {6, 7, 1}},
                {{1, 3, 10}, {1, 4, 20}, {2, 3, 4}, {2, 4, 6}, {3, 5, 20}, {4, 5, 6}}};
        int[][] gates = {{1, 3},
                {1},
                {3, 7},
                {1, 2}};
        int[][] summits = {{5},
                {2, 3, 4},
                {1, 5},
                {5}};
        int[][] result = {{5, 3},
                {3, 4},
                {5, 1},
                {5, 6}};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(Arrays.equals(s.solution(n[i], paths[i], gates[i], summits[i]), result[i]));
        }
    }

    public static int n;
    public static Set<Integer> gates;
    public static Set<Integer> summits;
    public static List<Node>[] graph;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        this.n = n;
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] path : paths) {
            graph[path[0]].add(new Node(path[1], path[2]));
            graph[path[1]].add(new Node(path[0], path[2]));
        }
        this.gates = new HashSet<>();
        for (int gate : gates) {
            this.gates.add(gate);
        }
        this.summits = new HashSet<>();
        for (int summit : summits) {
            this.summits.add(summit);
        }

        return dijkstra();
    }

    public int[] dijkstra() {
        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int gate : gates) {
            pq.add(new Node(gate, 0));
        }

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (intensity[cur.to] < cur.time) continue;
            for (Node next : graph[cur.to]) {
                if (gates.contains(next.to)) continue;
                int nextTime = Math.max(cur.time, next.time);
                if (intensity[next.to] <= nextTime) continue;
                intensity[next.to] = nextTime;
                if (summits.contains(next.to)) continue;
                pq.add(new Node(next.to, nextTime));
            }
        }

        int minSdx = Integer.MAX_VALUE;
        int minIntensity = Integer.MAX_VALUE;
        for (int summit : summits) {
            if (minIntensity < intensity[summit]) continue;
            if (minIntensity == intensity[summit]) {
                minSdx = Math.min(minSdx, summit);
            } else {
                minSdx = summit;
            }
            minIntensity = intensity[summit];
        }
        return new int[]{minSdx, minIntensity};
    }
}

class Node implements Comparable<Node> {
    int to;
    int time;

    public Node(int to, int time) {
        this.to = to;
        this.time = time;
    }

    @Override
    public int compareTo(Node o) {
        return this.time - o.time;
    }
}