package class04;

import utils.LogarithmArray;


/**
 * @description: 归并排序，也要求逆序对数量返回
 * @author: QZQ
 * @date: 2022/11/27
 **/
public class Code03_ReversePair {
    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = LogarithmArray.generateRandomArray(maxSize, maxValue);
            int[] arr2 = LogarithmArray.copyArray(arr1);
            if (reversePairNumber(arr1) != comparator(arr2)) {
                System.out.println("Oops!");
                LogarithmArray.printArray(arr1);
                LogarithmArray.printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private static int reversePairNumber(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        return process(arr, 0, arr.length - 1);
    }

    // arr[L..R]既要排好序，也要求逆序对数量返回
    // 所有merge时，产生的逆序对数量，累加，返回
    // 左 排序 merge并产生逆序对数量
    // 右 排序 merge并产生逆序对数量
    private static int process(int[] arr, int l, int r) {
        if (r == l) return 0;
        int mid = l + ((r - l) >> 1); // l < r
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    private static int merge(int[] arr, int L, int m, int r) {
        int[] help = new int[r - L + 1];
        int i = help.length - 1;
        int p1 = m;
        int p2 = r;
        int res = 0;
        while (p1 >= L && p2 > m) {
            res += arr[p1] > arr[p2] ? (p2 - m) : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= L) {
            help[i--] = arr[p1--];
        }
        while (p2 > m) {
            help[i--] = arr[p2--];
        }
        System.arraycopy(help, 0, arr, L, help.length);
        return res;
    }
}
