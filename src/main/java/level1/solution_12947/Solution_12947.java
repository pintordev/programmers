package level1;

class Solution_12947 {

    public static void main(String[] args) {

        Solution_12947 s = new Solution_12947();

        int[] x = {10, 12, 11, 13};
        boolean[] result = {true, true, false, false};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(x[i]) == result[i]);
        }
    }

    public boolean solution(int x) {

        int sum = 0, num = x;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return x % sum == 0;
    }
}