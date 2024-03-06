package level3;

class Solution_43105 {

    public static void main(String[] args) {

        Solution_43105 s = new Solution_43105();

        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        int result = 30;
        System.out.println(s.solution(triangle) == result);
    }

    public int solution(int[][] triangle) {
        return max(triangle, triangle.length - 2);
    }

    public int max(int[][] triangle, int i) {
        for (int j = 0; j < triangle[i].length; j++) {
            triangle[i][j] += Math.max(triangle[i + 1][j], triangle[i + 1][j + 1]);
        }
        return i == 0 ? triangle[0][0] : max(triangle, i - 1);
    }
}