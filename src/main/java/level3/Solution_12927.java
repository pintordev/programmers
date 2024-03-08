package level3;

import java.util.Comparator;
import java.util.PriorityQueue;

class Solution_12927 {

    public static void main(String[] args) {

        Solution_12927 s = new Solution_12927();

        int[] n = {4, 1, 3};
        int[][] works = {{4, 3, 3},
                {2, 1, 2},
                {1, 1}};
        long[] result = {12, 6, 0};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(n[i], works[i]) == result[i]);
        }
    }

    public long solution(int n, int[] works) {

        PriorityQueue<Long> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int work : works) queue.add((long) work);

        while (n-- > 0 && queue.peek() > 0) {
            queue.add(queue.poll() - 1);
        }

        long fatigue = 0;
        while (!queue.isEmpty()) fatigue += queue.peek() * queue.poll();
        return fatigue;
    }
}