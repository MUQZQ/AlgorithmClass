package class08;

public class Trie2 implements Trie {
    private final Node.Node2 root;

    public Trie2() {
        root = new Node.Node2();
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

        Node.Node2 node = root;
        node.pass++;
        int index = 0;
        for (char ch : word.toCharArray()) {
            index = ch;
            if (!node.nexts.containsKey(index)) {
                node.nexts.put(index, new Node.Node2());
            }
            node = node.nexts.get(index);
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

            Node.Node2 node = root;
            node.pass--;
            int index = 0;
            for (char ch : word.toCharArray()) {
                index = ch;
                if (--node.nexts.get(index).pass == 0) {
                    node.nexts.remove(index);
                    return;
                }
                node = node.nexts.get(index);
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

        Node.Node2 node = root;
        int index = 0;
        for (char ch : word.toCharArray()) {
            index = ch;
            if (!node.nexts.containsKey(index)) {
                return 0;
            }
            node = node.nexts.get(index);
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

        Node.Node2 node = root;
        int index = 0;
        for (char ch : pre.toCharArray()) {
            index = ch;
            if (!node.nexts.containsKey(index)) {
                return 0;
            }
            node = node.nexts.get(index);
        }
        return node.pass;
    }
}
