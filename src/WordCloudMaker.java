import java.util.List;

public class WordCloudMaker {
    private static final int MIN_FONT_SIZE = 10;
    private static final int MAX_FONT_SIZE = 40;

    public String generateHTML(List<TreeWordCountMap.WordCount> wordCounts) {
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html>\n");
        htmlBuilder.append("<head>\n");
        htmlBuilder.append("<style>\n");
        htmlBuilder.append(".wordcloud {\n");
        htmlBuilder.append("  width: 600px;\n");
        htmlBuilder.append("  height: 400px;\n");
        htmlBuilder.append("  display: flex;\n");
        htmlBuilder.append("  flex-wrap: wrap;\n");
        htmlBuilder.append("}\n");
        htmlBuilder.append(".wordcloud span {\n");
        htmlBuilder.append("  font-size: 12px;\n");
        htmlBuilder.append("}\n");
        htmlBuilder.append("</style>\n");
        htmlBuilder.append("</head>\n");
        htmlBuilder.append("<body>\n");
        htmlBuilder.append("<div class=\"wordcloud\">\n");

        int maxCount = getMaxCount(wordCounts);
        for (TreeWordCountMap.WordCount wordCount : wordCounts) {
            String word = wordCount.getWord();
            int count = wordCount.getCount();
            int fontSize = calculateFontSize(count, maxCount);

            System.out.printf("%s : %d, %d %n", word, count, fontSize);

            htmlBuilder.append("<span style=\"font-size: ").append(fontSize).append("px;\">").append(word).append("</span>\n");
        }

        htmlBuilder.append("</div>\n");
        htmlBuilder.append("</body>\n");
        htmlBuilder.append("</html>\n");

        return htmlBuilder.toString();
    }

    private int getMaxCount(List<TreeWordCountMap.WordCount> wordCounts) {
        int maxCount = 0;
        for (TreeWordCountMap.WordCount wordCount : wordCounts) {
            int count = wordCount.getCount();
            if (count > maxCount) {
                maxCount = count;
            }
        }
        return maxCount;
    }

    private int calculateFontSize(int count, int maxCount) {
        double ratio = (double) count / maxCount;
        return (int) (MIN_FONT_SIZE + (MAX_FONT_SIZE - MIN_FONT_SIZE) * ratio);
    }
}
