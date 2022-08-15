package class02;

import java.util.HashMap;

/**
 * @description: 数组中所有的数都出现了M次，只有一种数出现了K次
 * @author: QZQ
 * @date: 2022/8/15
 **/
public class Code02_KM {

    public static HashMap<Integer, Integer> map = new HashMap<>();

    //for test
    public static int comparator(int[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int res = 0;
        for (int num : map.keySet()) {
            if (map.get(num) == k) {
                res = num;
                break;
            }
        }
        return res;
    }

    // 请保证arr中，只有一种数出现了K次，其他数都出现了M次
    public static int onlyKTimes(int[] arr, int k, int m) {
        if (map.size() == 0) {
            mapCreater(map);
        }
        int[] t = new int[32];
        // t[0] 0位置的1出现了几个
        // t[i] i位置的1出现了几个
        for (int num : arr) {
            while (num != 0) {
                int rightOne = num & (~num + 1);
                t[map.get(rightOne)]++;
                num ^= rightOne;
            }
        }

        int ans = 0;
        // 如果这个出现了K次的数，就是0
        // 那么下面代码中的 : ans |= (1 << i);
        // 就不会发生
        // 那么ans就会一直维持0，最后返回0，也是对的！
        for (int i = 0; i < 32; i++) {
            if (t[i] % m != 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }

    private static void mapCreater(HashMap<Integer, Integer> map) {
        int value = 1;
        for (int i = 0; i < 32; i++) {
            map.put(value, i);
            value <<= 1;
        }
    }
}
