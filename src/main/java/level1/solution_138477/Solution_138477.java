package level1;

import java.util.Arrays;
import java.util.PriorityQueue;

class Solution_138477 {

    public static void main(String[] args) {

        Solution_138477 s = new Solution_138477();

        int[] k = {3, 4};
        int[][] score = {{10, 100, 20, 150, 1, 100, 200},
                {0, 300, 40, 300, 20, 70, 150, 50, 500, 1000}};
        int[][] result = {{10, 10, 10, 20, 20, 100, 100},
                {0, 0, 0, 0, 20, 40, 70, 70, 150, 300}};
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.equals(s.solution(k[i], score[i]), result[i]));
        }
    }

    public int[] solution(int k, int[] score) {

        int n = score.length;

        PriorityQueue<Integer> hof = new PriorityQueue<>();
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            hof.add(score[i]);
            if (hof.size() > k) hof.poll();
            result[i] = hof.peek();
        }

        return result;
    }
}