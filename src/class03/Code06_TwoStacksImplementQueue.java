package class03;

import java.util.Stack;

/**
 * @description: TwoStacksImplementQueue
 * @author: QZQ
 * @date: 2022/10/7
 * 队列和栈最大的区别就是，取数据的时候方向不一样
 * 所以要解决这个取数方向问题，可以通过将栈1中的数据导入栈2，
 * 需要确保栈2数据已经被消耗完，才能继续将数据导入栈2，不然栈2数据会被覆盖
 **/
public class Code06_TwoStacksImplementQueue {
    public static void main(String[] args) {
        TwoStacksQueue test = new TwoStacksQueue();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }

    public static class TwoStacksQueue {
        public Stack<Integer> stackPush;
        public Stack<Integer> stackPop;

        public TwoStacksQueue() {
            stackPush = new Stack<>();
            stackPop = new Stack<>();
        }

        /**
         * push栈向pop栈倒入数据
         */
        private void pushToPop() {
            if (stackPop.empty()) {
                while (!stackPush.empty()) {
                    stackPop.push(stackPush.pop());
                }
            }
        }

        public void add(int pushInt) {
            pushToPop();
            stackPush.push(pushInt);
        }

        public int peek() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.peek();
        }

        public int poll() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.pop();
        }
    }
}
