package com.hyq.leetcode;

/**
 * @author nanke
 * @date 2020/9/19 下午3:15
 */
public class BlackWhite {


    public static void main(String[] args) {

        int execute = execute(2, 2);
        System.out.println(execute);
    }

    public static int execute(int n, int k) {

        if (k <= 0 || k == n * n) {
            return 1;
        }

        if (k > n * n) {
            return 0;
        }

        int count = 0;
        for (int x = 0; x <= n; x ++) {

            int upA = 1;
            for (int a = 0; a < x; a++) {
                upA = upA * (n - a);
            }
            int downA = 1;
            for (int a = 1; a <= x; a++) {
                downA = downA * a;
            }

            for (int y = 0; y <= n; y++) {
                int upB = 1;
                for (int a = 0; a < y; a++) {
                    upB = upB * (n - a);
                }
                int downB = 1;
                for (int a = 1; a <= y; a++) {
                    downB = downB * a;
                }

                int size = size(x, y, n);
                if (size == k) {
                    count += (upA/downA) * (upB/downB);
                }
            }
        }
        return count;
    }

    public static int size(int x, int y, int n) {

        if (x == 0 || y == 0) {
            return x * n + y * n;
        } else {
            return x * n + y * n - (x * y);
        }
    }

}
