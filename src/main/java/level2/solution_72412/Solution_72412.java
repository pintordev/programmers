package level2.solution_72412;

import java.util.*;

class Solution_72412 {

    public static void main(String[] args) {

        Solution_72412 s = new Solution_72412();

        String[] info = {"java backend junior pizza 150",
                "python frontend senior chicken 210",
                "python frontend senior chicken 150",
                "cpp backend senior pizza 260",
                "java backend junior chicken 80",
                "python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100",
                "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150",
                "- and - and - and chicken 100",
                "- and - and - and - 150"};
        int[] result = {1, 1, 1, 1, 2, 4};
        System.out.println(Arrays.equals(s.solution(info, query), result));
    }

    public int[] solution(String[] info, String[] query) {

        Map<String, List<Integer>> map = new HashMap<>();
        for (String s : info) {
            String[] bits = s.split(" ");
            add(map, bits, "", 0);
        }

        for (String key : map.keySet()) map.get(key).sort(Comparator.reverseOrder());

        int[] result = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String[] bits = query[i].split(" and | ");
            List<Integer> list = map.getOrDefault(bits[0] + bits[1] + bits[2] + bits[3], new ArrayList<>());
            int limit = Integer.parseInt(bits[4]);
            result[i] = bs(list, limit);
        }

        return result;
    }

    public void add(Map<String, List<Integer>> map, String[] bits, String key, int count) {

        if (count == 4) {
            List<Integer> list = map.getOrDefault(key, new ArrayList<>());
            list.add(Integer.parseInt(bits[4]));
            map.put(key, list);
            return;
        }

        add(map, bits, key + "-", count + 1);
        add(map, bits, key + bits[count], count + 1);
    }

    public int bs(List<Integer> list, int limit) {

        int lb = 0, rb = list.size() - 1;
        while (lb <= rb) {
            int m = (lb + rb) / 2;
            if (list.get(m) < limit) rb = m - 1;
            else lb = m + 1;
        }
        return lb;
    }
}