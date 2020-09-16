package com.hyq.leetcode;

import lombok.Data;

/**
 * @author nanke
 * @date 2020/9/14 下午11:54
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TwoNumSum {

    @Data
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {

        ListNode l11 = new ListNode(9);

        ListNode l21 = new ListNode(1);
        ListNode l22 = new ListNode(9);
        ListNode l23 = new ListNode(9);
        ListNode l24 = new ListNode(9);
        ListNode l25 = new ListNode(9);
        ListNode l26 = new ListNode(9);
        ListNode l27 = new ListNode(9);
        ListNode l28 = new ListNode(9);
        ListNode l29 = new ListNode(9);
        ListNode l210 = new ListNode(9);
        l21.next = l22;
        l22.next = l23;
        l23.next = l24;
        l24.next = l25;
        l25.next = l26;
        l26.next = l27;
        l27.next = l28;
        l28.next = l29;
        l29.next = l210;

        ListNode execute = execute(l11, l21);
    }

    /**
     * 双指针,一个定头,一个右移
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode execute(ListNode l1, ListNode l2) {

        ListNode head = new ListNode(0);
        ListNode change = head;
        int temp = 0;

        while (l1 != null || l2 != null) {

            int one = l1 != null ? l1.val : 0;
            int two = l2 != null ? l2.val : 0;
            int sum = one + two + temp;

            change.next = new ListNode(sum % 10);
            change = change.next;
            temp = sum / 10;

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        if (temp > 0) {
            change.next = new ListNode(temp);
        }

        return head.next;
    }
}
