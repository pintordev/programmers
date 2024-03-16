package level1;

class Solution_12930 {

    public static void main(String[] args) {

        Solution_12930 s = new Solution_12930();

        String _s = "try hello world";
        String result = "TrY HeLlO WoRlD";
        System.out.println(s.solution(_s).equals(result));
    }

    public String solution(String s) {

        char[] c = s.toCharArray();
        int count = 0;
        for (int i = 0; i < c.length; i++) {

            if (c[i] == ' ') {
                count = 0;
                continue;
            }

            if (count++ % 2 == 0) c[i] = Character.toUpperCase(c[i]);
            else c[i] = Character.toLowerCase(c[i]);
        }

        return new String(c);
    }
}