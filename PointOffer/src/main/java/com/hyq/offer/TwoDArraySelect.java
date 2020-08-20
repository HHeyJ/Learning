package com.hyq.offer;

/**
 * @author nanke
 * @date 2020/8/19 下午5:51
 *
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *          1    2   8   9
 *          2    4   9   12
 *          4    7   10  13
 *          6    8   11  15
 *
 * 首先选取数组中右上角的数字。如果该数字等于要查找的数字，查找过程结束；
 * 如果该数字大于要查找的数组，剔除这个数字所在的列；
 * 如果该数字小于要查找的数字，剔除这个数字所在的行。
 * 也就是说如果要查找的数字不在数组的右上角，则每一次都在数组的查找范围中剔除一行或者一列，这样每一步都可以缩小查找的范围，直到找到要查找的数字，或者查找范围为空。
 */
public class TwoDArraySelect {

    public static void main(String[] args) {

        int[][] nums = new int[4][4];
        nums[0] = new int[]{1,2,8,9};
        nums[1] = new int[]{2,4,9,12};
        nums[2] = new int[]{4,7,10,13};
        nums[3] = new int[]{6,8,11,15};

        System.out.println(execute(nums,16));
    }


    public static boolean execute(int[][] nums, int num) {
        // 行
        int rowNum = nums.length;
        // 列
        int colNum = nums[0].length;

        int row = 0;
        int col = colNum - 1;
        while(row < rowNum && col >= 0 ) {

            if (nums[row][col] == num) {
                return true;
            } else if (nums[row][col] > num) {
                col --;
            } else {
                row ++;
            }
        }
        return false;
    }


}
