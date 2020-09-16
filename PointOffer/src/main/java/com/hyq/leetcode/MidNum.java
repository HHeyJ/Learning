package com.hyq.leetcode;

/**
 * @author nanke
 * @date 2020/9/15 下午2:57
 *
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * 示例 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * 则中位数是 2.0
 *
 *  示例 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MidNum {

    public static void main(String[] args) {

        int[] nums1 = new int[]{1};
        int[] nums2 = new int[]{2,3,4,5,6};

        double medianSortedArrays = findMedianSortedArrays(nums1, nums2);
        System.out.println(medianSortedArrays);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int totalLength = nums1.length + nums2.length;
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        } else {
            int midIndex1 = totalLength / 2;
            double median = (getKthElement(nums1, nums2, midIndex1) + getKthElement(nums1, nums2, midIndex1 + 1)) / 2.0;
            return median;
        }
    }

    /**
     * 寻找2个升序数组中第k大的数
     * @param nums1
     * @param nums2
     * @param k
     * @return
     *
     * 方法：要找到第 k (k>1) 小的元素，那么就取 x = nums1[k/2-1] 和 y = nums2[k/2-1] 进行比较
     * nums1 中小于等于 x 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
     * nums2 中小于等于 y 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
     * 取 pivot = min(x, y)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
     * 这样 pivot 本身最大也只能是第 k-1 小的元素
     * [1,2,3]
     * [4,5,6]
     */
    public static int getKthElement(int[] nums1, int[] nums2, int k) {

        int index1 = 0, index2 = 0, newIndex1 = 0, newIndex2 = 0;

        while (true) {
            /**
             * 如果数组1达到临界点，取数组2截取部分第k大的数
             */
            if (index1 == nums1.length) {
                return nums2[index2 + k -1];
            }
            /**
             * 如果数组2达到临界点，取数组1截取部分第k大的数
             */
            if (index2 == nums2.length) {
                return nums1[index1 + k -1];
            }
            /**
             * 取截取部分最小数
             */
            if (k == 1) {
                return Math.min(nums1[index1],nums2[index2]);
            }

            int half = k / 2;
            newIndex1 = Math.min(index1 + half, nums1.length) - 1;
            newIndex2 = Math.min(index2 + half, nums2.length) - 1;
            int x = nums1[newIndex1]; int y = nums2[newIndex2];
            if (x <= y) {
                /**
                 * 已经排除了k/2-1个比第k个数小的数,所以要找第k-(k/2-1)个数
                 */
                k -= newIndex1 - index1 + 1;
                /**
                 * 裁剪掉k/2-1部分
                 */
                index1 = newIndex1 + 1;
            } else {
                k -= newIndex2 - index2 + 1;
                index2 = newIndex2 + 1;
            }
        }
    }

}
