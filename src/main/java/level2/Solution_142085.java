package level2;

import java.util.PriorityQueue;

class Solution_142085 {

    public static void main(String[] args) {

        Solution_142085 s = new Solution_142085();

        int[] n = {7, 2};
        int[] k = {3, 4};
        int[][] enemy = {{4, 2, 4, 5, 3, 3, 1},
                {3, 3, 3, 3}};
        int[] result = {5, 4};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(n[i], k[i], enemy[i]) == result[i]);
        }
    }

    public int solution(int n, int k, int[] enemy) {

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < enemy.length; i++) {
            if (k-- > 0) queue.add(enemy[i]);
            else if (enemy[i] <= queue.peek()) n -= enemy[i];
            else {
                n -= queue.poll();
                queue.add(enemy[i]);
            }
            if (n == 0) return i + 1;
            else if (n < 0) return i;
        }

        return enemy.length;
    }
}