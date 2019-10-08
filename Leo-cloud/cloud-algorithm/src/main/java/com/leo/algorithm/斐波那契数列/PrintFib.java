package com.leo.algorithm.斐波那契数列;

/**
 * Copyright xxxx
 * FileName: Leo-cloud
 * Author:   lihao
 * Date:     2019/9/19 11:40 AM
 * Description:
 * author: leo
 */

public class PrintFib {


    public static void main(String[] args) {
        System.out.println(  print(10));
    }

    public  static  int print (int num ){
        if(num==1 || num ==2)
        {
            return 1;
        }
        return print(num-1)+print(num-2);
    }

}
