package level3;

import java.util.PriorityQueue;

class Solution_17678 {

    public static void main(String[] args) {

        Solution_17678 s = new Solution_17678();

        int[] n = {1, 2, 2, 1, 1, 10};
        int[] t = {1, 10, 1, 1, 1, 60};
        int[] m = {5, 2, 2, 5, 1, 45};
        String[][] timetable = {{"08:00", "08:01", "08:02", "08:03"},
                {"09:10", "09:09", "08:00"},
                {"09:00", "09:00", "09:00", "09:00"},
                {"00:01", "00:01", "00:01", "00:01", "00:01"},
                {"23:59"},
                {"23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"}};
        String[] answer = {"09:00", "09:09", "08:59", "00:00", "09:00", "18:00"};

        int _t = n.length;
        for (int i = 0; i < _t; i++) {
            System.out.println(s.solution(n[i], t[i], m[i], timetable[i]).equals(answer[i]));
        }
    }

    public String solution(int n, int t, int m, String[] timetable) {

        int busTime = timeStringToMin("09:00");
        int busNum = 0;
        PriorityQueue<Integer> crewTime = new PriorityQueue<>();
        for (String time : timetable) {
            crewTime.add(timeStringToMin(time));
        }

        while (busNum < n) {
            int passenger = 0;
            while (!crewTime.isEmpty() && crewTime.peek() <= busTime && passenger < m) {
                if (busNum == n - 1 && passenger == m - 1) return minToTimeString(crewTime.poll() - 1);
                crewTime.poll();
                passenger++;
            }
            busNum++;
            busTime += t;
        }

        return minToTimeString(busTime - t);
    }

    public int timeStringToMin(String time) {
        String[] times = time.split(":");
        return Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
    }

    public String minToTimeString(int min) {
        return String.format("%02d:%02d", min / 60, min % 60);
    }
}