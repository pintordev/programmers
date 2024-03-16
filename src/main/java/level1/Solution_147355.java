package level1;

class Solution_147355 {

    public static void main(String[] args) {

        Solution_147355 s = new Solution_147355();

        String[] t = {"3141592", "500220839878", "10203"};
        String[] p = {"271", "7", "15"};
        int[] result = {2, 8, 3};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(t[i], p[i]) == result[i]);
        }
    }

    public int solution(String t, String p) {

        int result = 0;
        long lp = Long.parseLong(p);
        for (int i = 0; i + p.length() <= t.length(); i++) {
            if (Long.parseLong(t.substring(i, i + p.length())) <= lp) result++;
        }

        return result;
    }
}