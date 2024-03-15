package level1;

class Solution_76501 {

    public static void main(String[] args) {

        Solution_76501 s = new Solution_76501();

        int[][] absolutes = {{4, 7, 12},
                {1, 2, 3}};
        boolean[][] signs = {{true, false, true},
                {false, false, true}};
        int[] result = {9, 0};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(absolutes[i], signs[i]) == result[i]);
        }
    }

    public int solution(int[] absolutes, boolean[] signs) {

        int result = 0;
        for (int i = 0; i < absolutes.length; i++) {
            result += (signs[i] ? 1 : -1) * absolutes[i];
        }

        return result;
    }
}