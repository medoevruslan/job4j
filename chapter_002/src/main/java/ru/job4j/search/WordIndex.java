package ru.job4j.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

public class WordIndex {

    private final Trie trie = new Trie();

    public void loadFile(String file) {
        trie.createTrie(file);
    }

    public Set<Integer> getIndex4Words(String word) {
        return trie.getIndex(word).size() == 0 ? null : trie.getIndex(word);
    }

    private class Trie {
        Node root = new Node();

        private void createTrie(String input) {
            String text = input.toLowerCase();
            char[] chars = text.toCharArray();
            for (int index = 0; index < text.length(); index++) {
                Node pointer = root;
                int start = index;
                while (Character.isLetter(chars[index])) {
                    if (pointer.children.containsKey(chars[index])) {
                        pointer = pointer.children.get(chars[index]);
                    } else {
                        Node node = new Node();
                        pointer.children.put(chars[index], node);
                        pointer = node;
                    }
                    if (index == text.length() - 1) {
                        break;
                    }
                    pointer.indexes.add(start);
                    index++;
                }
            }
        }

        private Set<Integer> getIndex(String input) {
            Set<Integer> set = new TreeSet<>();
            boolean isEnd = false;
            String word = input.toLowerCase();
            Node pointer = root;
            while (!isEnd) {
                for (int idx = 0; idx < word.length(); idx++) {
                    if (pointer.children.containsKey(word.charAt(idx))) {
                        if (idx == 0) {
                            set.addAll(pointer.children.get(word.charAt(idx)).indexes);
                        }
                        set.retainAll(pointer.children.get(word.charAt(idx)).indexes);
                        pointer = pointer.children.get(word.charAt(idx));
                    } else {
                        isEnd = true;
                        break;
                    }
                }
            }
            return set;
        }

        private class Node {
            private ArrayList<Integer> indexes;
            private HashMap<Character, Node> children;

            private Node() {
                this.indexes = new ArrayList<>();
                this.children = new HashMap<>();
            }
        }
    }
}

