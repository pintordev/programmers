package level2;

class Solution_250135 {

    public static void main(String[] args) {

        Solution_250135 s = new Solution_250135();

        int[] h1 = {0, 12, 0, 11, 11, 1, 0};
        int[] m1 = {5, 0, 6, 59, 58, 5, 0};
        int[] s1 = {30, 0, 1, 30, 59, 5, 0};
        int[] h2 = {0, 12, 0, 12, 11, 1, 23};
        int[] m2 = {7, 0, 6, 0, 59, 5, 59};
        int[] s2 = {0, 30, 6, 0, 0, 6, 59};
        int[] result = {2, 1, 0, 1, 1, 2, 2852};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(h1[i], m1[i], s1[i], h2[i], m2[i], s2[i]) == result[i]);
        }
    }

    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {

        double ds1 = h1 * 3600 + m1 * 60 + s1;
        double ds2 = h2 * 3600 + m2 * 60 + s2;

        int count = 0;
        count += (int) (ds2 / 360 / 120 * 719)
                - (int) (ds1 / 360 / 120 * 719)
                + (ds1 == 0 || ds1 == 43200 ? 1 : 0);
        count += (int) (ds2 / 360 / 10 * 59)
                - (int) (ds1 / 360 / 10 * 59)
                + (ds1 == 0 || ds1 == 43200 ? 1 : 0);
        if (ds1 <= 0 && ds2 >= 0) count--;
        if (ds1 <= 43200 && ds2 >= 43200) count--;

        return count;
    }
}