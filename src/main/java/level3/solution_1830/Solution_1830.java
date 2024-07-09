package level3.solution_1830;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

class Solution_1830 {

    public static void main(String[] args) {

        Solution_1830 s = new Solution_1830();

        String[] sentence = {"HaEaLaLaObWORLDb", "SpIpGpOpNpGJqOqA", "AxAxAxAoBoBoB"};
        String[] result = {"HELLO WORLD", "SIGONG JOA", "invalid"};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(sentence[i]).equals(result[i]));
        }
    }

    public String solution(String sentence) {
        int len = sentence.length();
        LinkedHashMap<Character, List<Integer>> map = new LinkedHashMap<>();
        for (int i = 0; i < len; i++) {
            char c = sentence.charAt(i);
            if (c < 'a' || c > 'z') continue;
            var value = map.putIfAbsent(c, new ArrayList<>(List.of(i)));
            if (value != null) value.add(i);
        }

        StringBuilder sb = new StringBuilder();
        for (var value : map.values()) {
            int cnt = value.size();
            if (cnt == 0) continue;
            if (!isValid(value, cnt)) return "invalid";
            if (cnt == 1 || cnt > 2) {
                if (!isValid(len, value, cnt)) return "invalid";
                sb.append(extract(sentence, value, 1));
            }
        }
        System.out.println(sb);
        return sb.toString();
    }

    public boolean isValid(List<Integer> idx, int cnt) {
        for (int i = 0; i < cnt - 1; i++) {
            int dist = idx.get(i + 1) - idx.get(i);
            if (dist < 2) return false;
            if (cnt > 2 && dist > 2) return false;
        }
        return true;
    }

    public boolean isValid(int len, List<Integer> idx, int cnt) {
        if (idx.get(0) <= 0 || idx.get(cnt - 1) >= len - 1) return false;
        return true;
    }

    public String extract(String sentence, List<Integer> idx, int rule) {
        StringBuilder sb = new StringBuilder();
        if (rule == 1) {
            int s = idx.get(0) - 1;
            int e = idx.get(idx.size() - 1) + 1;
            System.out.println(s + " " + e);
            for (int i = s; i < e; i += 2) {
                sb.append(sentence.charAt(i));
            }
        } else {
            for (int i = 0, t = idx.size(); i < t; i++) {
                if (i % 2 == 0) {
                    sb.append(sentence.charAt(idx.get(i)));
                }
            }
        }
        return sb.toString();
    }
}