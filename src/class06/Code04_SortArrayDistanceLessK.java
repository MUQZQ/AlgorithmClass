package class06;

import java.util.Arrays;
import java.util.PriorityQueue;

import static utils.LogarithmArray.*;

/**
 * 已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的。
 * 请选择一个合适的排序策略，对这个数组进行排序。
 */
public class Code04_SortArrayDistanceLessK {
    public static void sortedArrDistanceLessK(int[] arr, int k) {
        if (k == 0) {
            return;
        }

        int length = arr.length;
        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;

        // 添加K个或者全部数据，
        for (; index <= Math.min(length, k) - 1; index++) {
            heap.add(arr[index]);
        }

        int i = 0;
        for (; index < length; i++, index++) {
            heap.add(arr[index]);
            heap.poll();
        }

        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }

    /**
     * 生成基本有序（移动不超过K）的随机数组
     *
     * @param maxSize
     * @param maxValue
     * @param K
     * @return
     */
    public static int[] randomArrayNoMoveMoreK(int maxSize, int maxValue, int K) {
        // 生成随机数组
        int[] arr = generateRandomArray(maxSize, maxValue);

        // 数组先排个序
        Arrays.sort(arr);
        /*
         * 然后开始随意交换，但是保证每个数据距离不超过K
         * swap[i]=true 表示i位置的数据已经交换过
         * swap[i]=false 表示i位置的数据没有交换过
         */
        int length = arr.length;
        boolean[] isSwap = new boolean[length];
        for (int i = 0; i < length; i++) {
            int j = Math.min(length - 1, (int) (Math.random() * (K + 1) + i));
            if (!isSwap[i] && isSwap[j]) {
                isSwap[i] = true;
                isSwap[j] = true;
                swap(arr, i, j);
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        int testTimes = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;

        for (int i = 0; i < testTimes; i++) {
            int k = (int) (Math.random() * (maxSize) + 1);
            int[] arr = randomArrayNoMoveMoreK(maxSize, maxValue, k);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);

            sortedArrDistanceLessK(arr1, k);
            comparator(arr2);

            if (isNotEqual(arr1, arr2)) {
                succeed = false;
                System.out.println("k = " + k);
                printArray(arr);
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice" : "test Failed");
    }
}
