package class01;

import java.util.Arrays;

import static utils.LogarithmArray.generateRandomArray;

/**
 * @description: 二分查找是否存在
 * @author: QZQ
 * @date: 2022/8/14
 **/
public class Code04_BSExist {
    public static boolean exist(int[] sortedArray, int target) {
        if (sortedArray == null || sortedArray.length == 0) {
            return false;
        }

        int L = 0;
        int R = sortedArray.length - 1;
        int mid;

        //L...R
        while (L < R) {// L..R 至少两个数的时候
            mid = L + ((R - L) >> 1);
            if (sortedArray[mid] == target) {
                return true;
            }
            if (target > sortedArray[mid]) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return sortedArray[L] == target;//只有一个数的时候,取左边，不然有可能越界R=-1
    }

    //for test
    public static boolean comparator(int[] sortedArrary, int target) {
        if (sortedArrary == null) {
            return false;
        }
        for (int x : sortedArrary) {
            if (x == target) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        int testTime = 5000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (comparator(arr, value) != exist(arr, value)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
