package class01;

import java.util.Arrays;

import static utils.LogarithmArray.generateRandomArray;
import static utils.LogarithmArray.printArray;

/**
 * @description: 在数组arr上找，满足>=target的最左边位置;arr满足升序
 * @author: QZQ
 * @date: 2022/8/14
 **/
public class Code05_BSNearLeft {
    public static int nearestIndex(int[] arr, int target) {
        int index = -1;
        int L = 0;
        int R = arr.length - 1;
        int mid;
        while (R >= L) {
            mid = L + ((R - L) >> 1);
            if (target > arr[mid]) {
                L = mid + 1;
            } else {
                R = mid - 1;
                index = mid; //mid 为已知arr[mid] >= target,最左边位置
            }
        }
        return index;
    }

    public static int comparator(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int testTime = 5000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray();
            Arrays.sort(arr);
            int maxValue = 100;
            int target = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * maxValue);
            if (nearestIndex(arr, target) != comparator(arr, target)) {
                printArray(arr);
                System.out.println(target);
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
