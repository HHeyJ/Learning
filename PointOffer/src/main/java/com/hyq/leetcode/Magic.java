package com.hyq.leetcode;

/**
 * @author nanke
 * @date 2020/9/19 下午4:25
 */
public class Magic {

    public static void main(String[] args) {

        int[] target = new int[]{2,4,3,1,5};
        boolean execute = execute(target);
        System.out.println(execute);
    }

    public static boolean execute(int[] target) {

        int[] kArray = new int[target.length];
        for (int i = 0; i < kArray.length; i++) {
            kArray[i] = i+1;
        }
        kArray = move(kArray);

        int k = 0;
        for (int i = 0; i < target.length; i++) {
            if (target[i] == kArray[i]) {
                k++;
            } else {
                break;
            }
        }

        if (k < 1) {
            return false;
        }

        int[] orderArray = new int[target.length];
        for (int i = 0; i < orderArray.length; i++) {
            orderArray[i] = i+1;
        }

        int i = 0;
        int x = 0;
        int[] otherTarget = new int[target.length];
        while(orderArray.length > 0) {
            orderArray = move(orderArray);
            for (int y = 0; y < k && y < orderArray.length; i++,y++) {
                otherTarget[i] = orderArray[y];
            }
            orderArray = remove(orderArray, k);

            for (; x < i; x++) {
                if (target[x] != otherTarget[x]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int[] move(int[] orderArray) {

        int twoSize = orderArray.length / 2;
        int[] moveArray = new int[orderArray.length];
        int i = 0;
        for (; i < twoSize; i++ ) {
            moveArray[i] = orderArray[i * 2 + 1];
        }

        for (int y = 0; i < orderArray.length; y++,i++) {
            moveArray[i] = orderArray[y * 2];
        }
        return moveArray;
    }

    public static int[] remove(int[] orderArray, int k) {

        if (orderArray.length <= 0 || orderArray.length < k) {
            return new int[0];
        }

        int[] removeArray = new int[orderArray.length - k];
        for (int i = 0; i < removeArray.length; i++) {
            removeArray[i] = orderArray[k + i];
        }
        return removeArray;
    }
}
