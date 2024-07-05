package level3.solution_131702;

class Solution_131702 {

    public static void main(String[] args) {

        Solution_131702 s = new Solution_131702();

        int[][] clockHands = {{0,3,3,0},{3,2,2,3},{0,3,2,0},{0,3,3,3}};
        int result = 3;

        System.out.println(s.solution(clockHands) == result);
    }

    public static int n;
    public static int[][] clockHands;
    public static int[][] copy;
    public static int cnt;
    public static int[] dr = {0, 0, 0, 1, -1};
    public static int[] dc = {0, 1, -1, 0, 0};

    public int solution(int[][] clockHands) {
        n = clockHands.length;
        this.clockHands = clockHands;
        for (int i = 0; i < (1 << 2 * n); i++) {
            cnt = 0;
            deepCopy();
            turnFirstRow(i);
            turnOtherRow();
            if (isAllTwelve()) return cnt;
        }
        return -1;
    }

    public void deepCopy() {
        copy = new int[n][];
        for (int i = 0; i < n; i++) {
            copy[i] = clockHands[i].clone();
        }
    }

    public void turnFirstRow(int i) {
        for (int j = 0; j < n; j++) {
            int turn = (i >> (2 * j)) & 3;
            turnSurround(0, j, turn);
            cnt += turn;
        }
    }

    public void turnOtherRow() {
        for (int j = 1; j < n; j++) {
            for (int k = 0; k < n; k++) {
                int turn = (4 - copy[j - 1][k]) % 4;
                turnSurround(j, k, turn);
                cnt += turn;
            }
        }
    }

    public void turnSurround(int r, int c, int turn) {
        for (int i = 0; i < 5; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
            copy[nr][nc] += turn;
            copy[nr][nc] %= 4;
        }
    }

    public boolean isAllTwelve() {
        for (int i = 0; i < n; i++) {
            if (copy[n - 1][i] != 0) return false;
        }
        return true;
    }
}