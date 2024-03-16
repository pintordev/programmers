package level1;

class Solution_82612 {

    public static void main(String[] args) {

        Solution_82612 s = new Solution_82612();

        int price = 3;
        int money = 20;
        int count = 4;
        int result = 10;
        System.out.println(s.solution(price, money, count) == result);
    }

    public long solution(int price, int money, int count) {

        long remain = (long) price * count * (count + 1) / 2 - money;

        return remain > 0 ? remain : 0;
    }
}