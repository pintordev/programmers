package level1;

class Solution_12912 {

    public static void main(String[] args) {

        Solution_12912 s = new Solution_12912();

        int[] a = {3, 3, 5};
        int[] b = {5, 3, 3};
        int[] result = {12, 3, 12};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(a[i], b[i]) == result[i]);
        }
    }

    public long solution(int a, int b) {

        long max = Math.max(a, b);
        long min = Math.min(a, b);

        return max * (max + 1) / 2
                - min * (min - 1) / 2;
    }
}