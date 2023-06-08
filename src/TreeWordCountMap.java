import java.util.ArrayList;
import java.util.List;

public class TreeWordCountMap {
    private Node root;

    public TreeWordCountMap() {
        root = null;
    }

    public void insert(String word) {
        if (root == null) {
            root = new Node(word);
        } else {
            root.insert(word);
        }
    }

    public List<WordCount> getWordCounts() {
        List<WordCount> wordCounts = new ArrayList<>();
        if (root != null) {
            root.collectWordCounts(wordCounts);
        }
        return wordCounts;
    }

    private class Node {
        private final WordCount wordCount;
        private Node left;
        private Node right;

        public Node(String word) {
            this.wordCount = new WordCount(word, 1);
            left = null;
            right = null;
        }

        public void insert(String word) {
            int compare = this.wordCount.getWord().compareToIgnoreCase(word);
            if (compare == 0) {
                this.wordCount.incrementCount();
            } else if (compare > 0) {
                if (left == null) {
                    left = new Node(word);
                } else {
                    left.insert(word);
                }
            } else {
                if (right == null) {
                    right = new Node(word);
                } else {
                    right.insert(word);
                }
            }
        }

        public void collectWordCounts(List<WordCount> wordCounts) {
            wordCounts.add(wordCount);

            if (left != null) {
                left.collectWordCounts(wordCounts);
            }

            if (right != null) {
                right.collectWordCounts(wordCounts);
            }
        }
    }

    public static class WordCount {
        private final String word;
        private int count;

        public WordCount(String word, int count) {
            this.word = word;
            this.count = count;
        }

        public String getWord() {
            return word;
        }

        public int getCount() {
            return count;
        }

        public void incrementCount() {
            count++;
        }
    }
}
