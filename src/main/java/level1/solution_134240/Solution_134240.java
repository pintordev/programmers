package level1;

class Solution_134240 {

    public static void main(String[] args) {

        Solution_134240 s = new Solution_134240();

        int[][] food = {{1, 3, 4, 6},
                {1, 7, 1, 2}};
        String[] result = {"1223330333221", "111303111"};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(food[i]).equals(result[i]));
        }
    }

    public String solution(int[] food) {

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < food.length; i++) {
            for (int j = 0; j < food[i] / 2; j++) {
                sb.append(i);
            }
        }

        return new StringBuilder(sb).append(0).append(sb.reverse()).toString();
    }
}