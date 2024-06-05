package level3.solution_70130;

class Solution_70130 {

    public static void main(String[] args) {

        Solution_70130 s = new Solution_70130();

        int[][] a = {{0},
                {5, 2, 3, 3, 5, 3},
                {0, 3, 3, 0, 7, 2, 0, 2, 2, 0}};
        int[] result = {0, 4, 8};

        for (int i = 0, t = a.length; i < t; i++) {
            System.out.println(s.solution(a[i]) == result[i]);
        }
    }

    public int solution(int[] a) {
        int n = a.length;
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            cnt[a[i]]++;
        }

        int len = 0;
        for (int i = 0; i < n; i++) {
            if (cnt[i] <= len) continue;
            int localLen = 0;
            for (int j = 0; j < n - 1; j++) {
                if (a[j] != i && a[j + 1] != i) continue;
                if (a[j] == a[j + 1]) continue;
                localLen++;
                j++;
            }
            len = Math.max(len, localLen);
        }
        return len * 2;
    }
}