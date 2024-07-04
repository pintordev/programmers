package level3.solution_214288;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Solution_214288 {

    public static void main(String[] args) {

        Solution_214288 s = new Solution_214288();

        int[] k = {3, 2};
        int[] n = {5, 3};
        int[][][] reqs = {
                {{10, 60, 1}, {15, 100, 3}, {20, 30, 1}, {30, 50, 3}, {50, 40, 1}, {60, 30, 2}, {65, 30, 1}, {70, 100, 2}},
                {{5, 55, 2}, {10, 90, 2}, {20, 40, 2}, {50, 45, 2}, {100, 50, 2}}
        };
        int[] result = {25, 90};

        for (int i = 0, t = k.length; i < t; i++) {
            System.out.println(s.solution(k[i], n[i], reqs[i]) == result[i]);
        }
    }

    public static List<Request>[] requests;
    public static int[][] waitingTime;

    public int solution(int k, int n, int[][] reqs) {
        requests = new List[k +  1];
        for (int i = 1; i <= k; i++) {
            requests[i] = new ArrayList<>();
        }
        for (int[] req : reqs) {
            requests[req[2]].add(new Request(req[0], req[1]));
        }

        waitingTime = new int[k + 1][n - k + 2];
        for (int i = 1; i <= k; i++) {
            calWaitingTime(i, n - k + 1);
        }

        return minWaitingTime(k, n - k + 1);
    }

    public void calWaitingTime(int i, int limit) {
        for (int j = 1; j <= limit; j++) {
            int len = requests[i].size();
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int k = 0; k < j && k < len; k++) {
                pq.add(requests[i].get(k).end);
            }
            for (int k = j; k < len; k++) {
                Request next = requests[i].get(k);
                int end = pq.poll();
                if (end > next.req) waitingTime[i][j] += end - next.req;
                pq.add(Math.max(end + next.dur, next.end));
            }
        }
    }

    public int minWaitingTime(int k, int limit) {
        int[] assign = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            assign[i] = 1;
        }

        for (int i = 2; i <= limit; i++) {
            int max = 0;
            int mdx = 0;
            for (int j = 1; j <= k; j++) {
                int diff = waitingTime[j][assign[j]] - waitingTime[j][assign[j] + 1];
                if (max >= diff) continue;
                max = diff;
                mdx = j;
            }
            assign[mdx]++;
        }

        int sum = 0;
        for (int i = 1; i <= k; i++) {
            sum += waitingTime[i][assign[i]];
        }
        return sum;
    }
}

class Request {
    int req;
    int dur;
    int end;

    public Request(int req, int dur) {
        this.req = req;
        this.dur = dur;
        this.end = req + dur;
    }
}