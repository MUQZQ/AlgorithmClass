package class08;

import java.util.HashMap;

import static class08.ForTest.testStart;
import static class08.Node.Node1;
import static class08.Node.Node2;

// 该程序完全正确
public class Code02_TrieTree {
    public static void main(String[] args) {
        testStart("Code02");
    }


    public static class Trie1 implements Trie {
        private final Node1 root;

        public Trie1() {
            root = new Node1();
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
            char[] str = word.toCharArray();
            Node1 node = root;
            node.pass++;
            int path = 0;
            for (char c : str) { // 从左往右遍历字符
                path = c - 'a'; // 由字符，对应成走向哪条路
                if (node.nexts[path] == null) {
                    node.nexts[path] = new Node1();
                }
                node = node.nexts[path];
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
                char[] chs = word.toCharArray();
                Node1 node = root;
                node.pass--;
                int path = 0;
                for (char ch : chs) {
                    path = ch - 'a';
                    if (--node.nexts[path].pass == 0) {
                        node.nexts[path] = null;
                        return;
                    }
                    node = node.nexts[path];
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
            char[] chs = word.toCharArray();
            Node1 node = root;
            int index = 0;
            for (char ch : chs) {
                index = ch - 'a';
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
            char[] chs = pre.toCharArray();
            Node1 node = root;
            int index = 0;
            for (char ch : chs) {
                index = ch - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }
    }


    public static class Trie2 implements Trie {
        private final Node2 root;

        public Trie2() {
            root = new Node2();
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
            char[] chs = word.toCharArray();
            Node2 node = root;
            node.pass++;
            int index = 0;
            for (char ch : chs) {
                index = ch;
                if (!node.nexts.containsKey(index)) {
                    node.nexts.put(index, new Node2());
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
                char[] chs = word.toCharArray();
                Node2 node = root;
                node.pass--;
                int index = 0;
                for (char ch : chs) {
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
            char[] chs = word.toCharArray();
            Node2 node = root;
            int index = 0;
            for (char ch : chs) {
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
            char[] chs = pre.toCharArray();
            Node2 node = root;
            int index = 0;
            for (char ch : chs) {
                index = ch;
                if (!node.nexts.containsKey(index)) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.pass;
        }
    }

    public static class Right implements Trie {

        private final HashMap<String, Integer> box;

        public Right() {
            box = new HashMap<>();
        }


        /**
         * 添加某个字符串，可以重复添加，每次算1个
         *
         * @param word
         */
        @Override
        public void insert(String word) {
            if (!box.containsKey(word)) {
                box.put(word, 1);
            } else {
                box.put(word, box.get(word) + 1);
            }
        }

        /**
         * 删掉某个字符串，可以重复删除，每次算1个
         *
         * @param word
         */
        @Override
        public void delete(String word) {
            if (box.containsKey(word)) {
                if (box.get(word) == 1) {
                    box.remove(word);
                } else {
                    box.put(word, box.get(word) - 1);
                }
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
            return box.getOrDefault(word, 0);
        }

        /**
         * 查询有多少个字符串，是以str做前缀的
         *
         * @param pre
         * @return
         */
        @Override
        public int prefixNumber(String pre) {
            int count = 0;
            for (String cur : box.keySet()) {
                if (cur.startsWith(pre)) {
                    count += box.get(cur);
                }
            }
            return count;
        }
    }
}
