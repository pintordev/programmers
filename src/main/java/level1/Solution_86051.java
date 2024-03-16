package level1;

class Solution_86051 {

    public static void main(String[] args) {

        Solution_86051 s = new Solution_86051();

        int[][] numbers = {{1, 2, 3, 4, 6, 7, 8, 0},
                {5, 8, 4, 0, 6, 7, 9}};
        int[] result = {14, 6};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(numbers[i]) == result[i]);
        }
    }

    public int solution(int[] numbers) {

        int result = 45;
        for (int num : numbers) result -= num;

        return result;
    }
}