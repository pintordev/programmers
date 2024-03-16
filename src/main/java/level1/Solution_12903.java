package level1;

class Solution_12903 {

    public static void main(String[] args) {

        Solution_12903 s = new Solution_12903();

        String[] _s = {"abcde", "qwer"};
        String[] result = {"c", "we"};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(_s[i]).equals(result[i]));
        }
    }

    public String solution(String s) {

        if (s.length() % 2 == 1) {
            return s.substring(s.length() / 2, s.length() / 2 + 1);
        } else {
            return s.substring(s.length() / 2 - 1, s.length() / 2 + 1);
        }
    }
}