package com.leo.algorithm.leetcode;

/**
 * Copyright xxxx
 * FileName: Leo-cloud
 * Author:   lihao
 * Date:     2019/9/19 11:40 AM
 * Description: 斐波那契数列 0 1 1 2 3 5 8 13 21 34 55
 * author: leo
 */

public class Question509 {


    public static void main(String[] args) {
        System.out.println(  fib2(10));
    }

    /***
     * 解法1 递归时间、空间复杂度较高
     * @param num
     * @return
     */
    public  static  int fib (int num ){
        if(num==1 || num ==2)
        {
            return 1;
        }
        return fib(num-1)+fib(num-2);
    }


    public  static  int fib2 (int num ){
       if(num<=1){
           return  num;
       }
       int first=0;
       int second=1;
        for (int i = 2; i <=num; i++) {
            int sum=first+second;
            first=second;
            second=sum;
        }
        return second;
    }



}
