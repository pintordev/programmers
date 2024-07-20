package level4.solution_12983;

class Solution_12983 {

    public static void main(String[] args) {

        Solution_12983 s = new Solution_12983();

        String[][] strs = {
                {"ba", "na", "n", "a"},
                {"app", "ap", "p", "l", "e", "ple", "pp"},
                {"ba", "an", "nan", "ban", "n"}
        };
        String[] t = {"banana", "apple", "banana"};
        int[] result = {3, 2, -1};

        for (int i = 0, _t = result.length; i < _t; i++) {
            System.out.println(s.solution(strs[i], t[i]) == result[i]);
        }
    }

    public int solution(String[] strs, String t) {
        int tLen = t.length();
        int[] memo = new int[tLen + 1];

        for (int i = 1, mLen = memo.length; i < mLen; i++) {
            for (String str : strs) {
                int sLen = str.length();
                int pre = i - sLen;

                if (pre < 0) continue;
                if (!t.substring(pre, i).equals(str)) continue;

                if (pre == 0) memo[i] = 1;
                if (memo[pre] == 0) continue;

                if (memo[i] == 0) memo[i] = memo[pre] + 1;
                else memo[i] = Math.min(memo[i], memo[pre] + 1);
            }
        }

        return memo[tLen] == 0 ? -1 : memo[tLen];
    }
}