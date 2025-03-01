package level2.solution_17686;

import java.util.Arrays;

class Solution_17686 {

    public static void main(String[] args) {

        Solution_17686 s = new Solution_17686();

        String[][] files = {
                {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"},
                {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"}
        };
        String[][] result = {
                {"img1.png", "IMG01.GIF", "img02.png", "img2.JPG", "img10.png", "img12.png"},
                {"A-10 Thunderbolt II", "B-50 Superfortress", "F-5 Freedom Fighter", "F-14 Tomcat"}
        };

        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.deepEquals(s.solution(files[i]), result[i]));
        }
    }

    public String[] solution(String[] files) {

        File[] _files = Arrays.stream(files)
                .map(file -> new File(file)).toArray(File[]::new);

        Arrays.sort(_files, (o1, o2) -> {
            if (o1.head.equals(o2.head)) {
                if (o1.number == o2.number) return o1.id - o2.id;
                return o1.number - o2.number;
            }
            return o1.head.compareTo(o2.head);
        });

        return Arrays.stream(_files)
                .map(file -> file.name).toArray(String[]::new);
    }
}

class File {

    public static int count = 0;
    public int id;
    public String head;
    public int number;
    public String name;

    public File(String name) {
        this.id = ++count;
        this.name = name;
        String _head = "", _number = "";
        while (!name.substring(0, 1).matches("^[0-9]")) {
            _head += name.substring(0, 1);
            name = name.substring(1);
        }
        while (name.length() > 0 && name.substring(0, 1).matches("^[0-9]")) {
            _number += name.substring(0, 1);
            name = name.substring(1);
        }
        this.head = _head.toLowerCase();
        this.number = Integer.parseInt(_number);
    }
}