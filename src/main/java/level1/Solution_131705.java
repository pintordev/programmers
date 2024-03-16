package level1;

class Solution_131705 {

    public static void main(String[] args) {

        Solution_131705 s = new Solution_131705();

        int[][] number = {{-2, 3, 0, 2, -5},
                {-3, -2, -1, 0, 1, 2, 3},
                {-1, 1, -1, 1}};
        int[] result = {2, 5, 0};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(number[i]) == result[i]);
        }
    }

    public int solution(int[] number) {

        int result = 0;
        for (int i = 0; i < number.length; i++) {
            for (int j = i + 1; j < number.length; j++) {
                for (int k = j + 1; k < number.length; k++) {
                    if (number[i] + number[j] + number[k] == 0) result++;
                }
            }
        }

        return result;
    }
}