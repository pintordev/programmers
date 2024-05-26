package level3.solution_81303;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution_81303 {

    public static void main(String[] args) {

        Solution_81303 s = new Solution_81303();

        int[] n = {8, 8};
        int[] k = {2, 2};
        String[][] cmd = {{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"},
                {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"}};
        String[] result = {"OOOOXOOO", "OOXOXOOO"};

        int t = n.length;
        for (int i = 0; i < t; i++) {
            System.out.println(s.solution(n[i], k[i], cmd[i]).equals(result[i]));
            Row.clear();
        }
    }

    public String solution(int n, int k, String[] cmd) {

        Row now = null;
        for (int i = 0; i < n; i++) {
            Row row = new Row();
            Row.add(row);
            if (i == k) now = row;
        }

        Stack<Row> deleted = new Stack<>();
        for (String line : cmd) {
            char c = line.charAt(0);
            switch (c) {
                case 'U':
                    now = now.movePrev(Integer.parseInt(line.substring(2)));
                    break;
                case 'D':
                    now = now.moveNext(Integer.parseInt(line.substring(2)));
                    break;
                case 'C':
                    deleted.add(now);
                    now = now.delete();
                    break;
                case 'Z':
                    deleted.pop().recover();
                    break;
            }
        }

        return Row.getDeletedStatus();
    }
}

class Row {

    static List<Row> rows = new ArrayList<>();

    public static void add(Row row) {
        int size = rows.size();
        if (size > 0) {
            row.addPrev(rows.get(size - 1));
            row.prev.addNext(row);
        } else {
            row.addPrev(null);
        }
        rows.add(row);
    }

    public static String getDeletedStatus() {
        StringBuilder sb = new StringBuilder();
        for (Row row : rows) {
            sb.append(row.isDeleted ? 'X' : 'O');
        }
        return sb.toString();
    }

    public static void clear() {
        rows.clear();
    }

    Row prev;
    Row next;
    boolean isDeleted;

    public void addPrev(Row prev) {
        this.prev = prev;
    }

    public void addNext(Row next) {
        this.next = next;
    }

    public Row movePrev(int depth) {
        Row toMove = this;
        while (depth-- > 0) {
            toMove = toMove.prev;
        }
        return toMove;
    }

    public Row moveNext(int depth) {
        Row toMove = this;
        while (depth-- > 0) {
            toMove = toMove.next;
        }
        return toMove;
    }

    public Row delete() {
        isDeleted = true;
        if (this.prev != null) {
            this.prev.addNext(next);
        }
        if (this.next != null) {
            this.next.addPrev(prev);
            return this.next;
        }
        return this.prev;
    }

    public void recover() {
        isDeleted = false;
        if (this.prev != null) {
            this.prev.addNext(this);
        }
        if (this.next != null) {
            this.next.addPrev(this);
        }
    }
}