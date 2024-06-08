package com.web;

import java.util.*;

public class P127 {

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Map<String, Integer> map = new HashMap<>();
        map.put(beginWord, 1);

        while (!queue.isEmpty()) {
            String word = queue.poll();
            int path = map.get(word);
            for (int i = 0; i < word.length(); i++) {
                char[] chars = word.toCharArray();
                for (char k = 'a'; k <= 'z'; k++) {
                    chars[i] = k;
                    String newWord = String.valueOf(chars);
                    if (newWord.equals(endWord)) {
                        return path + 1;
                    }
                    if (wordSet.contains(newWord) && !map.containsKey(newWord)) {
                        map.put(newWord, path + 1);
                        queue.offer(newWord);
                    }
                }
            }
        }
        return 0; // No transformation sequence found
    }

    public static void main(String[] args) {
        // Sample word list
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        // Sample beginWord and endWord
        String beginWord = "hit";
        String endWord = "cog";

        // Call ladderLength method
        int result = ladderLength(beginWord, endWord, wordList);
        // Print the result
        System.out.println("Shortest transformation sequence length: " + result);
    }
}
