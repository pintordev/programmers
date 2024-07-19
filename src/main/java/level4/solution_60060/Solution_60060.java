package level4.solution_60060;

import java.util.*;

class Solution_60060 {

    public static void main(String[] args) {

        Solution_60060 s = new Solution_60060();

        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
        int[] result = {3, 2, 4, 1, 0};

        System.out.println("===== solution 1 =====");
        System.out.println(Arrays.equals(s.solution(words, queries), result));

        System.out.println("===== solution 2 =====");
        System.out.println(Arrays.equals(s.solution2(words, queries), result));
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

    public int[] solution2(String[] words, String[] queries) {
        Map<Integer, List<String>> front = new HashMap<>();
        Map<Integer, List<String>> back = new HashMap<>();
        for (String word : words) {
            int wLen = word.length();
            front.computeIfAbsent(wLen, k -> new ArrayList<>()).add(word);
            back.computeIfAbsent(wLen, k -> new ArrayList<>()).add(reverse(word));
        }

        for(int key : front.keySet()) {
            front.get(key).sort(String::compareTo);
            back.get(key).sort(String::compareTo);
        }

        int qLen = queries.length;
        int[] result = new int[qLen];
        for (int i = 0; i < qLen; i++) {
            if (queries[i].charAt(0) != '?') result[i] = search(front, queries[i]);
            else result[i] = search(back, reverse(queries[i]));
        }
        return result;
    }

    public int search(Map<Integer, List<String>> list, String query) {
        int len = query.length();
        if (!list.containsKey(len)) return 0;

        String ls = query.replace('?', 'a');
        String us = query.replace('?', 'z');

        return binarySearch(list.get(len), us) - binarySearch(list.get(len), ls);
    }

    public int binarySearch(List<String> words, String str) {
        int low = -1;
        int high = words.size();
        while (low + 1 < high) {
            int mid = (low + high) >> 1;
            if (words.get(mid).compareTo(str) <= 0) low = mid;
            else high = mid;
        }
        return high;
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