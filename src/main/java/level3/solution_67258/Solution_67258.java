package level3;

import java.util.*;

class Solution_67258 {

    public static void main(String[] args) {

        Solution_67258 s = new Solution_67258();

        String[][] gems = {{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"},
                {"AA", "AB", "AC", "AA", "AC"},
                {"XYZ", "XYZ", "XYZ"},
                {"ZZZ", "YYY", "NNNN", "YYY", "BBB"},
                {"A", "AA", "AA", "AAA", "AA", "A"}};
        int[][] result = {{3, 7},
                {1, 3},
                {1, 1},
                {1, 5},
                {4, 6}};
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.equals(s.solution(gems[i]), result[i]));
        }
    }

    public int[] solution(String[] gems) {

        int[] result = new int[2];
        Map<String, Integer> map = new HashMap<>();
        Queue<String> queue = new LinkedList<>();

        int len = 0, kind = 0;
        for (int i = 0; i < gems.length; i++) {

            queue.add(gems[i]);
            add(map, gems[i]);

            while (!queue.isEmpty() && map.get(queue.peek()) > 1) {
                remove(map, queue.poll());
            }

            if (kind < map.size() || (kind == map.size() && len > queue.size())) {
                len = queue.size();
                result[0] = i - len + 2;
                result[1] = i + 1;
            }
            if (kind < map.size()) kind = map.size();
        }

        return result;
    }

    public void add(Map<String, Integer> map, String s) {
        map.put(s, map.getOrDefault(s, 0) + 1);
    }

    public void remove(Map<String, Integer> map, String s) {
        map.put(s, map.get(s) - 1);
        if (map.get(s) == 0) map.remove(s);
    }
}