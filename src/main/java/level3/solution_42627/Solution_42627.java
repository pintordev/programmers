package level3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution_42627 {

    public static void main(String[] args) {

        Solution_42627 s = new Solution_42627();

        int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};
        int result = 9;
        System.out.println(s.solution(jobs) == result);
    }

    public int solution(int[][] jobs) {

        PriorityQueue<int[]> jq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        Arrays.sort(jobs, Comparator.comparingInt(o -> o[0]));
        int n = jobs.length, sum = 0, due = 0, idx = 0;
        while (idx < n || !jq.isEmpty()) {
            while (idx < n && jobs[idx][0] <= due) jq.add(jobs[idx++]);
            if (jq.isEmpty()) due = jobs[idx][0];
            else {
                due += jq.peek()[1];
                sum += due - jq.poll()[0];
            }
        }

        return sum / n;
    }
}