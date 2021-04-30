package com.leo.algorithm.leetcode;

/**
 * @Copyright zm
 * @FileName: Leo-cloud
 * @Author: lihao
 * @Date: 2020/5/26 3:00 PM
 * @Description: 回文数
 * @author: lihao
 */
public class Question9 {

    public static void main(String[] args) {
        System.out.println(isPalindrome(10101));
    }


    /**
     * 解法1
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x){

        String str = String.valueOf(x);
        int length = str.length();
        int mid =(int) Math.ceil(Double.valueOf(length)/ 2);
        boolean flag=true;
        for (int i = 0; i <mid; i++) {
            char suffix = str.charAt(i);
            char prefix = str.charAt(length-1-i);
            if(suffix!=prefix){
                flag=false;
                break;
            }
        }
        return  flag;
    }

    /**
     * 10101
     * @param x 解法2
     * @return
     */
    public static boolean isPalindrome2(int x) {
        if(x < 0) {
            return false;
        }
        int rev = 0, y = x;
        while(y != 0) {
            rev = rev * 10 + y % 10;
            y = y / 10;
        }
        return x == rev;
    }
}
