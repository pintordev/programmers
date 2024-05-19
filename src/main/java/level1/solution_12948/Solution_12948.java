package level1;

class Solution_12948 {

    public static void main(String[] args) {

        Solution_12948 s = new Solution_12948();

        String[] phone_number = {"01033334444", "027778888"};
        String[] result = {"*******4444", "*****8888"};
        for (int i = 0; i < result.length; i++) {
            System.out.println(s.solution(phone_number[i]) == result[i]);
        }
    }

    public String solution(String phone_number) {

        char[] c = phone_number.toCharArray();
        for (int i = 0; i < c.length - 4; i++) c[i] = '*';

        return new String(c);
    }
}