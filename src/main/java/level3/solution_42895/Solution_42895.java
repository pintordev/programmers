package level3.solution_42895;

import java.util.HashSet;
import java.util.Set;

class Solution_42895 {

    public static void main(String[] args) {

        Solution_42895 s = new Solution_42895();

        int[] N = {5, 2};
        int[] number = {12, 11};
        int[] result = {4, 3};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(N[i], number[i]) == result[i]);
        }
    }

    public static Set<Integer>[] memo;

    public int solution(int N, int number) {
        if (N == number) return 1;

        memo = new Set[9];
        int num = N;
        for (int i = 1; i <= 8; i++) {
            memo[i] = new HashSet<>();
            memo[i].add(num);
            num = num * 10 + N;
        }

        for (int i = 2; i <= 8; i++) {
            for (int j = 1; j <= i / 2; j++) {
                comb(i, j);
            }
            if (memo[i].contains(number)) return i;
        }
        return -1;
    }

    public void comb(int i, int j) {
        for (int a : memo[j]) {
            for (int b : memo[i - j]) {
                memo[i].add(a + b);
                memo[i].add(a - b);
                memo[i].add(b - a);
                memo[i].add(a * b);
                if (b != 0) memo[i].add(a / b);
                if (a != 0) memo[i].add(b / a);
            }
        }
    }
}