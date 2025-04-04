package level2.solution_131127;

import java.util.HashMap;
import java.util.Map;

class Solution_131127 {

    public static void main(String[] args) {

        Solution_131127 s = new Solution_131127();

        String[][] want = {
                {"banana", "apple", "rice", "pork", "pot"},
                {"apple"}
        };
        int[][] number = {
                {3, 2, 2, 2, 1},
                {10}
        };
        String[][] discount = {
                {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"},
                {"banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana"}
        };
        int[] result = {3, 0};

        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(want[i], number[i], discount[i]) == result[i]);
        }
    }

    public int solution(String[] want, int[] number, String[] discount) {
        int count = 0;
        for (int i = 0; i < discount.length - 10 + 1; i++) {
            Map<String, Integer> discountMap = new HashMap<>();
            for (int j = i; j < i + 10; j++) {
                discountMap.put(discount[j], discountMap.getOrDefault(discount[j], 0) + 1);
            }

            boolean can = true;
            for (int j = 0; j < want.length; j++) {
                if (discountMap.getOrDefault(want[j], -1) != number[j]) {
                    can = false;
                    break;
                }
            }
            if (can) count++;
        }

        return count;
    }
}