package level3.solution_72413;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Solution_72413 {

    public static void main(String[] args) {

        Solution_72413 s = new Solution_72413();

        int[] n = {6, 7, 6};
        int[] _s = {4, 3, 4};
        int[] a = {6, 4, 5};
        int[] b = {2, 1, 6};
        int[][][] fares = {{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}},
                {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}},
                {{2, 6, 6}, {6, 3, 7}, {4, 6, 7}, {6, 5, 11}, {2, 5, 12}, {5, 3, 20}, {2, 4, 8}, {4, 3, 9}}};
        int[] result = {82, 14, 18};

        int t = n.length;
        for (int i = 0; i < t; i++) {
            System.out.println(s.solution(n[i], _s[i], a[i], b[i], fares[i]) == result[i]);
        }
    }

    public static List<Node>[] graph;
    public static int[] sCost;
    public static int[] aCost;
    public static int[] bCost;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for (int[] fare : fares) {
            graph[fare[0]].add(new Node(fare[1], fare[2]));
            graph[fare[1]].add(new Node(fare[0], fare[2]));
        }
        sCost = new int[n + 1];
        aCost = new int[n + 1];
        bCost = new int[n + 1];

        dijkstra(n, s, sCost);
        dijkstra(n, a, aCost);
        dijkstra(n, b, bCost);

        int minCost = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            minCost = Math.min(minCost, sCost[i] + aCost[i] + bCost[i]);
        }
        return minCost;
    }

    public void dijkstra(int n, int p, int[] pCost) {
        for (int i = 1; i <= n; i++) pCost[i] = Integer.MAX_VALUE;
        pCost[p] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(p, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (pCost[cur.idx] < cur.cost) continue;
            for (Node next : graph[cur.idx]) {
                int cost = cur.cost + next.cost;
                if (cost < pCost[next.idx]) {
                    pCost[next.idx] = cost;
                    pq.add(new Node(next.idx, cost));
                }
            }
        }
    }
}

class Node implements Comparable<Node> {
    int idx;
    int cost;

    public Node(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}