package class08;

/**
 * 该程序的对数器跑不过，你能发现bug在哪吗？
 */


public class Trie1 implements Trie {
    private final Node.Node1 root;

    public Trie1() {
        root = new Node.Node1();
    }

    /**
     * 添加某个字符串，可以重复添加，每次算1个
     *
     * @param word
     */
    @Override
    public void insert(String word) {
        if (word == null) {
            return;
        }
        Node.Node1 node = root;
        int index = 0;
        for (int i = 0; i < word.length(); i++) { //从左到右遍历字符
            index = word.charAt(i) - 'a'; // 由字符，对应走那条路
            if (node.nexts[index] == null) {
                node.nexts[index] = new Node.Node1();
            }
            node = node.nexts[index];
            node.pass++;
        }
        node.end++;
    }

    /**
     * 删掉某个字符串，可以重复删除，每次算1个
     *
     * @param word
     */
    @Override
    public void delete(String word) {
        if (search(word) != 0) {
            Node.Node1 node = root;
            node.pass--;
            int index = 0;
            for (char ch : word.toCharArray()) {
                index = ch - 'a';
                if (--node.nexts[index].pass == 0) {
                    node.nexts[index] = null;
                    return;
                }
                node = node.nexts[index];
            }
            node.end--;
        }
    }

    /**
     * 查询某个字符串在结构中还有几个
     *
     * @param word
     * @return
     */
    @Override
    public int search(String word) {
        if (word == null) {
            return 0;
        }
        Node.Node1 node = root;
        int index = 0;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            if (node.nexts[index] == null) {
                return 0;
            }
            node = node.nexts[index];
        }
        return node.end;
    }

    /**
     * 查询有多少个字符串，是以str做前缀的
     *
     * @param pre
     * @return
     */
    @Override
    public int prefixNumber(String pre) {
        if (pre == null) {
            return 0;
        }
        Node.Node1 node = root;
        int index = 0;
        for (int i = 0; i < pre.length(); i++) {
            index = pre.charAt(i) - 'a';
            if (node.nexts[index] == null) {
                return 0;
            }
            node = node.nexts[index];
        }
        return node.pass;
    }
}
