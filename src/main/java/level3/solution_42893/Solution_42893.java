package level3.solution_42893;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution_42893 {

    public static void main(String[] args) {

        Solution_42893 s = new Solution_42893();

        String[] word = {"blind", "Muzi"};
        String[][] pages = {
                {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"},
                {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"}
        };
        int[] result = {0, 1};

        for (int i = 0, t = result.length; i < t; i++) {
            System.out.println(s.solution(word[i], pages[i]) == result[i]);
        }
    }

    public int solution(String word, String[] pages) {
        Web.init(word);
        Web.add(pages);
        Web.calScore();
        return Web.getMaxScoreIdx();
    }
}

class Web {
    public static List<Web> pages;
    public static Map<String, Set<Web>> links;

    public static Pattern urlPattern = Pattern.compile("<meta property=\"og:url\" content=\"https://(\\S*)\"/>");
    public static Pattern linkPattern = Pattern.compile("<a href=\"https://(\\S*)\"");
    public static Pattern wordPattern;

    public static void init(String word) {
        pages = new ArrayList<>();
        links = new HashMap<>();
        wordPattern = Pattern.compile("\\b(?i)" + word + "\\b");
    }

    public static void add(String[] pages) {
        for (String page : pages) {
            new Web(page);
        }
    }

    public static void calScore() {
        for (Web page : pages) {
            for (Web link : links.getOrDefault(page.url, new HashSet<>())) {
                page.totalScore += (double) link.basicScore / link.linkCnt;
            }
        }
    }

    public static int getMaxScoreIdx() {
        int idx = 0;
        double max = -1;
        for (int i = 0, t = pages.size(); i < t; i++) {
            if (max >= pages.get(i).totalScore) continue;
            max = pages.get(i).totalScore;
            idx = i;
        }
        return idx;
    }

    public String url;
    public int basicScore;
    public int linkCnt;
    public double totalScore;

    public Web(String page) {
        pages.add(this);

        Matcher urlMatcher = urlPattern.matcher(page);
        if (urlMatcher.find()) url = urlMatcher.group(1);

        Matcher wordMatcher = wordPattern.matcher(page.replaceAll("[0-9]", " "));
        while (wordMatcher.find()) basicScore++;
        totalScore = basicScore;

        Matcher linkMatcher = linkPattern.matcher(page);
        while (linkMatcher.find()) {
            linkCnt++;
            links.computeIfAbsent(linkMatcher.group(1), k -> new HashSet<>()).add(this);
        }
    }
}