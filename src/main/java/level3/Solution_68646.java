package level3;

class Solution_68646 {

    public static void main(String[] args) {

        Solution_68646 s = new Solution_68646();

        int[][] a = {{9, -1, -5},
                {-16, 27, 65, -2, 58, -92, -71, -68, -61, -33}};
        int[] result = {3, 6};

        int t = a.length;
        for (int i = 0; i < t; i++) {
            System.out.println(s.solution(a[i]) == result[i]);
        }
    }

    public int solution(int[] a) {
        int n = a.length;
        if (n < 3) return n;

        int[] left = new int[n - 2];
        int[] right = new int[n - 2];
        left[0] = a[0];
        right[n - 3] = a[n - 1];
        for (int i = 1; i < n - 2; i++) {
            left[i] = Math.min(left[i - 1], a[i]);
            right[n - 3 - i] = Math.min(right[n - 3 - i + 1], a[n - 1 - i]);
        }

        int result = 2;
        for (int i = 1; i < n - 1; i++) {
            if (a[i] < left[i - 1] || a[i] < right[i - 1]) result++;
        }
        return result;
    }
}