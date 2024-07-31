package level4.solution_81304;

import java.util.*;

class Solution_81304 {

    public static void main(String[] args) {

        Solution_81304 s = new Solution_81304();

        int[] n = {3, 4};
        int[] start = {1, 1};
        int[] end = {3, 4};
        int[][][] roads = {
                {{1, 2, 2}, {3, 2, 3}},
                {{1, 2, 1}, {3, 2, 1}, {2, 4, 1}}
        };
        int[][] traps = {
                {2},
                {2, 3}
        };
        int[] result = {5, 4};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(n[i], start[i], end[i], roads[i], traps[i]) == result[i]);
        }
    }

    public static List<Edge>[] graph;
    public static Map<Integer, Integer> trap;
    public static int[][] dist;

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] road : roads) {
            graph[road[0]].add(new Edge(road[1], road[2], true));
            graph[road[1]].add(new Edge(road[0], road[2], false));
        }

        int t = traps.length;

        trap = new HashMap<>();
        for (int i = 0; i < t; i++) {
            trap.put(traps[i], 1 << i);
        }

        dist = new int[n + 1][1 << t];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        return dijkstra(start, end);
    }

    public int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0, 0));
        dist[start][0] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int to = cur.to;
            int w = cur.w;
            int state = cur.state;

            if(to == end) return w;

            if(dist[to][state] < w) continue;

            boolean t1 = isTrapOn(to, state);
            for(Edge e : graph[to]) {
                int nextTo = e.to;
                int nextW = w + e.w;
                int nextState = state;

                boolean t2 = isTrapOn(nextTo, state);
                if ((t1 ^ t2) == e.dir) continue;

                if(trap.containsKey(nextTo)) nextState ^= trap.get(nextTo);

                if(dist[nextTo][nextState] > nextW) {
                    dist[nextTo][nextState] = nextW;
                    pq.add(new Node(nextTo, nextW, nextState));
                }
            }
        }

        return -1;
    }

    public boolean isTrapOn(int to, int state) {
        return (state & trap.getOrDefault(to, 0)) != 0;
    }
}

class Edge {
    int to;
    int w;
    boolean dir;

    public Edge(int to, int w, boolean dir) {
        this.to = to;
        this.w = w;
        this.dir = dir;
    }
}

class Node implements Comparable<Node> {
    int to;
    int w;
    int state;

    public Node(int to, int w, int state) {
        this.to = to;
        this.w = w;
        this.state = state;
    }

    @Override
    public int compareTo(Node o) {
        return this.w - o.w;
    }
}