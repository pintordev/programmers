package level2.solution_131130;

import java.util.Comparator;
import java.util.PriorityQueue;

class Solution_131130 {

    public static void main(String[] args) {

        Solution_131130 s = new Solution_131130();

        int[] cards = {8, 6, 3, 7, 2, 5, 1, 4};
        int result = 12;
        System.out.println(s.solution(cards) == result);
    }

    public int solution(int[] cards) {

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        boolean[] visited = new boolean[cards.length];
        int idx = 0, count = 0;
        while (idx < cards.length) {
            if (!visited[idx]) {
                visited[idx] = true;
                count++;
                idx = cards[idx] - 1;
            } else {
                if (count != 0) queue.add(count);
                count = 0;
                idx++;
            }
        }

        return queue.size() == 1 ? 0 : queue.poll() * queue.poll();
    }
}