package class01;

import static utils.LogarithmArray.*;

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
            if (isNotEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice" : "Fuck");
    }
}
