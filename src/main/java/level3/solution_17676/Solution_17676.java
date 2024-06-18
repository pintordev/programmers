package level3.solution_17676;

class Solution_17676 {

    public static void main(String[] args) {

        Solution_17676 s = new Solution_17676();

        String[][] lines = {
                {"2016-09-15 00:00:00.000 3s"},
                {"2016-09-15 23:59:59.999 0.001s"},
                {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"},
                {"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"},
                {"2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s", "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s"},
                {"2016-09-15 00:00:00.000 2.3s", "2016-09-15 23:59:59.999 0.1s"}
        };
        int[] result = {1, 1, 1, 2, 7, 1};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(lines[i]) == result[i]);
        }
    }

    public int solution(String[] lines) {
        int n = lines.length;
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(lines[i]);
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            int cnt = 1;
            for (int j = i + 1; j < n; j++) {
                if (jobs[j].start - jobs[i].end < 1000) cnt++;
            }
            max = Math.max(max, cnt);
        }
        return max;
    }
}

class Job {
    int start;
    int end;

    public Job(String line) {
        int hr = Integer.parseInt(line.substring(11, 13));
        int min = Integer.parseInt(line.substring(14, 16));
        int sec = Integer.parseInt(line.substring(17, 19));
        int ms = Integer.parseInt(line.substring(20, 23));
        int duration = (int) (Double.parseDouble(line.substring(24, line.length() - 1)) * 1000);

        this.end = (hr * 3600 + min * 60 + sec) * 1000 + ms;
        this.start = this.end - duration + 1;
    }
}