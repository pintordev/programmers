package level1;

class Solution_70128 {

    public static void main(String[] args) {

        Solution_70128 s = new Solution_70128();

        int[][] a = {{1, 2, 3, 4},
                {-1, 0, 1}};
        int[][] b = {{-3, -1, 0, 2},
                {1, 0, -1}};
        int[] result = {3, -2};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(a[i], b[i]) == result[i]);
        }
    }

    public int solution(int[] a, int[] b) {

        int dotProduct = 0;
        for (int i = 0; i < a.length; i++) {
            dotProduct += a[i] * b[i];
        }

        return dotProduct;
    }
}