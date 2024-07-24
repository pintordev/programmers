package level4.solution_67260;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution_67260 {

    public static void main(String[] args) {

        Solution_67260 s = new Solution_67260();

        int[] n = {9, 9, 9};
        int[][][] path = {
                {{0, 1}, {0, 3}, {0, 7}, {8, 1}, {3, 6}, {1, 2}, {4, 7}, {7, 5}},
                {{8, 1}, {0, 1}, {1, 2}, {0, 7}, {4, 7}, {0, 3}, {7, 5}, {3, 6}},
                {{0, 1}, {0, 3}, {0, 7}, {8, 1}, {3, 6}, {1, 2}, {4, 7}, {7, 5}}
        };
        int[][][] order = {
                {{8, 5}, {6, 7}, {4, 1}},
                {{4, 1}, {5, 2}},
                {{4, 1}, {8, 7}, {6, 5}}
        };
        boolean[] result = {true, true, false};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(n[i], path[i], order[i]) == result[i]);
        }
    }

    public static int n;
    public static List<Integer>[] graph;
    public static int[] pre;
    public static int[] hold;
    public static boolean[] visited;

    public boolean solution(int n, int[][] path, int[][] order) {
        this.n = n;

        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0, p = path.length; i < p; i++) {
            graph[path[i][0]].add(path[i][1]);
            graph[path[i][1]].add(path[i][0]);
        }

        pre = new int[n];
        for (int i = 0, o = order.length; i < o; i++) {
            pre[order[i][1]] = order[i][0];
        }
        if (pre[0] != 0) return false;

        hold = new int[n];
        visited = new boolean[n];

        return bfs();
    }

    public boolean bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(0);

        int cnt = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();

            if (visited[cur]) continue;
            visited[cur] = true;
            cnt++;

            for (int next : graph[cur]) {
                if (visited[next]) continue;
                if (!visited[pre[next]]) {
                    hold[pre[next]] = next;
                    continue;
                }
                q.add(next);
            }

            q.add(hold[cur]);
        }
        return cnt == n;
    }
}