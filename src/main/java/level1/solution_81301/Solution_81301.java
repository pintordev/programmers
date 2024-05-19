package level1;

class Solution_81301 {

    public static void main(String[] args) {

        Solution_81301 s = new Solution_81301();

        String[] _s = {"one4seveneight", "23four5six7", "2three45sixseven", "123"};
        int[] result = {1478, 234567, 234567, 123};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(_s[i]) == result[i]);
        }
    }

    public int solution(String s) {

        String[] numbers = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        for (int i = 0; i < numbers.length; i++) {
            s = s.replaceAll(numbers[i], i + "");
        }

        return Integer.parseInt(s);
    }
}