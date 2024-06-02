package level3.solution_60062;

class Solution_60062 {

    public static void main(String[] args) {

        Solution_60062 s = new Solution_60062();

        int[] n = {12, 12, 200};
        int[][] weak = {{1, 5, 6, 10},
                {1, 3, 4, 9, 10},
                {0, 10, 50, 80, 120, 160}};
        int[][] dist = {{1, 2, 3, 4},
                {3, 5, 7},
                {1, 10, 5, 40, 30}};
        int[] result = {2, 1, 3};

        for (int i = 0, t = n.length; i < t; i++) {
            System.out.println(s.solution(n[i], weak[i], dist[i]) == result[i]);
        }
    }

    public static int[] weak;
    public static int[] dist;
    public static boolean[] visited;
    public static int[] permutation;
    public static int min;
    public static int wLen;
    public static int dLen;

    public int solution(int n, int[] weak, int[] dist) {

        wLen = weak.length;
        this.weak = new int[wLen * 2];
        for (int i = 0; i < wLen; i++) {
            this.weak[i] = weak[i];
            this.weak[i + wLen] = weak[i] + n;
        }
        dLen = dist.length;
        this.dist = dist;
        this.visited = new boolean[dLen];
        this.permutation = new int[dLen];
        min = Integer.MAX_VALUE;

        for (int i = 0; i < wLen; i++) {
            dfs(i, 0);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public void dfs(int wdx, int depth) {
        if (depth == dLen) {
            min = Math.min(min, countFriends(wdx));
            return;
        }

        for (int i = 0; i < dLen; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            permutation[depth] = dist[i];
            dfs(wdx, depth + 1);
            visited[i] = false;
        }
    }

    public int countFriends(int wdx) {
        int cnt = 1;
        int pos = weak[wdx] + permutation[0];
        for (int i = wdx; i < wdx + wLen; i++) {
            if (pos < weak[i]) {
                cnt++;
                if (cnt > dLen) return Integer.MAX_VALUE;
                pos = weak[i] + permutation[cnt - 1];
            }
        }
        return cnt;
    }
}