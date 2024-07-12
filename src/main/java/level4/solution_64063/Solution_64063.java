package level4.solution_64063;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution_64063 {

    public static void main(String[] args) {

        Solution_64063 s = new Solution_64063();

        long k = 10;
        long[] room_number = {1, 3, 4, 1, 3, 1};
        long[] result = {1, 3, 4, 2, 5, 6};

        System.out.println(Arrays.equals(s.solution(k, room_number), result));
    }

    public static Map<Long, Long> reserved;

    public long[] solution(long k, long[] room_number) {
        int len = room_number.length;

        reserved = new HashMap<>();
        long[] result = new long[len];
        for (int i = 0; i < len; i++) {
            result[i] = reserve(room_number[i]);
        }
        return result;
    }

    public long reserve(long rm) {
        var value = reserved.putIfAbsent(rm, rm + 1);
        if (value == null) return rm;

        long nextRm = reserve(value);
        reserved.put(rm, nextRm + 1);
        return nextRm;
    }
}