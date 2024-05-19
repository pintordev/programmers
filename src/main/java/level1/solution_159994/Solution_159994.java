package level1;

class Solution_159994 {

    public static void main(String[] args) {

        Solution_159994 s = new Solution_159994();

        String[][] cards1 = {{"i", "drink", "water"},
                {"i", "water", "drink"}};
        String[][] cards2 = {{"want", "to"},
                {"want", "to"}};
        String[][] goal = {{"i", "want", "to", "drink", "water"},
                {"i", "want", "to", "drink", "water"}};
        String[] result = {"Yes", "No"};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(cards1[i], cards2[i], goal[i]).equals(result[i]));
        }
    }

    public String solution(String[] cards1, String[] cards2, String[] goal) {

        int c1dx = 0, c2dx = 0;
        for (int i = 0; i < goal.length; i++) {
            if (c1dx < cards1.length && cards1[c1dx].equals(goal[i])) c1dx++;
            else if (c2dx < cards2.length && cards2[c2dx].equals(goal[i])) c2dx++;
            else return "No";
        }

        return "Yes";
    }
}