package level2.solution_176962;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

class Solution_176962 {

    public static void main(String[] args) {

        Solution_176962 s = new Solution_176962();

        String[][][] plans = {{{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}},
                {{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"}, {"computer", "12:30", "100"}},
                {{"aaa", "12:00", "20"}, {"bbb", "12:10", "30"}, {"ccc", "12:40", "10"}}};
        String[][] result = {{"korean", "english", "math"},
                {"science", "history", "computer", "music"},
                {"bbb", "ccc", "aaa"}};
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.equals(s.solution(plans[i]), result[i]));
        }
    }

    public String[] solution(String[][] plans) {

        Arrays.sort(plans, Comparator.comparing(o -> o[1]));

        Stack<int[]> stack = new Stack<>();
        String[] result = new String[plans.length];
        int min = toMin(plans[0][1]), idx = 0, rdx = 0;
        for (String[] plan : plans) {
            int temp = toMin(plan[1]);
            int remain = temp - min;
            min = temp;
            while (!stack.isEmpty()) {
                int[] now = stack.pop();
                if (remain >= now[1]) {
                    remain -= now[1];
                    result[rdx++] = plans[now[0]][0];
                } else {
                    now[1] -= remain;
                    stack.add(now);
                    break;
                }
            }
            stack.add(new int[]{idx++, Integer.parseInt(plan[2])});
        }

        while (!stack.isEmpty()) {
            int[] now = stack.pop();
            result[rdx++] = plans[now[0]][0];
        }

        return result;
    }

    public int toMin(String s) {
        String[] sBit = s.split(":");
        return Integer.parseInt(sBit[0]) * 60 + Integer.parseInt(sBit[1]);
    }
}