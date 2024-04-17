package level3;

import java.util.*;

class Solution_49189 {

    public static void main(String[] args) {

        Solution_49189 s = new Solution_49189();

        int n = 6;
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        int result = 3;
        System.out.println(s.solution(n, edge) == result);
    }

    public int solution(int n, int[][] edge) {

        List<Integer>[] map = new List[n + 1];
        for (int i = 1; i <= n; i++) map[i] = new ArrayList<>();
        for (int[] e : edge) {
            map[e[0]].add(e[1]);
            map[e[1]].add(e[0]);
        }

        int[] node = new int[n + 1];
        node[1] = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);

        int max = 0;
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int i : map[now]) {
                if (node[i] == 0) {
                    node[i] = node[now] + 1;
                    queue.add(i);
                }
            }
            max = Math.max(max, node[now]);
        }

        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (node[i] == max) result++;
        }

        return result;
    }
}