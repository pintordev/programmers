package level2.solution_1835;

import java.util.HashMap;
import java.util.Map;

class Solution_1835 {

    public static void main(String[] args) {

        Solution_1835 s = new Solution_1835();

        int[] n = {2, 2};
        String[][] data = {{"N~F=0", "R~T>2"},
                {"M~C<2", "C~M>1"}};
        int[] result = {3648, 0};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(n[i], data[i]) == result[i]);
        }
    }

    public int solution(int n, String[] data) {
        char[] kf = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        return dfs(data, new HashMap<>(), kf, 0);
    }

    public int dfs(String[] data, Map<Character, Integer> map, char[] kf, int count) {

        if (map.size() == kf.length && isAllSTF(map, data)) return count + 1;

        for (int i = 0; i < kf.length; i++) {
            if (!map.containsKey(kf[i])) {
                map.put(kf[i], map.size());
                count = dfs(data, map, kf, count);
                map.remove(kf[i]);
            }
        }

        return count;
    }

    public boolean isAllSTF(Map<Character, Integer> arrange, String[] data) {
        for (String d : data) if(!isSTF(arrange, d)) return false;
        return true;
    }

    public boolean isSTF(Map<Character, Integer> arrange, String d) {

        int i = arrange.get(d.charAt(0));
        int j = arrange.get(d.charAt(2));
        char op = d.charAt(3);
        int m = d.charAt(4) - '0';

        if (op == '=') return Math.abs(j - i) == m + 1;
        else if (op == '>') return Math.abs(j - i) > m + 1;
        else return Math.abs(j - i) <= m;
    }
}