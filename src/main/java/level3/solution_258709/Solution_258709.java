package level3.solution_258709;

import java.util.*;

class Solution_258709 {

    public static void main(String[] args) {

        Solution_258709 s = new Solution_258709();

        int[][][] dice = {
                {{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}},
                {{1, 2, 3, 4, 5, 6}, {2, 2, 4, 4, 6, 6}},
                {{40, 41, 42, 43, 44, 45}, {43, 43, 42, 42, 41, 41}, {1, 1, 80, 80, 80, 80}, {70, 70, 1, 1, 70, 70}}
        };
        int[][] result = {
                {1, 4},
                {2},
                {1, 3}
        };

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(Arrays.equals(s.solution(dice[i]), result[i]));
        }
    }

    public int n;
    public int[][] dice;
    public Set<Integer> comb;
    public int[][] diceA;
    public int[][] diceB;
    public List<Integer> scoreA;
    public List<Integer> scoreB;
    public int maxWin;
    public int[] result;

    public int[] solution(int[][] dice) {
        n = dice.length >> 1;
        this.dice = dice;

        comb = new HashSet<>();
        maxWin = Integer.MIN_VALUE;
        result = new int[n];
        combDice(0, 0);
        return result;
    }

    public void combDice(int depth, int s) {
        if (depth == n) {
            int win = calWin();
            if (maxWin > win) return;
            maxWin = win;
            result = comb.stream().sorted().mapToInt(i -> i + 1).toArray();
            return;
        }

        for (int i = s, t = dice.length; i < t; i++) {
            comb.add(i);
            combDice(depth + 1, i + 1);
            comb.remove(i);
        }
    }

    public int calWin() {
        calScore();
        Collections.sort(scoreB);

        int win = 0;
        for (int i = 0, t = scoreA.size(); i < t; i++) {
            int low = 0;
            int high = scoreB.size();
            while (low + 1 < high) {
                int mid = (low + high) >> 1;
                if (scoreA.get(i) > scoreB.get(mid)) low = mid;
                else high = mid;
            }
            win += low;
        }
        return win;
    }

    public void calScore() {
        diceA = new int[n][6];
        diceB = new int[n][6];
        for (int i = 0, j = 0, k = 0, t = n << 1; i < t; i++) {
            if (comb.contains(i)) diceA[j++] = dice[i];
            else diceB[k++] = dice[i];
        }

        scoreA = new ArrayList<>();
        scoreB = new ArrayList<>();
        combScore(0, 0, diceA, scoreA);
        combScore(0, 0, diceB, scoreB);
    }

    public void combScore(int depth, int sum, int[][] dice, List<Integer> score) {
        if (depth == n) {
            score.add(sum);
            return;
        }

        for (int i = 0; i < 6; i++) {
            combScore(depth + 1, sum + dice[depth][i], dice, score);
        }
    }
}