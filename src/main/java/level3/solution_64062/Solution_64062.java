package level3;

import java.util.Deque;
import java.util.LinkedList;

class Solution_64062 {

    public static void main(String[] args) {

        Solution_64062 s = new Solution_64062();

        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;
        int result = 3;
        System.out.println(s.solution(stones, k) == result);
    }

    public int solution(int[] stones, int k) {

        Deque<Integer> deque = new LinkedList<>();
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < stones.length; i++) {
            while (!deque.isEmpty() && stones[deque.peekLast()] < stones[i]) deque.pollLast();
            deque.addLast(i);
            while (!deque.isEmpty() && i - deque.peekFirst() == k) deque.pollFirst();
            if (i >= k - 1) min = Math.min(min, stones[deque.peekFirst()]);
        }

        return min;
    }
}