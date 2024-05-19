package level1;

class Solution_12918 {

    public static void main(String[] args) {

        Solution_12918 s = new Solution_12918();

        String[] _s = {"a234", "1234"};
        boolean[] result = {false, true};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(_s[i]) == result[i]);
        }
    }

    public boolean solution(String s) {

        return (s.length() == 4 || s.length() == 6)
                && s.replaceAll("[0-9]", "").isEmpty();
    }
}