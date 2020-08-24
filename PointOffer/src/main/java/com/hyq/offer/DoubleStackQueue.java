package com.hyq.offer;

import java.util.Enumeration;
import java.util.Stack;

/**
 * @author nanke
 * @date 2020/8/24 下午3:06
 *
 * 用两个栈实现一个队列，队列的声明如下，请实现它的两个函数 Out（出栈）Into（入栈）
 * 分别完成在队列尾部加插入节点和在队列头部移除节点
 */
public class DoubleStackQueue {

    private static Stack<Integer> aStack;

    private static Stack<Integer> bStack;

    public static int size;

    public DoubleStackQueue() {
        aStack = new Stack();
        bStack = new Stack();
        size = 0;
    }

    public Integer out() {

        if (bStack.size() > 0) {
            size --;
            return bStack.pop();
        }

        if (aStack.size() > 0) {
            Enumeration<Integer> elements = aStack.elements();
            while(elements.hasMoreElements()) {
                bStack.addElement(aStack.pop());
            }
            size --;
            return bStack.pop();
        }
        return -1;
    }

    public boolean into(Integer num) {

        aStack.addElement(num);
        size ++;
        return true;
    }

    public static void main(String[] args) {

        DoubleStackQueue queue = new DoubleStackQueue();
        queue.into(1);
        queue.into(2);
        queue.into(3);
        queue.into(4);
        queue.into(5);
        queue.into(6);
        queue.into(7);

        for (int i = 0; i < 4; i++) {
            System.out.println(queue.out());
        }

        System.out.println("---------");
        queue.into(8);
        queue.into(9);
        for (int i = 0; i < queue.size; ) {
            System.out.println(queue.out());
        }


    }
}
