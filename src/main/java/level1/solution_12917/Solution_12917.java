package level1;

import java.util.Arrays;

class Solution_12917 {

    public static void main(String[] args) {

        Solution_12917 s = new Solution_12917();

        String _s = "Zbcdefg";
        String result = "gfedcbZ";
        System.out.println(s.solution(_s).equals(result));
    }

    public String solution(String s) {

        char[] c = s.toCharArray();
        Arrays.sort(c);

        return new StringBuilder().append(c).reverse().toString();
    }
}