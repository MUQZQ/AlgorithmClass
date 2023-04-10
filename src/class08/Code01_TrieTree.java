package class08;

import class08.Node.Node2;

import java.util.HashMap;

import static class08.ForTest.generateRandomStringArray;
import static class08.Node.Node1;

/**
 * 该程序的对数器跑不过，你能发现bug在哪吗？
 */
public class Code01_TrieTree {
    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            Trie1 trie1 = new Trie1();
            Trie2 trie2 = new Trie2();
            Right right = new Right();
            for (String s : arr) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(s);
                    trie2.insert(s);
                    right.insert(s);
                } else if (decide < 0.5) {
                    trie1.delete(s);
                    trie2.delete(s);
                    right.delete(s);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(s);
                    int ans2 = trie2.search(s);
                    int ans3 = right.search(s);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(s);
                    int ans2 = trie2.prefixNumber(s);
                    int ans3 = right.prefixNumber(s);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");
    }

    public static class Trie1 implements class08.Trie {
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
            Node1 node = root;
            int index = 0;
            for (int i = 0; i < word.length(); i++) { //从左到右遍历字符
                index = word.charAt(i) - 'a'; // 由字符，对应走那条路
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Node1();
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
                Node1 node = root;
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
            Node1 node = root;
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
            Node1 node = root;
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

    public static class Trie2 implements class08.Trie {
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

            Node2 node = root;
            node.pass++;
            int index = 0;
            for (char ch : word.toCharArray()) {
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

                Node2 node = root;
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

            Node2 node = root;
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

            Node2 node = root;
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


    public static class Right implements class08.Trie {
        private final HashMap<String, Integer> box;

        public Right() {
            this.box = new HashMap<>();
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
                    count++;
                }
            }
            return count;
        }
    }
}
