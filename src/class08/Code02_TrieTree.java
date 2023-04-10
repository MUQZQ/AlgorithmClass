package class08;

import java.util.HashMap;

import static class08.ForTest.testStart;

// 该程序完全正确
public class Code02_TrieTree {
    public static void main(String[] args) {
        testStart("Code02");
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
                    count += box.get(cur); // 正确获取当前数
                }
            }
            return count;
        }
    }
}
