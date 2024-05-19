package level3;

class Solution_161988 {

    public static void main(String[] args) {

        Solution_161988 s = new Solution_161988();

        int[] sequence = {2, 3, -6, 1, 3, -1, 2, 4};
        int result = 10;
        System.out.println(s.solution(sequence) == result);
    }

    public long solution(int[] sequence) {
        int n = sequence.length;

        long max = 0, memoA = 0, memoB = 0;
        int factor = 1;
        for (int i = 0; i < n; i++) {
            int now = factor * sequence[i];
            memoA = Math.max(memoA + now, now);
            memoB = Math.max(memoB - now, -now);
            max = Math.max(max, Math.max(memoA, memoB));
            factor *= -1;
        }

        return max;
    }
}