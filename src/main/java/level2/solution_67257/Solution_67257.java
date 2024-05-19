package level2.solution_67257;

import java.util.*;

class Solution_67257 {

    public static void main(String[] args) {

        Solution_67257 s = new Solution_67257();

        String[] expression = {"100-200*300-500+20", "50*6-3*2"};
        long[] result = {60420, 300};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(expression[i]) == result[i]);
        }
    }

    public long solution(String expression) {

        expression = expression.replaceAll("-", "m");
        char[] operator = getOperator(expression);
        List<char[]> orders = new ArrayList<>();
        getOrder(operator, orders, "", new boolean[operator.length]);

        long sum = 0;
        for (char[] order : orders) {

            String[] eq = expression.split("(?<=[*+m])|(?=[*+m])");

            for (char o : order) {

                Stack<String> eqStack = new Stack<>();
                for (int i = 0; i < eq.length; i++) {
                    if (eq[i].equals(o + "")) eqStack.push(calculate(eqStack.pop(), o, eq[++i]));
                    else eqStack.push(eq[i]);
                }

                int i = eqStack.size();
                eq = new String[i];
                while(!eqStack.isEmpty()) {
                    eq[--i] = eqStack.pop();
                }
            }

            sum = Math.max(sum, Math.abs(Long.parseLong(eq[0])));
        }

        return sum;
    }

    public char[] getOperator(String expression) {
        char[] operator = new char[3];
        int i = 0;
        if (expression.contains("*")) operator[i++] = '*';
        if (expression.contains("+")) operator[i++] = '+';
        if (expression.contains("m")) operator[i++] = 'm';
        return Arrays.copyOf(operator, i);
    }

    public void getOrder(char[] operator, List<char[]> orders, String order, boolean[] visited) {

        if (order.length() == operator.length) {
            orders.add(order.toCharArray());
            return;
        }

        for (int i = 0; i < operator.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                getOrder(operator, orders, order + operator[i], visited);
                visited[i] = false;
            }
        }
    }

    public String calculate(String a, char o, String b) {
        if (o == '*') return Long.parseLong(a) * Long.parseLong(b) + "";
        else if (o == '+') return Long.parseLong(a) + Long.parseLong(b) + "";
        else return Long.parseLong(a) - Long.parseLong(b) + "";
    }
}
