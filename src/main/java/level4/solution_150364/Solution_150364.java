package level4.solution_150364;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution_150364 {

    public static void main(String[] args) {

        Solution_150364 s = new Solution_150364();

        int[][][] edges = {
                {{2, 4}, {1, 2}, {6, 8}, {1, 3}, {5, 7}, {2, 5}, {3, 6}, {6, 10}, {6, 9}},
                {{1, 2}, {1, 3}},
                {{1, 3}, {1, 2}}
        };
        int[][] target = {
                {0, 0, 0, 3, 0, 0, 5, 1, 2, 3},
                {0, 7, 3},
                {0, 7, 1}
        };
        int[][] result = {
                {1, 1, 2, 2, 2, 3, 3},
                {1, 1, 3, 2, 3},
                {-1}
        };

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(Arrays.equals(s.solution(edges[i], target[i]), result[i]));
        }
    }

    public int[] solution(int[][] edges, int[] target) {
        int n = target.length;
        Node[] nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i, target[i - 1]);
        }

        for (int[] edge : edges) {
            nodes[edge[0]].add(nodes[edge[1]]);
        }

        int leafCnt = 0;
        for (int i = 1; i <= n; i++) {
            if (!nodes[i].isParent && nodes[i].max > 0) leafCnt++;
            nodes[i].sort();
        }

        List<Node> drops = new ArrayList<>();
        while (leafCnt > 0) {
            Node drop = nodes[1].drop();
            if (drop.cnt > drop.max) return new int[]{-1};
            if (!drop.made && drop.cnt >= drop.min) {
                drop.made = true;
                leafCnt--;
            }
            drops.add(drop);
        }

        int m = drops.size();
        int[] result = new int[m];
        for (int i = m - 1; i >= 0; i--) {
            Node drop = drops.get(i);
            for (int j = 3; j >= 1; j--) {
                if (drop.max - j < drop.cnt - 1) continue;
                drop.max -= j;
                drop.cnt--;
                result[i] = j;
                break;
            }
        }
        return result;
    }
}

class Node implements Comparable<Node> {
    int idx;
    int cnt;
    int max;
    int min;
    boolean isParent;
    boolean made;
    List<Node> children;
    int size;

    public Node(int idx, int target) {
        this.idx = idx;
        this.cnt = 0;
        this.max = target;
        this.min = target / 3 + target % 3 / 2 + target % 3 % 2;
        this.children = new ArrayList<>();
    }

    public void add(Node child) {
        isParent = true;
        children.add(child);
    }

    public void sort() {
        size = children.size();
        Collections.sort(children);
    }

    public Node drop() {
        cnt++;
        if (isParent) return children.get((cnt - 1) % size).drop();
        return this;
    }

    @Override
    public int compareTo(Node o) {
        return this.idx - o.idx;
    }
}