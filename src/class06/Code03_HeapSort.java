package class06;

import java.util.PriorityQueue;

import static utils.LogarithmArray.*;

/**
 * 堆排序，优先队列（小根堆，可以传入比较器）
 */
public class Code03_HeapSort {
    public static void main(String[] args) {
        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(6);
        heap.add(8);
        heap.add(0);
        heap.add(2);
        heap.add(9);
        heap.add(1);

        while (!heap.isEmpty()) {
            System.out.println("heap.poll() = " + heap.poll());
        }

        int testTimes = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            heapSort(arr1);
            comparator(arr2); // 排序对比
            if (isNotEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        heapSort(arr);
        printArray(arr);
    }

    /**
     * 堆排序额外空间复杂堆O(1)
     *
     * @param arr array
     */
    private static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int heapSize = arr.length;

        // 从上往下的方法，时间复杂度O（N*logN）
        for (int i = 0; i < heapSize; i++) {
            heapInsert(arr, i);
        }

        // 从下到上的方法，时间复杂度O（N）
//        for (int i = heapSize - 1; i >= 0; i--) {
//            heapify(arr, i, heapSize);
//        }

        // 从大根堆结构中，将大的数调整到后面，达到排序目的
        // O(N*logN)
        while (heapSize > 0) { // O(N)
            heapify(arr, 0, heapSize); // O(logN)
            swap(arr, 0, --heapSize); // O(1)
        }
    }


    // arr[index]位置的数，能否往下移动
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1; // 左孩子的下标
        while (left < heapSize) { // 下方还有孩子的时候
            // 两个孩子中，谁的值大，把下标给largest
            // 1）只有左孩子，left -> largest
            // 2) 同时有左孩子和右孩子，右孩子的值<= 左孩子的值，left -> largest
            // 3) 同时有左孩子和右孩子并且右孩子的值> 左孩子的值， right -> largest
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 父和较大的孩子之间，谁的值大，把下标给largest
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }


    /**
     * arr[index]刚来的数，往上
     *
     * @param arr
     * @param index
     */
    private static void heapInsert(int[] arr, int index) {
        int fatherIndex = (index - 1) / 2;
        while (arr[index] > arr[fatherIndex]) {
            swap(arr, index, fatherIndex);
            index = fatherIndex;
            fatherIndex = (index - 1) / 2;
        }
    }
}
