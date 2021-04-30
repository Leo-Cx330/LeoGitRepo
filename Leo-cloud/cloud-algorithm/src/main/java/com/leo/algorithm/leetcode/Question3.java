package com.leo.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright zm
 * @FileName: Leo-cloud
 * @Author: lihao
 * @Date: 2020/5/20 5:54 PM
 * @Description: 无重复的最长子串
 * @author: lihao
 */
public class Question3 {

    //pwwkew
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));

    }

    /**
     * 滑动窗口
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        //最长子串长度
        int len = 0;
        //key：字符 ，value：所在下标
        Map<Character, Integer> map = new HashMap();
        for (int start = 0, end = 0; end < n; end++) {
            //当前字符
            char c = s.charAt(end);
            //如果存在map中
            if (map.containsKey(c)) {
                //当前字符所在的下标与当前起始位置的下标比较取最大值。
                start = Math.max(map.get(c), start);
            }
            //最长字串与当前窗口长度比较。取最大值
            len = Math.max(len, end-start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return len;
    }



}
