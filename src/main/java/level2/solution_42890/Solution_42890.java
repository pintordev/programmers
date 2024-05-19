package level2.solution_42890;

import java.util.*;

class Solution_42890 {

    public static void main(String[] args) {

        Solution_42890 s = new Solution_42890();

        String[][] relation = {{"100", "ryan", "music", "2"},
                {"200", "apeach", "math", "2"},
                {"300", "tube", "computer", "3"},
                {"400", "con", "computer", "4"},
                {"500", "muzi", "music", "3"},
                {"600", "apeach", "music", "2"}};
        int result = 2;
        System.out.println(s.solution(relation) == result);
    }

    public int solution(String[][] relation) {

        int n = 1;
        Set<int[]> keySet = new HashSet<>();
        while (relation[0].length >= n) {
            comb(relation, keySet, new int[n++], 0, 0);
        }

        return keySet.size();
    }

    public void comb(String[][] relation, Set<int[]> keySet, int[] kdx, int count, int start) {

        if (count == kdx.length) {
            for (int[] key : keySet) if (!isMinimal(kdx, key)) return;
            if (!isUnique(relation, kdx)) return;
            keySet.add(Arrays.copyOf(kdx, kdx.length));
            return;
        }

        for (int i = start; i < relation[0].length; i++) {
            kdx[count] = i;
            comb(relation, keySet, kdx, count + 1, i + 1);
        }
    }

    public boolean isMinimal(int[] main, int[] sub) {
        for (int s : sub) {
            boolean contained = false;
            for (int m : main) if (s == m) contained = true;
            if (!contained) return true;
        }
        return false;
    }

    public boolean isUnique(String[][] relation, int[] kdx) {
        Set<String> set = new HashSet<>();
        for (String[] rel : relation) {
            StringBuilder sb = new StringBuilder();
            for (int k : kdx) sb.append(rel[k]).append(" ");
            String s = sb.toString();
            if (set.contains(s)) return false;
            set.add(s);
        }
        return true;
    }
}