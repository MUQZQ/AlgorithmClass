package class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @description: TwoQueueImplementStack
 * @author: QZQ
 * @date: 2022/10/7
 * <p>
 * 每次取数据时，将元素转移到help中，只剩最后一个元素最为答案返回
 **/
public class Code07_TwoQueueImplementStack {
    public static void main(String[] args) {
        System.out.println("test begin");
        TwoQueueStack<Integer> myStack = new TwoQueueStack<>();
        Stack<Integer> test = new Stack<>();
        int testTime = 1000000;
        int max = 1000000;
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty()) {
                if (!test.isEmpty()) {
                    System.out.println("Oops");
                }
                int num = (int) (Math.random() * max);
                myStack.push(num);
                test.push(num);
            } else {
                if (Math.random() < 0.25) {
                    int num = (int) (Math.random() * max);
                    myStack.push(num);
                    test.push(num);
                } else if (Math.random() < 0.5) {
                    if (!myStack.peek().equals(test.peek())) {
                        System.out.println("Oops");
                    }
                } else if (Math.random() < 0.75) {
                    if (!myStack.poll().equals(test.pop())) {
                        System.out.println("Oops");
                    }
                } else {
                    if (myStack.isEmpty() != test.isEmpty()) {
                        System.out.println("Oops");
                    }
                }
            }
        }

        System.out.println("test finish!");

    }

    public static class TwoQueueStack<T> {
        public Queue<T> queue;
        public Queue<T> help;

        public TwoQueueStack() {
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }

        public T poll() {
            //将元素转移到help中，只剩最后一个元素最为答案返回
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            T ans = queue.poll();

            //队列替换
            Queue<T> tmp = queue;
            queue = help;
            help = tmp;

            return ans;
        }

        public T peek() {
            //将元素转移到help中，只剩最后一个元素最为答案返回
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            T ans = queue.poll();

            //不做删除要将最后一个元素也转移
            help.offer(ans);

            //队列替换
            Queue<T> tmp = queue;
            queue = help;
            help = tmp;

            return ans;
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

        public void push(T num) {
            queue.offer(num);
        }
    }
}
