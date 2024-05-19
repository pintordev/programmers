package level3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution_42628 {

    public static void main(String[] args) {

        Solution_42628 s = new Solution_42628();

        String[][] operations = {{"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"},
                {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"}};
        int[][] result = {{0, 0},
                {333, -45}};
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.equals(s.solution(operations[i]), result[i]));
        }
    }

    public int[] solution(String[] operations) {

        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Comparator.reverseOrder());

        for (String operation : operations) {
            String[] bits = operation.split(" ");
            if (bits[0].equals("I")) {
                minQ.add(Integer.parseInt(bits[1]));
                maxQ.add(Integer.parseInt(bits[1]));
            } else if (bits[0].equals("D") && Integer.parseInt(bits[1]) == -1) {
                maxQ.remove(minQ.poll());
            } else {
                minQ.remove(maxQ.poll());
            }
        }

        return new int[]{maxQ.isEmpty() ? 0 : maxQ.poll(),
                minQ.isEmpty() ? 0 : minQ.poll()};
    }
}