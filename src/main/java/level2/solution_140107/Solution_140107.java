package level2.solution_140107;

class Solution_140107 {

    public static void main(String[] args) {

        Solution_140107 s = new Solution_140107();

        int[] k = {2, 1};
        int[] d = {4, 5};
        long[] result = {6, 26};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(k[i], d[i]) == result[i]);
        }
    }

    public long solution(int k, int d) {

        long count = 0;
        long dd = (long) d * d;
        for (long i = 0; i <= d; i += k) count += (long) Math.floor(Math.sqrt(dd - i * i)) / k + 1;

        return count;
    }
}