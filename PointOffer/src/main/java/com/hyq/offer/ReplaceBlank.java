package com.hyq.offer;

/**
 * @author nanke
 * @date 2020/8/19 下午6:25
 *
 * 请实现一个函数，把字符串中的每个空格都替换成"%20"。
 * 例如输入 "How are you"，输出 "How%20are%20you"。
 *
 * 先找出字符串中出现的空格次数，然后确定新数组的长度，将老数组和需要替换的字符移入到新数组当中。
 */
public class ReplaceBlank {


    public static void main(String[] args) {

        String str = "How are you";
        System.out.println(execute(str));
    }


    public static String execute(String str) {

        int count = 0;
        for (int index = 0; index < str.length(); index++) {
            if (str.charAt(index) == ' ') {
                count ++;
            }
        }

        char[] chars = new char[str.length() + count * 2];
        int strIndex = 0;
        int newIndex = 0;
        while (newIndex < chars.length) {
            if (str.charAt(strIndex) == ' ') {
                chars[newIndex] = '%';
                chars[newIndex + 1] = '2';
                chars[newIndex + 2] = '0';
                newIndex += 3;
                strIndex ++;
            } else {
                chars[newIndex] = str.charAt(strIndex);
                newIndex ++;
                strIndex ++;
            }
        }
        return new String(chars);
    }
}
