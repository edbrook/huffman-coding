import java.util.*;

public class HuffmanCoding {
    static abstract class Node implements Comparable<Node> {
        int freq;
        char value;

        Node(int freq) {
            this.freq = freq;
        }

        @Override
        public int compareTo(Node other) {
            int freqOrder = freq - other.freq;
            if (freqOrder == 0) {
                return value - other.value;
            }
            return freqOrder;
        }
    }

    static class HuffNode extends Node {
        Node left;
        Node right;

        HuffNode(Node left, Node right) {
            super(left.freq + right.freq);
            this.left = left;
            this.right = right;
        }
    }

    static class HuffLeaf extends Node {
        HuffLeaf(char value, int freq) {
            super(freq);
            this.value = value;
        }
    }

    public static String encode(String in) {
        Map<Character,String> map = getHuffCodeMap(in);
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<in.length(); ++i) {
            char c = in.charAt(i);
            sb.append(map.get(c));
        }
        return sb.toString();
    }

    public static String decode(String in, Node root) {
        return null;
    }

    public static Map<Character,String> getHuffCodeMap(String in) {
        return generateCodeMap(buildHuffTree(in));
    }

    public static Node buildHuffTree(String in) {
        return buildTree(getCharFrequencies(in));
    }

    private static Node buildTree(Map<Character,Integer> freqs) {
        PriorityQueue<Node> nodes = new PriorityQueue<>();
        for (char c : freqs.keySet()) {
            nodes.add(new HuffLeaf(c, freqs.get(c)));
        }
        while (nodes.size() > 1) {
            nodes.offer(new HuffNode(nodes.poll(), nodes.poll()));
        }
        return nodes.poll();
    }

    static Map<Character,Integer> getCharFrequencies(String in) {
        Map<Character,Integer> freqs = new HashMap<>();
        for (int i=0; i<in.length(); ++i) {
            char c = in.charAt(i);
            freqs.put(c, freqs.getOrDefault(c, 0) + 1);
        }
        return freqs;
    }

    private static Map<Character,String> generateCodeMap(Node root) {
        return generateCodeMap(root, "");
    }

    private static Map<Character,String> generateCodeMap(Node root, String code) {
        Map<Character,String> map = new HashMap<>();
        if (root instanceof HuffLeaf) {
            map.put(((HuffLeaf) root).value, code);
        } else if (root instanceof HuffNode) {
            Node left = ((HuffNode) root).left;
            Node right = ((HuffNode) root).right;
            map.putAll(generateCodeMap(left, code + "0"));
            map.putAll(generateCodeMap(right, code + "1"));
        }
        return map;
    }
}
