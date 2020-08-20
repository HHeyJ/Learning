package com.hyq.offer;

/**
 * @author nanke
 * @date 2020/8/19 下午3:51
 *
 * 在一个长度为n的数组里的所有数字都在0~n-1的范围内，数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数字中任意一个重复的数字。例如，如果输入长度为7的数字{2, 3, 1, 0, 2, 5, 3}，那么对应的输出是重复的数字2或者3。
 */
public class RepeatNumber {

    public static void main(String[] args) {

        int[] nums = new int[]{2, 2, 1, 0, 3, 5, 3 ,8 ,8 ,6};

        Integer execute = execute1(nums);
        System.out.println(execute);
    }


    /**
     * 单数组替换法
     * @param nums
     * @return
     */
    public static Integer execute(int[] nums)  {

        if (nums.length <= 0) {
            return -1;
        }

        for (int num : nums) {
            if (num >= nums.length) {
                return -1;
            }
        }

        for (int index = 0; index < nums.length; index++) {

            while (index != nums[index]) {
                int indexNum = nums[index];
                if (indexNum == nums[indexNum]) {
                    return indexNum;
                }
                int numNum = nums[indexNum];
                nums[indexNum] = indexNum;
                nums[index] = numNum;
            }
        }

        return -1;
    }

    /**
     * boolean数组确定法
     * @param nums
     * @return
     */
    public static Integer execute1(int[] nums) {

        if (nums.length <= 0) {
            return -1;
        }

        for (int num : nums) {
            if (num >= nums.length) {
                return -1;
            }
        }

        boolean[] booleanArray = new boolean[nums.length];

        for (int index = 0; index < nums.length; index++) {
            if (!booleanArray[nums[index]]) {
                booleanArray[nums[index]] = true;
            } else {
                return nums[index];
            }
        }
        return -1;
    }
}
