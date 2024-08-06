package level4.solution_86054;

import java.util.Arrays;

class Solution_86054 {

    public static void main(String[] args) {

        Solution_86054 s = new Solution_86054();

        int[] a = {1, 1, 1, 1, 1, 1, 2, 5, 8, 2, 1, 1, 4, 8, 8, 8, 12, 6, 6};
        int[] _s = {4, 3, 1, 5, 6};
        int[] result = {6, 3, 1, 5, 9};

        System.out.println(Arrays.equals(s.solution(a, _s), result));
    }

    public int[] solution(int[] a, int[] s) {
        int sLen = s.length;

        int[] result = new int[sLen];
        int offset = 0;
        for (int i = 0; i < sLen; i++) {
            result[i] = count(Arrays.copyOfRange(a, offset, offset + s[i]));
            offset += s[i];
        }
        return result;
    }

    public static int mod = 1_000_000_007;

    public int count(int[] b) {
        Count[][] counts = new Count[b.length][b.length];
        for (int i = 0; i < b.length; i++) {
            long targetSum = b[i];
            if (i == 0) {
                counts[i][i] = new Count(targetSum, 1, i);
            } else {
                int totalCount = 0;
                for (int j = 0; j < i; j++) {
                    if (counts[i - 1][j] != null) {
                        totalCount = sum(totalCount, counts[i - 1][j].count);
                    }
                }
                counts[i][i] = new Count(targetSum, totalCount, i);
            }

            while (true) {
                targetSum *= 2;
                Count lastCount = counts[i][i];
                if (lastCount == null) break;

                int prevOffset = lastCount.offset - 1;
                if (prevOffset < 0) break;

                Count count = counts[prevOffset][prevOffset];
                if (count == null) break;

                counts[i][i] = new Count(targetSum, count.count, count.offset);
            }
        }

        int finalCount = 0;
        for (int i = 0; i < b.length; i++) {
            if (counts[b.length - 1][i] != null) {
                finalCount = sum(finalCount, counts[b.length - 1][i].count);
            }
        }
        return finalCount;
    }

    public int sum(int a, int b) {
        long sum = (long) a + b;
        return (int) (sum % mod);
    }
}

class Count {
    long sum;
    int count;
    int offset;

    public Count(long sum, int count, int offset) {
        this.sum = sum;
        this.count = count;
        this.offset = offset;
    }
}