package level4.solution_60060;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution_60060 {

    public static void main(String[] args) {

        Solution_60060 s = new Solution_60060();

        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
        int[] result = {3, 2, 4, 1, 0};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(Arrays.equals(s.solution(words, queries), result));
        }
    }

    public int[] solution(String[] words, String[] queries) {
        Trie front = new Trie();
        Trie back = new Trie();
        for (String word : words) {
            front.insert(word);
            back.insert(reverse(word));
        }

        int len = queries.length;
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            if (queries[i].charAt(0) != '?') result[i] = front.search(queries[i]);
            else result[i] = back.search(reverse(queries[i]));
        }
        return result;
    }

    public String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}

class Trie {
    Trie[] child;
    Map<Integer, Integer> lenCnt;

    public Trie() {
        this.child = new Trie[26];
        this.lenCnt = new HashMap<>();
    }

    public void insert(String word) {
        Trie trie = this;

        int len = word.length();
        addCnt(trie, len);

        for (char c : word.toCharArray()) {
            int cdx = c - 'a';
            if (trie.child[cdx] == null) trie.child[cdx] = new Trie();
            trie = trie.child[cdx];

            addCnt(trie, len);
        }
    }

    private void addCnt(Trie trie, int len) {
        var cnt = trie.lenCnt.putIfAbsent(len, 1);
        if (cnt != null) trie.lenCnt.put(len, cnt + 1);
    }

    public int search(String word) {
        Trie trie = this;

        int len = word.length();
        for (char c : word.toCharArray()) {
            if (c == '?') return trie.lenCnt.getOrDefault(len, 0);
            if (trie.child[c - 'a'] == null) return 0;
            trie = trie.child[c - 'a'];
        }

        return -1;
    }
}