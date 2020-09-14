package com.hyq.offer;


/**
 * @author nanke
 * @date 2020/8/27 下午5:01
 *
 * 二分查找
 * 1、找出顺序数组中的某个数，若不存在返回-1，存在则返回下标
 * 2、
 */
public class BinarySearch {

    public static void main(String[] args) {

        int[] nums = new int[]{6,7,8,9,11,13,14,15,18,19};
        int execute = execute(nums, 12);
        System.out.println(execute);
    }

    /**
     * 1、找出顺序数组中的某个数，若不存在返回-1，存在则返回下标
     */
    public static int execute(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;
        // 终止条件 [right+1,right]
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 2、把一个数组最开始的若干个元素搬到末尾，我们称之为数组的旋转，输入一个递增顺序数组的一个旋转，输出旋转数组的最小元素。
     * eg:[3,4,5,1,2]为[1,2,3,4,5]的一个旋转，该数组最小元素为1
     */
    public static int execute(int[] nums) {

        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (nums[left] >= nums[right]) {
            if (right - left == 1) {
                return right;
            }


        }


        return -1;
    }




}
