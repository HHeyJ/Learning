package com.hyq.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nanke
 * @date 2020/9/14 上午11:46
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TwoSum {

    public static void main(String[] args) {

        int[] nums = new int[]{1,2,3,4,5,6,7,8};

        int[] execute = execute1(nums, 9);
        System.out.println(execute);

    }

    /**
     * 暴力
     * @param nums
     * @param target
     * @return
     */
    public static int[] execute(int nums[], int target) {

        int[] result = new int[2];

        for (int i = 0; i < nums.length; i++) {
            for (int y = i + 1; y < nums.length; y++) {
                if (nums[y] == target - nums[i]) {
                    result[0] = i;
                    result[1] = y;
                    return result;
                }
            }
        }
        return result;
    }

    /**
     * 哈希
     * 1、因为只存在唯一解，所以当发生哈希碰撞的时候可以直接覆盖,因为重复出现的肯定不是我们的解
     * @param nums
     * @param target
     * @return
     */
    public static int[] execute1(int nums[], int target) {
        // 初始化过程
        Map<Integer,Integer> hash = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            hash.put(nums[i],i);
        }

        for (int i = 0; i < nums.length; i++) {
            int value = target - nums[i];
            if (hash.containsKey(value) && hash.get(value) != i) {
                return new int[] { i, hash.get(value) };
            }
        }
        return new int[2];
    }

    /**
     * 哈希
     * 1、在放置的时候即可判断
     * @param nums
     * @param target
     * @return
     */
    public static int[] execute2(int nums[], int target) {

        Map<Integer,Integer> hash = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int value = target - nums[i];
            if (hash.containsKey(value)) {
                return new int[] { i, hash.get(value) };
            }
            hash.put(nums[i],i);
        }
        return new int[2];
    }
}
