package class08;

import static utils.LogarithmArray.*;

/**
 * 计数排序
 * only for 0~200 value
 */
public class Code03_CountSort {
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 150;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue, false);
            int[] arr2 = copyArray(arr1);
            countSort(arr1);
            comparator(arr2);
            if (isNotEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue, false);
        printArray(arr);
        countSort(arr);
        printArray(arr);
    }

    private static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        // 获取数组中最大值
        int max = Integer.MIN_VALUE;
        for (int value : arr) {
            max = Math.max(max, value);
        }

        /*
         * 生成桶，保证相同数据放到一个桶中
         * 计数
         */
        int[] bucket = new int[max + 1];
        for (int k : arr) {
            bucket[k]++;
        }

        // 根据桶的顺序取数据放回到原数组
        int i = 0;
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0) {
                arr[i++] = j;
            }
        }
    }
}
