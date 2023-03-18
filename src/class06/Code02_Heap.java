package class06;

import java.util.Comparator;
import java.util.PriorityQueue;

import static utils.LogarithmArray.swap;

public class Code02_Heap {
    public static void main(String[] args) {
        // PriorityQueue 默认为小根堆，实现大根堆需要传入降序比较器
        testPriorityQueue();
        System.out.println("testPriorityQueue finish! ");

        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;

        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyMaxHeap my = new MyMaxHeap(curLimit);
            RightMaxHeap test = new RightMaxHeap(curLimit);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty() || my.isFull() != test.isFull()) {
                    System.out.println("Oops!");
                }
                // 随机弹出或添加（空加、满弹）
                if (my.isEmpty()) {
                    pushTowHeap(value, my, test);
                } else if (my.isFull()) {
                    justPop(my, test);
                } else {
                    if (Math.random() < 0.5) {
                        justPop(my, test);
                    } else {
                        pushTowHeap(value, my, test);
                    }
                }
            }
        }
        System.out.println("finish!");
    }

    private static void pushTowHeap(int value, MyMaxHeap my, RightMaxHeap test) {
        int curValue = (int) (Math.random() * value);
        my.push(curValue);
        test.push(curValue);
    }

    private static void justPop(MyMaxHeap my, RightMaxHeap test) {
        int testPop = test.pop();
        int myPop = my.pop();
        if (myPop != testPop) {
            System.out.println("Oops!");
            System.out.println("my.pop() = " + myPop + "test.pop() = " + testPop);
        }
    }

    private static void testPriorityQueue() {
        PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComparator());
        heap.add(5);
        heap.add(5);
        heap.add(5);
        heap.add(3);
        // 5 , 3
        System.out.println(heap.peek());
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        heap.add(7);
        heap.add(0);
        System.out.println(heap.peek());
        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }
    }

    public static class MyMaxHeap {
        private final int limit;
        private final int[] heap;
        private int heapSize;

        public MyMaxHeap(int limit) {
            this.limit = limit;
            heap = new int[limit];
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int value) {
            if (isFull()) {
                throw new RuntimeException("heap is full");
            }
            heap[heapSize] = value;
            heapInsert(heap, heapSize++);
        }

        /**
         * 返回大根堆中最大值，并在大根堆中删除最大值
         * 剩下的数据保持大根堆结构
         *
         * @return int
         */
        public int pop() {
            int ans = heap[0];
            swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return ans;

        }


        /**
         * 从index位置，往下看，不断下沉
         * 停；当较大的孩子都不再大于index位置的数；已经没有孩子节点
         *
         * @param heap     堆
         * @param index    索引位置
         * @param heapSize 堆大小
         */
        private void heapify(int[] heap, int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize) { // 存在左孩子，有可能存在右孩子，也可能没有
                int right = left + 1;
                // 把较大孩子的下标，给largest
                int largest = right < heapSize && heap[right] > heap[left] ? right : left;
                if (heap[largest] <= heap[index]) {
                    break;
                } else {
                    // index和较大孩子互换，并更新指针
                    swap(heap, largest, index);
                    index = largest;
                    left = index * 2 + 1;
                }
            }
        }

        /**
         * 新加进来的数，现在停在index位置，请依次往上移动
         * 移动到0位置，或者干不掉自己的父亲，停
         *
         * @param heap  堆
         * @param index 索引
         */
        private void heapInsert(int[] heap, int index) {
            // 因为数组索引从0开始
            while (heap[index] > heap[(index - 1) / 2]) {
                swap(heap, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }
    }

    /**
     * 不是堆结构
     */
    public static class RightMaxHeap {
        private final int[] heap;
        private final int limit;
        private int heapSize;

        public RightMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }


        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int value) {
            if (isFull()) {
                throw new RuntimeException("heap is full");
            }
            heap[heapSize++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < heapSize; i++) {
                if (heap[i] > heap[maxIndex]) {
                    maxIndex = i;
                }
            }
            int ans = heap[maxIndex];
            heap[maxIndex] = heap[--heapSize];
            return ans;
        }
    }

    public static class MyComparator implements Comparator<Integer> {
        /**
         * @param o1 the first object to be compared.
         * @param o2 the second object to be compared.
         * @return 降序
         */
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }
}
