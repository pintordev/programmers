package level3.solution_150365;

class Solution_150365 {

    public static void main(String[] args) {

        Solution_150365 s = new Solution_150365();

        int[] n = {3, 2, 3};
        int[] m = {4, 2, 3};
        int[] x = {2, 1, 1};
        int[] y = {3, 1, 2};
        int[] r = {3, 2, 3};
        int[] c = {1, 2, 3};
        int[] k = {5, 2, 4};
        String[] result = {"dllrl", "dr", "impossible"};

        for (int i = 0, t = n.length; i < t; i++) {
            System.out.println(s.solution(n[i], m[i], x[i], y[i], r[i], c[i], k[i]).equals(result[i]));
        }
    }

    public static String result;
    public static int n;
    public static int m;
    public static int r;
    public static int c;
    public static int k;
    public static char[] dir = {'d', 'l', 'r', 'u'};
    public static int[] dx = {1, 0, 0, -1};
    public static int[] dy = {0, -1, 1, 0};

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        result = "impossible";
        int dis = distance(x, y, r, c);
        if (dis > k || (k - dis) % 2 == 1) return result;

        this.n = n;
        this.m = m;
        this.r = r;
        this.c = c;
        this.k = k;

        dfs(x, y, new StringBuilder());
        return result;
    }

    public void dfs(int x, int y, StringBuilder sb) {
        if (!result.equals("impossible")) return;
        if (sb.length() == k) {
            if (x == r && y == c) result = sb.toString();
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 1 || nx > n || ny < 1 || ny > m) continue;
            if (distance(nx, ny, r, c) > k - sb.length()) continue;

            sb.append(dir[i]);
            dfs(nx, ny, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public int distance(int x, int y, int r, int c) {
        return Math.abs(x - r) + Math.abs(y - c);
    }
}