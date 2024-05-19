package level3.solution_77486;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution_77486 {
    public static void main(String[] args) {
        Solution_77486 s = new Solution_77486();

        String[][] enroll = {{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"}};
        String[][] referral = {{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"}};
        String[][] seller = {{"young", "john", "tod", "emily", "mary"},
                {"sam", "emily", "jaimie", "edward"}};
        int[][] amount = {{12, 4, 2, 5, 10},
                {2, 3, 5, 4}};
        int[][] result = {{360, 958, 108, 0, 450, 18, 180, 1080},
                {0, 110, 378, 180, 270, 450, 0, 0}};

        int t = enroll.length;
        for (int i = 0; i < t; i++) {
            System.out.println(Arrays.equals(s.solution(enroll[i], referral[i], seller[i], amount[i]), result[i]));
        }
    }

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, Node> map = new HashMap<>();
        map.put("-", new Node("-", null));
        int n = enroll.length;
        for (int i = 0; i < n; i++) {
            map.put(enroll[i], new Node(enroll[i], map.get(referral[i])));
        }

        int m = seller.length;
        for (int i = 0; i < m; i++) {
            map.get(seller[i]).earn(amount[i] * 100);
        }

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = map.get(enroll[i]).earning;
        }
        return result;
    }
}

class Node {
    Node parent;
    String name;
    int earning;

    public Node(String name, Node parent) {
        this.name = name;
        this.parent = parent;
        this.earning = 0;
    }

    public void earn(int price) {
        int fee = price / 10;
        this.earning += price - fee;
        if (this.parent != null) {
            this.parent.earn(fee);
        }
    }
}