package level4.solution_42891;

import java.util.PriorityQueue;

class Solution_42891 {

    public static void main(String[] args) {

        Solution_42891 s = new Solution_42891();

        int[] food_times = {3, 1, 2};
        long k = 5;
        int result = 1;

        System.out.println(s.solution(food_times, k) == result);
    }

    public int solution(int[] food_times, long k) {
        int len = food_times.length;

        PriorityQueue<Food> timeQ = new PriorityQueue<>(Food::compareTime);
        for (int i = 0; i < len; i++) {
            timeQ.add(new Food(i + 1, food_times[i]));
        }

        long sum = 0;
        long prev = 0;
        while (!timeQ.isEmpty() && sum + (timeQ.peek().t - prev) * len <= k) {
            int t = timeQ.poll().t;
            sum += (t - prev) * len;
            len--;
            prev = t;
        }

        if (timeQ.isEmpty()) return -1;

        PriorityQueue<Food> idxQ = new PriorityQueue<>(Food::compareIdx);
        idxQ.addAll(timeQ);

        long rdx = (k - sum) % len;
        while (rdx-- > 0) {
            idxQ.poll();
        }
        return idxQ.peek().idx;
    }
}

class Food {
    int idx;
    int t;

    public Food(int idx, int t) {
        this.idx = idx;
        this.t = t;
    }

    public int compareIdx(Food o) {
        return this.idx - o.idx;
    }

    public int compareTime(Food o) {
        return this.t - o.t;
    }
}