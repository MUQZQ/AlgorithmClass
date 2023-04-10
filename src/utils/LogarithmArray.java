package utils;

import java.util.Arrays;

/**
 * @description: 数组对数器
 * @author: QZQ
 * @date: 2022/8/14
 **/
public class LogarithmArray {
    /**
     * 排序对比器
     *
     * @param arr
     */
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    /**
     * 数据交换
     *
     * @param arr
     * @param j
     * @param i
     */
    public static void swap(int[] arr, int j, int i) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;

       /*  这种写法有一个问题就是，当要交换的两数是同一个时，会发生错误
         arr[i] = arr[i] ^ arr[j];
         arr[j] = arr[i] ^ arr[j];
         arr[i] = arr[i] ^ arr[j];*/
    }

    /**
     * 生成随机数组
     *
     * @param maxSize
     * @param maxValue
     * @return
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        return generateRandomArray(maxSize, maxValue, true);
    }


    /**
     * 生成随机数组(包含负数)
     *
     * @param maxSize
     * @param maxValue
     * @param negative 是否包含负数
     * @return
     */
    public static int[] generateRandomArray(int maxSize, int maxValue, boolean negative) {
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            int v1 = (int) ((maxValue + 1) * Math.random());
            arr[i] = negative ? v1 - (int) (Math.random() * maxValue) : v1;
        }
        return arr;
    }

    /**
     * 生成随机数组
     * maxSize = 10;
     * maxValue = 100;
     *
     * @return
     */
    public static int[] generateRandomArray() {
        return generateRandomArray(100, 100);
    }

    /**
     * 复制数组
     *
     * @param arr
     * @return
     */
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        System.arraycopy(arr, 0, res, 0, arr.length);
        return res;
    }


    /**
     * 对比数组一致性
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1 == null || arr2 == null) {
            return false;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }


    /**
     * 数组不相等
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static boolean isNotEqual(int[] arr1, int[] arr2) {
        return !isEqual(arr1, arr2);
    }

    /**
     * 打印数组
     *
     * @param arr
     */
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int j : arr) {
            System.out.print(j + ",");
        }
        System.out.println();
    }
}
