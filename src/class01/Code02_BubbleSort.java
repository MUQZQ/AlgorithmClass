package class01;

import java.util.Arrays;

/**
 * @description: 冒泡排序
 * @author: QZQ
 * @date: 2022/8/14
 **/
public class Code02_BubbleSort {
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        //双重循环 第一层：控制作用域 第二层：实际操作
        //冒泡是从下往上冒泡，所以最上的位置最先排好序
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    private static void swap(int[] arr, int j, int i) {
//        int tmp = arr[j];
//        arr[j] = arr[i];
//        arr[i] = tmp;

        //传入的两个参数位置不一致
        arr[j] = arr[j] ^ arr[i];
        arr[i] = arr[j] ^ arr[i];
        arr[j] = arr[j] ^ arr[i];
    }

    //for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        System.arraycopy(arr, 0, res, 0, arr.length);
        return res;
    }

    //for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    //for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    //for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null) {
            return true;
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

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int x : arr) {
            System.out.print(x + ",");
        }
        System.out.println();
    }

    //for test
    public static void main(String[] args) {
        int testTime = 2000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            bubbleSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }
}
