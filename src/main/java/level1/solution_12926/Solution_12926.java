package level1;

class Solution_12926 {

    public static void main(String[] args) {

        Solution_12926 s = new Solution_12926();

        String[] _s = {"AB", "z", "a B z"};
        int[] n = {1, 1, 4};
        String[] result = {"BC", "a", "e F d"};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(_s[i], n[i]).equals(result[i]));
        }
    }

    public String solution(String s, int n) {

        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') continue;
            if (c[i] < 97) c[i] = (char) ((c[i] + n - 'A') % 26 + 'A');
            else c[i] = (char) ((c[i] + n - 'a') % 26 + 'a');
        }

        return new String(c);
    }
}