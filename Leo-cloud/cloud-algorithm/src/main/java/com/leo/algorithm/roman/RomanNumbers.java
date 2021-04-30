package com.leo.algorithm.roman;

/**
 * @Copyright zm
 * @FileName: Leo-cloud
 * @Author: lihao
 * @Date: 2020/11/8 7:14 下午
 * @Description:
 * @author: lihao
 */
public class RomanNumbers {

    public enum Roman{
        i("i", 1), v("v", 5), x("x", 10), l("l", 50), c("c", 100), d("d", 500), m("m", 1000);

        private int value;
        private String displayValue;
        Roman(String displayValue, int value){
            this.displayValue = displayValue;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public String getDisplayValue() {
            return displayValue;
        }


    }
}
