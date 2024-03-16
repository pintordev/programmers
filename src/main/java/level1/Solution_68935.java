package level1;

class Solution_68935 {

    public static void main(String[] args) {

        Solution_68935 s = new Solution_68935();

        int[] n = {45, 125};
        int[] result = {7, 229};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(n[i]) == result[i]);
        }
    }

    public int solution(int n) {

        String n3 = Integer.toString(n, 3);
        StringBuffer sb = new StringBuffer(n3);

        return Integer.parseInt(sb.reverse().toString(), 3);
    }
}