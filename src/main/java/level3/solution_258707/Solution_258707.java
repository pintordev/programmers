package level3.solution_258707;

import java.util.HashSet;
import java.util.Set;

class Solution_258707 {

    public static void main(String[] args) {

        Solution_258707 s = new Solution_258707();

        int[] coin = {4, 3, 2, 10};
        int[][] cards = {
                {3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4},
                {1, 2, 3, 4, 5, 8, 6, 7, 9, 10, 11, 12},
                {5, 8, 1, 2, 9, 4, 12, 11, 3, 10, 6, 7},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18}
        };
        int[] result = {5, 2, 4, 1};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(coin[i], cards[i]) == result[i]);
        }
    }

    public static int n;
    public static int coin;

    public int solution(int coin, int[] cards) {
        n = cards.length;
        this.coin = coin;

        Set<Integer> origin = new HashSet<>();
        for (int i = 0; i < n / 3; i++) {
            origin.add(cards[i]);
        }

        Set<Integer> extra = new HashSet<>();
        int idx = n / 3;
        int rnd = 1;
        while (idx < n) {
            extra.add(cards[idx]);
            extra.add(cards[idx + 1]);
            idx += 2;
            rnd++;

            if (canMade(origin, 0)) continue;
            if (canMade(origin, extra)) continue;
            if (canMade(extra, 2)) continue;
            rnd--;
            break;
        }

        return rnd;
    }

    public boolean canMade(Set<Integer> set, int type) {
        if (coin < type) return false;
        for (int a : set) {
            if (!set.contains(n + 1 - a)) continue;
            set.remove(a);
            set.remove(n + 1 - a);
            coin -= type;
            return true;
        }
        return false;
    }

    public boolean canMade(Set<Integer> setA, Set<Integer> setB) {
        if (coin < 1) return false;
        for (int a : setA) {
            if (!setB.contains(n + 1 - a)) continue;
            setA.remove(a);
            setB.remove(n + 1 - a);
            coin -= 1;
            return true;
        }
        return false;
    }
}