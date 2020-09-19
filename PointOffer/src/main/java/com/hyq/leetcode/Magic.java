package com.hyq.leetcode;

/**
 * @author nanke
 * @date 2020/9/19 下午4:25
 *
 * 魔术排列
 * 秋日市集上，魔术师邀请小扣与他互动。魔术师的道具为分别写有数字 1~N 的 N 张卡牌，然后请小扣思考一个 N 张卡牌的排列 target。
 * 魔术师的目标是找到一个数字 k（k >= 1），使得初始排列顺序为 1~N 的卡牌经过特殊的洗牌方式最终变成小扣所想的排列 target，特殊的洗牌方式为：
 * 第一步，魔术师将当前位于 偶数位置 的卡牌（下标自 1 开始），保持 当前排列顺序 放在位于 奇数位置 的卡牌之前。例如：将当前排列 [1,2,3,4,5] 位于偶数位置的 [2,4] 置于奇数位置的 [1,3,5] 前，排列变为 [2,4,1,3,5]；
 * 第二步，若当前卡牌数量小于等于 k，则魔术师按排列顺序取走全部卡牌；若当前卡牌数量大于 k，则取走前 k 张卡牌，剩余卡牌继续重复这两个步骤，直至所有卡牌全部被取走；
 * 卡牌按照魔术师取走顺序构成的新排列为「魔术取数排列」，请返回是否存在这个数字 k 使得「魔术取数排列」恰好就是 target，从而让小扣感到大吃一惊。
 *
 * 示例 1：
 * 输入：target = [2,4,3,1,5]
 * 输出：true
 *
 * 解释：排列 target 长度为 5，初始排列为：1,2,3,4,5。我们选择 k = 2：
 * 第一次：将当前排列 [1,2,3,4,5] 位于偶数位置的 [2,4] 置于奇数位置的 [1,3,5] 前，排列变为 [2,4,1,3,5]。取走前 2 张卡牌 2,4，剩余 [1,3,5]；
 * 第二次：将当前排列 [1,3,5] 位于偶数位置的 [3] 置于奇数位置的 [1,5] 前，排列变为 [3,1,5]。取走前 2 张 3,1，剩余 [5]；
 * 第三次：当前排列为 [5]，全部取出。
 * 最后，数字按照取出顺序构成的「魔术取数排列」2,4,3,1,5 恰好为 target。
 *
 * 示例 2：
 * 输入：target = [5,4,3,2,1]
 * 输出：false
 * 解释：无法找到一个数字 k 可以使「魔术取数排列」恰好为 target。
 *
 * 提示：
 * 1 <= target.length = N <= 5000
 * 题目保证 target 是 1~N 的一个排列。
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
