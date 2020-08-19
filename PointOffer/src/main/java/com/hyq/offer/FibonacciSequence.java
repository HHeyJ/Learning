package com.hyq.offer;

/**
 * @author nanke
 * @date 2020/8/17 上午10:06
 *
 * 题目：一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个n级的台阶总共有多少种跳法？
 * 答题思路
 * 如果只有1级台阶，那显然只有一种跳法
 * 如果有2级台阶，那么就有2种跳法，一种是分2次跳。每次跳1级，另一种就是一次跳2级
 * 如果台阶级数大于2，设为n的话，这时我们把n级台阶时的跳法看成n的函数，记为f(n),第一次跳的时候有2种不同的选择：
 *  一是第一次跳一级，此时跳法的数目等于后面剩下的n-1级台阶的跳法数目，即为f(n-1),
 *  二是第一次跳二级，此时跳法的数目等于后面剩下的n-2级台阶的跳法数目，即为f(n-2),
 *  因此n级台阶的不同跳法的总数为f(n) = f(n-1) + f(n-2)，不难看出就是斐波那契数列
 */
public class FibonacciSequence {

    public static void main(String[] args) {
        System.out.println(fibonacci(6));
    }

    public static int fibonacci(int num) {

        if (num == 1) {
            return 1;
        } else if (num == 2) {
            return 2;
        }

        return fibonacci(num - 1) + fibonacci(num -2);
    }
}
