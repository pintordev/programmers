package level3.solution_72414;

class Solution_72414 {

    public static void main(String[] args) {

        Solution_72414 s = new Solution_72414();

        String[] play_time = {"02:03:55", "99:59:59", "50:00:00"};
        String[] adv_time = {"00:14:15", "25:00:00", "50:00:00"};
        String[][] logs = {{"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"},
                {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"},
                {"15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"}};
        String[] result = {"01:30:59", "01:00:00", "00:00:00"};

        for (int i = 0, t = play_time.length; i < t; i++) {
            System.out.println(s.solution(play_time[i], adv_time[i], logs[i]).equals(result[i]));
        }
    }

    public String solution(String play_time, String adv_time, String[] logs) {

        int n = timeToSec(play_time) + 1;
        long[] accTime = new long[n];
        for (String log : logs) {
            int start = timeToSec(log.substring(0, 8));
            int end = timeToSec(log.substring(9));
            accTime[start]++;
            accTime[end]--;
        }

        for (int i = 1; i < n; i++) {
            accTime[i] += accTime[i - 1];
        }

        for (int i = 1; i < n; i++) {
            accTime[i] += accTime[i - 1];
        }

        int advTime = timeToSec(adv_time);
        long maxView = accTime[advTime - 1];
        int maxTime = 0;
        for (int i = 0; i + advTime < n; i++) {
            long view = accTime[i + advTime] - accTime[i];
            if (view > maxView) {
                maxView = view;
                maxTime = i + 1;
            }
        }
        return secToTime(maxTime);
    }

    public int timeToSec(String playTime) {
        int hr = Integer.parseInt(playTime.substring(0, 2));
        int min = Integer.parseInt(playTime.substring(3, 5));
        int sec = Integer.parseInt(playTime.substring(6, 8));

        return hr * 3600 + min * 60 + sec;
    }

    public String secToTime(int sec) {
        int hr = sec / 3600;
        int min = sec % 3600 / 60;
        sec = sec % 60;

        return String.format("%02d:%02d:%02d", hr, min, sec);
    }
}