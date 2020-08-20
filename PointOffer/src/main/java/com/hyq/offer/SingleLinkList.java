package com.hyq.offer;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @author nanke
 * @date 2020/8/20 上午11:06
 *
 * 单向链表
 */
@Data
public class SingleLinkList<T> {

    public static void main(String[] args) {

        // 创建一个链表
        SingleLinkList<Integer> singleList = new SingleLinkList();
        singleList.add(1);
        singleList.add(2);
        singleList.add(3);
        singleList.add(3,-1);

        singleList.remove(0);
        System.out.println(singleList.toString());
        System.out.println(singleList.length());

    }

    private Node<T> head;

    /**
     * 链表新增节点
     * @param value
     */
    public void add(T value) {
        if (this.head == null) {
            head = new Node<>(value);
        } else {
            Node<T> temp = head;
            while (temp.aNode != null) {
                temp = temp.aNode;
            }
            temp.aNode = new Node<>(value);
        }
    }

    /**
     * 指定位置新增节点
     * @param index
     * @param value
     */
    public void add(int index ,T value) {
        if (index < 0 || index > length()) {
            System.out.println("java.lang.IndexOutOfBoundsException: Index: " + index + ", Size: " + length());
            return ;
        }

        if (head == null) {
            head = new Node<>(value);
            return ;
        }

        int nowIndex = 0;
        Node<T> beforeNode = head;
        while (true) {
            if (index == nowIndex) {
                Node<T> node = new Node<>(value);
                if (index == 0) {
                    node.aNode = head;
                    head = node;
                } else {
                    node.aNode = beforeNode.aNode;
                    beforeNode.aNode = node;
                }
                return ;
            }

            if (nowIndex > 0) {
                beforeNode = beforeNode.aNode;
            }
            nowIndex ++;
        }
    }

    /**
     * 指定位置删除节点
     * @param index
     */
    public void remove(int index) {

        if (index < 0 || index >= length()) {
            System.out.println("java.lang.IndexOutOfBoundsException: Index: " + index + ", Size: " + length());
            return ;
        }

        // 第一个
        if (index == 0) {
            head = head.aNode;
            return ;
        }

        int nowIndex = 1;
        Node<T> beforeNode = head;
        // 最后一个
        if (index == length() - 1) {
            while(true) {
                if (index == nowIndex) {
                    beforeNode.aNode = null;
                    return ;
                }

                beforeNode = beforeNode.aNode;
                nowIndex ++;
            }
        }

        // 中间
        while(true) {
            if (index == nowIndex) {
                beforeNode.aNode = beforeNode.aNode.aNode;
                return ;
            }

            beforeNode = beforeNode.aNode;
            nowIndex ++;
        }
    }

    /**
     * 链表长度
     * @return
     */
    public int length() {
        int length = 0;
        Node<T> temp = head;
        while (temp != null) {
            length ++;
            temp = temp.aNode;
        }
        return length;
    }

    @Override
    public String toString() {
        Node<T> temp = head;
        String str = "";
        while (temp != null) {
            str = str + temp.value + ",";
            temp = temp.aNode;
        }
        if (!StringUtils.isEmpty(str)) {
            str = str.substring(0, str.length() - 1);
        }
        return "[" + str + "]";
    }

    @Data
    public class Node<T> {

        /**
         * 值
         */
        private T value;
        /**
         * 后一个节点
         */
        private Node<T> aNode;

        public Node(T value) {
            this.value = value;
        }
    }
}
