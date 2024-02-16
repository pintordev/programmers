package level2.solution_60058;

import java.util.Stack;

class Solution {

    public static void main(String[] args) {

        Solution s = new Solution();

        String[] p = {"(()())()", ")(", "()))((()"};
        String[] result = {"(()())()", "()", "()(())()"};
        for (int i = 0; i < p.length; i++) {
            System.out.println(s.solution(p[i]).equals(result[i]));
        }
    }

    public String solution(String p) {
        return convert(p);
    }

    public String convert(String p) {

        // step 1
        if (p.isEmpty()) return p;

        // step 2
        int m = sdx(p);
        String u = p.substring(0, m), v = p.substring(m);

        // step 3
        if (isRight(u)) return u + convert(v);

        // step 4
        StringBuilder sb = new StringBuilder();
        sb.append('(')
                .append(convert(v))
                .append(')');

        if (u.isEmpty()) return sb.toString();

        for (char c : u.substring(1, u.length() - 1).toCharArray()) {
            sb.append(c == '(' ? ')' : '(');
        }
        return sb.toString();
    }

    public int sdx(String p) {
        char[] ps = p.toCharArray();
        int c = 0;
        for (int i = 0; i < ps.length; i++) {
            c = ps[i] == '(' ? c - 1 : c + 1;
            if (c == 0) return i + 1;
        }
        return 0;
    }

    public boolean isRight(String u) {
        Stack<Character> stack = new Stack<>();
        for (char c : u.toCharArray()) {
            if (!stack.isEmpty() && (stack.peek() == '(' && c == ')')) stack.pop();
            else stack.add(c);
        }
        return stack.isEmpty();
    }
}