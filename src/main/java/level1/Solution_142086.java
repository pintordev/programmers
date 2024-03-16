package level1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution_142086 {

    public static void main(String[] args) {

        Solution_142086 s = new Solution_142086();

        String[] _s = {"banana", "foobar"};
        int[][] result = {{-1, -1, -1, 2, 2, 2},
                {-1, -1, 1, -1, -1, -1}};
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.equals(s.solution(_s[i]), result[i]));
        }
    }

    public int[] solution(String s) {

        int[] result = new int[s.length()];
        char[] c = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < c.length; i++) {
            result[i] = i - map.getOrDefault(c[i], i + 1);
            map.put(c[i], i);
        }

        return result;
    }
}