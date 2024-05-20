package level3.solution_49191;

class Solution_49191 {

    public static void main(String[] args) {

        Solution_49191 s = new Solution_49191();

        int n = 5;
        int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
        int result = 2;

        System.out.println(s.solution(n, results) == result);
    }

    public static final int INF = Integer.MAX_VALUE;

    public int solution(int n, int[][] results) {

        int[][] graph = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) graph[i][j] = INF;
            graph[i][i] = 0;
        }

        for (int[] result : results) graph[result[0]][result[1]] = 1;

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (graph[i][k] == INF || graph[k][j] == INF) continue;
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        int answer = n;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] == INF && graph[j][i] == INF) {
                    answer--;
                    break;
                }
            }
        }
        return answer;
    }
}