package level1;

import java.util.Arrays;

class Solution_12919 {

    public static void main(String[] args) {

        Solution_12919 s = new Solution_12919();

        String[] seoul = {"Jane", "Kim"};
        String result = "김서방은 1에 있다";
        System.out.println(s.solution(seoul).equals(result));
    }

    public String solution(String[] seoul) {
        return "김서방은 "
                + Arrays.asList(seoul).indexOf("Kim")
                + "에 있다";
    }
}