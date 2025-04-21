package level2.solution_42584;

import java.util.Arrays;
import java.util.Stack;

class Solution_42584 {

    public static void main(String[] args) {

        Solution_42584 s = new Solution_42584();

        int[] prices = {1, 2, 3, 2, 3};
        int[] result = {4, 3, 1, 1, 0};

        System.out.println(Arrays.equals(s.solution(prices), result));
    }

    public int[] solution(int[] prices) {
        int[] result = new int[prices.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < prices.length; i++) {
            while (!stack.empty() && prices[stack.peek()] > prices[i]) {
                result[stack.peek()] = i - stack.pop();
            }
            stack.add(i);
        }

        while(!stack.empty()) {
            result[stack.peek()] = prices.length - 1 - stack.pop();
        }

        return result;
    }
}