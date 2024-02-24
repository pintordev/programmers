package level2;

class Solution_160585 {

    public static void main(String[] args) {

        Solution_160585 s = new Solution_160585();

        String[][] board = {{"O.X", ".O.", "..X"},
                {"OOO", "...", "XXX"},
                {"...", ".X.", "..."},
                {"...", "...", "..."}};
        int[] result = {1, 0, 0, 1};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(board[i]) == result[i]);
        }
    }

    public int solution(String[] board) {

        int o = 0, x = 0;
        for (String b : board) {
            for (char c : b.toCharArray()) {
                if (c == 'O') o++;
                else if (c == 'X') x++;
            }
        }

        if (o - x < 0 || o - x > 1) return 0;
        if (made(board, 'O') && o == x) return 0;
        if (made(board, 'X') && o != x) return 0;

        return 1;
    }

    public boolean made(String[] board, char c) {

        boolean isMadeL = true, isMadeR = true;
        for (int i = 0; i < board.length; i++) {
            if (board[i].charAt(i) != c) isMadeL = false;
            if (board[i].charAt(2 - i) != c) isMadeR = false;
            boolean isMadeI = true, isMadeJ = true;
            for (int j = 0; j < board.length; j++) {
                if (board[i].charAt(j) != c) isMadeI = false;
                if (board[j].charAt(i) != c) isMadeJ = false;
            }
            if (isMadeI || isMadeJ) return true;
        }

        return isMadeL || isMadeR;
    }
}