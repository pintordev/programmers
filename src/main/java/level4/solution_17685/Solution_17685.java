package level4.solution_17685;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution_17685 {

    public static void main(String[] args) {

        Solution_17685 s = new Solution_17685();

        String[][] words = {
                {"go", "gone", "guild"},
                {"abc", "def", "ghi", "jklm"},
                {"word", "war", "warrior", "world"}
        };
        int[] result = {7, 4, 15};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(words[i]) == result[i]);
            System.out.println(s.solution2(words[i]) == result[i]);
        }
    }

    public int solution(String[] words) {
        Arrays.sort(words);

        int n = words.length;
        int[] typing = new int[n];
        for (int i = 0; i < n - 1; i++) {
            String cur = words[i];
            String next = words[i + 1];

            int comLen = getComLen(cur, next);

            if (comLen == cur.length()) typing[i] = Math.max(typing[i], comLen);
            else typing[i] = Math.max(typing[i], comLen + 1);
            typing[i + 1] = Math.max(typing[i + 1], comLen + 1);
        }


        int cnt = 0;
        for (int t : typing) {
            cnt += t;
        }
        return cnt;
    }

    public int getComLen(String cur, String next) {
        int len = 0;
        for (int i = 0, t = Math.min(cur.length(), next.length()); i < t; i++) {
            if (cur.charAt(i) == next.charAt(i)) len++;
            else break;
        }
        return len;
    }

    public int solution2(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        int cnt = 0;
        for (String word : words) {
            cnt += trie.search(word);
        }
        return cnt;
    }
}

class Node {
    Map<Character, Node> child;
    int childCnt;
    boolean eof;

    public Node() {
        this.child = new HashMap<>();
        this.childCnt = 0;
        this.eof = false;
    }
}

class Trie {
    Node root;

    public Trie() {
        this.root = new Node();
    }

    public void insert(String word) {
        Node cur = root;

        for (int i = 0, len = word.length(); i < len; i++) {
            cur.childCnt++;
            cur = cur.child.computeIfAbsent(word.charAt(i), f -> new Node());
        }
        cur.childCnt++;
        cur.eof = true;
    }

    public int search(String word) {
        Node cur = root;

        int len = word.length();
        int cnt = len;
        for (int i = 0; i < len; i++) {
            if (cur.childCnt == 1) return i;
            cur = cur.child.get(word.charAt(i));
        }
        return cnt;
    }
}