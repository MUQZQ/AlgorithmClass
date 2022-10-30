package class04;

import utils.LogarithmArray;

/**
 * @description:
 * @author: QZQ
 * @date: 2022/10/31
 **/
public class Code01_MergeSort {
    // 递归方法
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }


    /**
     * 把arr[L...R]排有序
     * l...r N
     * T(N) = 2 * T( N / 2 ) +O(N)
     * O(N * logN)
     *
     * @param arr arr
     * @param L   L
     * @param R   R
     */
    private static void process(int[] arr, int L, int R) {
        if (L == R) { // base case
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int L, int mid, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 要么p1越界了，要么p2越界了
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        System.arraycopy(help, 0, arr, L, help.length);
    }

    // 非递归方法实现
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;

        // 步长
        int mergeSize = 1;
        while (mergeSize < N) {
            // 当前左组的第一个位置
            int L = 0;
            while (L < N) {
                if (mergeSize >= N - L) {
                    break;
                }
                int M = L + mergeSize - 1;
                int R = M + Math.min(mergeSize, N - M - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            // 防止溢出
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    public static void main(String[] args) {
        int testTime = 50000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = LogarithmArray.generateRandomArray(maxSize, maxValue);
            int[] arr2 = LogarithmArray.copyArray(arr1);
            mergeSort1(arr1);
            mergeSort2(arr2);
            if (LogarithmArray.isNotEqual(arr1, arr2)) {
                System.out.println("出错了！");
                LogarithmArray.printArray(arr1);
                LogarithmArray.printArray(arr2);
            }
        }
        System.out.println("测试结束！");
    }
}
