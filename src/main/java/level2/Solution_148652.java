package level2;

class Solution_148652 {

    public static void main(String[] args) {

        Solution_148652 s = new Solution_148652();

        int n = 2;
        long l = 4;
        long r = 17;
        int result = 8;
        System.out.println(s.solution(n, l, r) == result);
    }

    public int solution(int n, long l, long r) {

        int count = 0;
        for (long i = l; i <= r; i++) {
            if (isOne(n, i)) count++;
        }

        return count;
    }

    public boolean isOne(int n, long now) {
        if (n == 0) return true;
        if (now % 5 == 3) return false;
        return isOne(n - 1, (now + 4) / 5);
    }
}