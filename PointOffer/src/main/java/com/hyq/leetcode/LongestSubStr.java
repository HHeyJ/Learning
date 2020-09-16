package com.hyq.leetcode;

import com.taobao.diamond.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author nanke
 * @date 2020/9/15 上午10:20
 *
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestSubStr {

    public static void main(String[] args) {

        int abcabc = execute("aabaabcse");
        System.out.println(abcabc);
    }

    public static int execute(String str) {

        if (str == null || str.length() <= 0) {
            return 0;
        }

        int maxLength = 0;
        Map<Character,Integer> map = new HashMap<>();
        for (int left = 0, right = 0; right < str.length(); right++) {
            char charAt = str.charAt(right);
            // 当遇到重复字符,将左边界右移
            if (map.containsKey(charAt)) {
                left = Math.max(left,map.get(charAt) + 1);
            }
            map.put(charAt,right);
            maxLength = Math.max(right - left + 1, maxLength);
        }
        return maxLength;
    }
}
