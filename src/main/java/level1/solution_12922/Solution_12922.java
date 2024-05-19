package level1;

class Solution_12922 {

    public static void main(String[] args) {

        Solution_12922 s = new Solution_12922();

        int[] n = {3, 4};
        String[] result = {"수박수", "수박수박"};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(n[i]).equals(result[i]));
        }
    }

    public String solution(int n) {

        char[] c = new char[n];
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) c[i] = '수';
            else c[i] = '박';
        }

        return new String(c);
    }
}