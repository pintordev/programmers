package level2;

class Solution_62048 {

    public static void main(String[] args) {

        Solution_62048 s = new Solution_62048();

        int w = 8;
        int h = 12;
        long result = 80;
        System.out.println(s.solution(w, h) == result);
    }

    public long solution(int w, int h) {

        int min = Math.min(w, h);
        int max = Math.max(w,h);

        long sum = 0;
        for (int i = 0; i < min; i++) sum += 2 * Math.floor((double) max * i / min);

        return sum;
    }
}