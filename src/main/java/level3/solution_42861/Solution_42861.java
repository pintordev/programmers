package level3.solution_42861;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Solution_42861 {

    public static void main(String[] args) {

        Solution_42861 s = new Solution_42861();

        int n = 4;
        int[][] costs = {{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}};
        int result = 4;

        System.out.println(s.solution(n, costs) == result);
        System.out.println(s.solution2(n, costs) == result);
    }

    // Kruskal Algorithm
    public static int[] parent;

    public int solution(int n, int[][] costs) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        int e = costs.length;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < e; i++) {
            pq.add(new Edge(costs[i][0], costs[i][1], costs[i][2]));
        }

        int minCost = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (!isUnion(edge.node1, edge.node2)) {
                minCost += edge.cost;
                union(edge.node1, edge.node2);
            }
        }
        return minCost;
    }

    public int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return;
        if (x < y) parent[y] = x;
        else parent[x] = y;
    }

    public boolean isUnion(int x, int y) {
        return find(x) == find(y);
    }

    // Prim Algorithm
    public int solution2(int n, int[][] costs) {
        List<Edge2>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] cost : costs) {
            graph[cost[0]].add(new Edge2(cost[1], cost[2]));
            graph[cost[1]].add(new Edge2(cost[0], cost[2]));
        }

        boolean[] visited = new boolean[n];

        PriorityQueue<Edge2> pq = new PriorityQueue<>();
        pq.add(new Edge2(0, 0));

        int minCost = 0;
        while (!pq.isEmpty()) {
            Edge2 cur = pq.poll();
            if (visited[cur.node]) continue;
            visited[cur.node] = true;
            minCost += cur.cost;
            for (Edge2 next : graph[cur.node]) {
                if (!visited[next.node]) pq.add(next);
            }
        }
        return minCost;
    }
}

// Edge for Kruskal Algorithm
class Edge implements Comparable<Edge> {
    int node1;
    int node2;
    int cost;

    public Edge(int node1, int node2, int cost) {
        this.node1 = node1;
        this.node2 = node2;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}

// Edge for Prim Algorithm
class Edge2 implements Comparable<Edge2> {
    int node;
    int cost;

    public Edge2(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge2 o) {
        return this.cost - o.cost;
    }
}