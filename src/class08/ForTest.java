package class08;

import java.util.Objects;

public class ForTest {
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen + 1)];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    public static void testStart(String codeTy) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);

            Trie trie1 = new Trie1();
            Trie trie2 = new Trie2();
            Trie right;
            if (!Objects.equals(codeTy, "Code01")) {
                right = new Code02_TrieTree.Right();
            } else {
                right = new Code01_TrieTree.Right();
            }

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
}
