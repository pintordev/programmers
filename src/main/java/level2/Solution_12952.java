package level2;

class Solution_12952 {

    public static void main(String[] args) {

        Solution_12952 s = new Solution_12952();

        int n = 4;
        int result = 2;
        System.out.println(s.solution(n) == result);
    }

    public int solution(int n) {

        return put(new int[n], new boolean[n], 0, 0);
    }

    public int put(int[] dp, boolean[] visited, int cases, int count) {

        if (count == visited.length) return cases + 1;

        for (int j = 0; j < visited.length; j++) {
            if (!visited[j] && canPut(dp, count, j)) {
                visited[j] = true;
                dp[count] = j;
                cases = put(dp, visited, cases, count + 1);
                visited[j] = false;
            }
        }

        return cases;
    }

    public boolean canPut(int[] dp, int count, int j) {
        for (int i = 0; i < count; i++) {
            if (count - i == Math.abs(j - dp[i])) return false;
        }
        return true;
    }
}