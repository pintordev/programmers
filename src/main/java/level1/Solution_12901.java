package level1;

class Solution_12901 {

    public static void main(String[] args) {

        Solution_12901 s = new Solution_12901();

        int a = 5;
        int b = 24;
        String result = "TUE";
        System.out.println(s.solution(a, b).equals(result));
    }

    public String solution(int a, int b) {

        String[] days = {"THU", "FRI", "SAT", "SUN", "MON", "TUE", "WED"};
        int[] dates = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int allDate = 0;

        for (int i = 0; i < a - 1; i++) {
            allDate += dates[i];
        }
        allDate += b;

        return days[allDate % days.length];
    }
}