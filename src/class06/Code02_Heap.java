package class06;

import static utils.LogarithmArray.swap;

public class Code02_Heap {
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

        private void heapify(int[] heap, int index, int heapSize) {

        }

        /**
         * 新加进来的数，现在停在index位置，请依次往上移动
         * 移动到0位置，或者干不掉自己的父亲，停
         *
         * @param heap  堆
         * @param index 索引
         */
        private void heapInsert(int[] heap, int index) {
            int fatherIndex = (index - 1) / 2; // 因为数组索引从0开始
            while (heap[index] > heap[fatherIndex]) {
                swap(heap, index, fatherIndex);
                index = fatherIndex;
            }
        }

    }


}
