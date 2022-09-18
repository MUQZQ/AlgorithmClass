package utils;

/**
 * @description:
 * @author: QZQ
 * @date: 2022/9/18
 **/
public class List {
    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data) {
            value = data;
        }
    }

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            value = data;
        }
    }
}
