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
        String invalid = "invalid";
        int len = sentence.length();

        LinkedHashMap<Character, List<Integer>> charIdx = new LinkedHashMap<>();
        for (int i = 0; i < len; i++) {
            char c = sentence.charAt(i);
            if (c < 'a' || c > 'z') continue;
            var value = charIdx.putIfAbsent(c, new ArrayList<>(List.of(i)));
            if (value != null) value.add(i);
        }

        StringBuilder sb = new StringBuilder();
        int preRule = -1, preStart = -1, preLast = -1;
        int nowRule = 0, nowStart = 0, nowLast = 0;
        for (var idx : charIdx.values()) {
            int cnt = idx.size();
            int sdx = idx.get(0), edx = idx.get(cnt - 1);

            if (cnt == 1 || cnt >= 3) {
                for (int i = 0; i < cnt - 1; i++) {
                    if (idx.get(i + 1) - idx.get(i) != 2) return invalid;
                }
                nowRule = 1;
            } else if (cnt == 2) {
                int dist = edx - sdx;
                if (dist == 2 && preStart < sdx && edx < preLast) nowRule = 1;
                else if (dist >= 2) nowRule = 2;
                else return invalid;
            }

            if (nowRule == 1) {
                nowStart = sdx - 1;
                nowLast = edx + 1;
                if (preStart < nowStart && nowLast < preLast) {
                    if (preRule == 2) continue;
                    else return invalid;
                }
                if (nowStart < 0 || nowLast >= len) return invalid;
            } else if (nowRule == 2) {
                nowStart = sdx;
                nowLast = edx;
                if (preStart < nowStart && nowLast < preLast) return invalid;
            }

            if (preLast >= nowStart) return invalid;

            sb.append(extract(sentence, preLast + 1, nowStart - 1));
            sb.append(extract(sentence, nowStart, nowLast));

            preRule = nowRule;
            preStart = nowStart;
            preLast = nowLast;
        }

        sb.append(extract(sentence, preLast + 1, len - 1));
        return sb.toString().trim();
    }

    public String extract(String sentence, int s, int e) {
        StringBuilder sb = new StringBuilder();
        for (int i = s; i <= e; i++) {
            char c = sentence.charAt(i);
            if (c < 'a') sb.append(c);
        }
        if (sb.length() > 0) sb.append(' ');
        return sb.toString();
    }
}