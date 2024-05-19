package level3;

class Solution_43162 {

    public static void main(String[] args) {

        Solution_43162 s = new Solution_43162();

        int[] n = {3, 3};
        int[][][] computers = {{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}},
                {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}};
        int[] result = {2, 1};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(n[i], computers[i]) == result[i]);
        }
    }

    public int solution(int n, int[][] computers) {

        boolean[] visited = new boolean[n];

        int network = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                network++;
                dfs(n, computers, visited, i);
            }
        }

        return network;
    }

    public void dfs(int n, int[][] computers, boolean[] visited, int i) {
        visited[i] = true;
        for (int j = 0; j < n; j++) {
            if (j != i && !visited[j] && computers[i][j] == 1) {
                dfs(n, computers, visited, j);
            }
        }
    }
}