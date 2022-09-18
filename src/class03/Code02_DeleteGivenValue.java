package class03;

import utils.List;

/**
 * @description:删除链表中特定值得节点
 * @author: QZQ
 * @date: 2022/9/18
 **/
public class Code02_DeleteGivenValue {
    // head = removeValue(head, 2);
    public static List.Node removeValue(List.Node head, int num) {
        //head来到第一个不需要删除的位置
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }

        List.Node pre = head;
        List.Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
