package level1;

class Solution_12943 {

    public static void main(String[] args) {

        Solution_12943 s = new Solution_12943();

        int[] num = {6, 16, 626331};
        int[] result = {8, 4, -1};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(num[i]) == result[i]);
        }
    }

    public int solution(int num) {

        long n = num;

        int iter = 1;
        while (n > 1 && iter <= 500) {
            n = n % 2 == 0 ? n / 2 : n * 3 + 1;
            iter++;
        }

        return n == 1 ? iter - 1 : -1;
    }
}