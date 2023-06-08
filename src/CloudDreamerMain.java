import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

// Problem : https://cs.carleton.edu/faculty/jondich/courses/cs201_w14/assignments/assignment8.html

public class CloudDreamerMain {
    public static void main(String[] args) {
        String filename = "file.txt";
        TreeWordCountMap wordCountMap = new TreeWordCountMap();
        URL fileUrl = CloudDreamerMain.class.getClassLoader().getResource(filename);

        try {
            assert fileUrl != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileUrl.openStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] words = line.split("[\\s.]");
                    for (String word : words) {
                        if (!isStopword(word)) {
                            wordCountMap.insert(word);
                        }
                    }
                }

                List<TreeWordCountMap.WordCount> wordCounts = wordCountMap.getWordCounts();
                WordCloudMaker wordCloudMaker = new WordCloudMaker();
                String html = wordCloudMaker.generateHTML(wordCounts);
                System.out.println(html);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static boolean isStopword(String word) {
        // Implement your own logic to identify stopwords
        String[] stopwords = {
                "the", "and", "a", "an", "in", "on", "than", "to", "is", "am", "are", "was", "were", "will", "be",
                "then", "when", "might", "may", "ought", "can", "could", "shall", "not"
        };
        for (String stopword : stopwords) {
            if (word.equalsIgnoreCase(stopword)) {
                return true;
            }
        }

        return false;
    }
}

