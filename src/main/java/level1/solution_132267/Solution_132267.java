package level1;

class Solution_132267 {

    public static void main(String[] args) {

        Solution_132267 s = new Solution_132267();

        int[] a = {2, 3};
        int[] b = {1, 1};
        int[] n = {20, 20};
        int[] result = {19, 9};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(a[i], b[i], n[i]) == result[i]);
        }
    }

    public int solution(int a, int b, int n) {

        int result = 0;
        while (n / a > 0) {
            result += n / a * b;
            n = n / a * b + n % a;
        }

        return result;
    }
}