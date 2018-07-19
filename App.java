import java.util.Map;

public class App {
    public static void main(String[] args) {
        String input;
        if (args.length > 0) {
            input = args[0];
        } else {
            input = "this is a test";
        }

        System.out.println("Code Map:");
        Map<Character,String> freqsB = HuffmanCoding.getHuffCodeMap(input);
        for (char key : freqsB.keySet()) {
            System.out.format("'%s' => %s\n", key, freqsB.get(key));
        }
        System.out.println();

        String enc = HuffmanCoding.encode(input);
        System.out.format("'%s' ==> %s\n\n", input, enc);

        HuffmanCoding.Node root = HuffmanCoding.buildHuffTree(input);
        String dec = HuffmanCoding.decode(enc, root);
        System.out.format("%s ==> '%s'\n", enc, dec);
    }
}