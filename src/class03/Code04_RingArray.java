package class03;

/**
 * @description:数组实现队列
 * @author: QZQ
 * @date: 2022/9/18
 **/
public class Code04_RingArray {
    public static class MyQueue {
        private final int limit;
        private int[] arr;
        private int pushI;//end
        private int pollI;//begin
        private int size;

        public MyQueue(int limit) {
            this.limit = limit;
            arr = new int[limit];
            pollI = 0;
            pushI = 0;
            size = 0;
        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("队列空了，不能再拿了");
            }
            size--;
            int ans = arr[pollI];
            pollI = nextIndex(pollI);
            return ans;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("队列满了，不能再添加");
            }
            size++;
            arr[pushI] = value;
            pushI = nextIndex(pushI);
        }

        /**
         * 如果现在的下标是i，返回下一个位置
         *
         * @param i
         * @return
         */
        private int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }
    }
}
