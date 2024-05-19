package level2.solution_42860;

class Solution_42860 {

    public static void main(String[] args) {

        Solution_42860 s = new Solution_42860();

        String[] name = {"JEROEN", "JAN", "AAAABBBBAA"};
        int[] result = {56, 23, 10};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(name[i]) == result[i]);
        }
    }

    public int solution(String name) {

        int len = name.length();
        int ud = 0, lr = len - 1;
        for (int i = 0; i < len; i++) {

            char c = name.charAt(i);
            ud += Math.min(c - 'A', 'Z' - c + 1);

            int e = i + 1;
            while (e < len && name.charAt(e) == 'A') e++;
            lr = Math.min(lr, i + len - e + Math.min(i, len - e));
        }

        return ud + lr;
    }
}