package level3.solution_42892;

import java.util.Arrays;

class Solution_42892 {

    public static void main(String[] args) {

        Solution_42892 s = new Solution_42892();

        int[][] nodeinfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
        int[][] result = {{7, 4, 6, 9, 1, 8, 5, 2, 3}, {9, 6, 5, 8, 1, 4, 3, 2, 7}};

        System.out.println(Arrays.deepEquals(s.solution(nodeinfo), result));
    }

    public int[][] solution(int[][] nodeinfo) {
        int n = nodeinfo.length;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) nodes[i] = new Node(i + 1, nodeinfo[i]);
        Arrays.sort(nodes);

        Node root = nodes[0];
        for (int i = 1; i < n; i++) root.add(nodes[i]);

        return new int[][]{root.preOrder(new int[n]), root.postOrder(new int[n])};
    }
}

class Node implements Comparable<Node> {

    static int preIdx = 0;
    static int postIdx = 0;

    @Override
    public int compareTo(Node o) {
        if (y == o.y) return x - o.x;
        return o.y - y;
    }

    int idx;
    int x;
    int y;
    Node left;
    Node right;

    public Node(int idx, int[] info) {
        this.idx = idx;
        this.x = info[0];
        this.y = info[1];
    }

    public void add(Node node) {
        if (node.x < x) {
            if (left == null) {
                left = node;
            } else {
                left.add(node);
            }
        } else {
            if (right == null) {
                right = node;
            } else {
                right.add(node);
            }
        }
    }

    public int[] preOrder(int[] pre) {
        pre[preIdx++] = idx;
        if (left != null) left.preOrder(pre);
        if (right != null) right.preOrder(pre);
        return pre;
    }

    public int[] postOrder(int[] pre) {
        if (left != null) left.postOrder(pre);
        if (right != null) right.postOrder(pre);
        pre[postIdx++] = idx;
        return pre;
    }
}