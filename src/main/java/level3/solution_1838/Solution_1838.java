package level3.solution_1838;

import java.util.HashSet;
import java.util.Set;

class Solution_1838 {

    public static void main(String[] args) {

        Solution_1838 s = new Solution_1838();

        int[] n = {3, 2, 2, 4};
        int[] m = {2, 1, 2, 5};
        int[][][] timetable = {
                {{1170, 1210}, {1200, 1260}},
                {{840, 900}},
                {{600, 630}, {630, 700}},
                {{1140, 1200}, {1150, 1200}, {1100, 1200}, {1210, 1300}, {1220, 1280}}
        };
        int[] result = {4, 0, 2, 4};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(n[i], m[i], timetable[i]) == result[i]);
        }
    }

    public int solution(int n, int m, int[][] timetable) {
        int max = calMax(timetable);
        if (max == 1) return 0;
        return calDist(n, max);
    }

    public int calMax(int[][] timetable) {
        int[] cumulative = new int[722];
        for (int[] time : timetable) {
            cumulative[time[0] - 600]++;
            cumulative[time[1] - 600 + 1]--;
        }

        int max = 1;
        for (int i = 1; i < 722; i++) {
            cumulative[i] += cumulative[i - 1];
            max = Math.max(max, cumulative[i]);
        }
        return max;
    }

    public int calDist(int n, int max) {
        for (int d = 2 * (n - 1); d > 0; d--) {
            for (int j = 0; j < n; j++) {
                if (calCnt(j, n, d) >= max) return d;
            }
        }
        return -1;
    }

    public int calCnt(int sc, int n, int d) {
        Set<Node> nodes = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j < sc) continue;
                if (!canPut(nodes, i, j, d)) continue;
                nodes.add(new Node(i, j));
            }
        }
        return nodes.size();
    }

    public boolean canPut(Set<Node> nodes, int r, int c, int d) {
        for (Node node : nodes) {
            if (Math.abs(node.r - r) + Math.abs(node.c - c) < d) return false;
        }
        return true;
    }
}

class Node {
    int r;
    int c;

    Node(int r, int c) {
        this.r = r;
        this.c = c;
    }
}