package level2.solution_181187;

class Solution_181187 {

    public static void main(String[] args) {

        Solution_181187 s = new Solution_181187();

        int r1 = 2;
        int r2 = 3;
        int result = 20;
        System.out.println(s.solution(r1, r2) == result);
    }

    public long solution(int r1, int r2) {

        long l1 = r1, l2 = r2;
        long count = 0;
        for (long i = 1; i <= r2; i++) {
            count += Math.floor(Math.sqrt(l2 * l2 - i * i))
                    - (i < l1 ? Math.ceil(Math.sqrt(l1 * l1 - i * i)) : 0)
                    + 1;
        }

        return count * 4;
    }
}