package class04;

import utils.LogarithmArray;

/**
 * @description: 归并排序，返回小和
 * @author: QZQ
 * @date: 2022/11/27
 **/
public class Code02_SmallSum {
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return process(arr, 0, arr.length - 1);
    }

    // arr[L..R]既要排好序，也要求小和返回
    // 所有merge时，产生的小和，累加
    // 左 排序   merge
    // 右 排序  merge
    // merge
    private static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);

    }

    private static int merge(int[] arr, int l, int mid, int r) {
        int p1 = l;
        int p2 = mid + 1;
        int[] help = new int[r - l + 1];
        int i = 0;
        int res = 0;
        while (p1 <= mid && p2 <= r) {
            if (arr[p1] < arr[p2]) {
                // 先算结果，再赋值，因为有指针变动的副作用
                res += (r - p2 + 1) * arr[p1];
                help[i++] = arr[p1++];
            } else {
                help[i++] = arr[p2++];
            }
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        //每次排完序，都要将原数组排序部分刷新
        System.arraycopy(help, 0, arr, l, help.length);
        return res;
    }

    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0; // 右边比左边小
            }
        }
        return res;
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = LogarithmArray.generateRandomArray(maxSize, maxValue);
            int[] arr2 = LogarithmArray.copyArray(arr1);
            if (smallSum(arr1) != comparator(arr2)) {
                succeed = false;
                LogarithmArray.printArray(arr1);
                LogarithmArray.printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
