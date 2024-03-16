package level1;

class Solution_77884 {

    public static void main(String[] args) {

        Solution_77884 s = new Solution_77884();

        int[] left = {13,24};
        int[] right = {17,27};
        int[] result = {43,52};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(left[i], right[i]) == result[i]);
        }
    }

    public int solution(int left, int right) {

        int result = 0;
        for (int i = left; i <= right; i++) result += divFlag(i) * i;

        return result;
    }

    public int divFlag(int n) {

        if (n == 1 || (int) Math.sqrt(n) == Math.sqrt(n)) return -1;

        return 1;
    }
}