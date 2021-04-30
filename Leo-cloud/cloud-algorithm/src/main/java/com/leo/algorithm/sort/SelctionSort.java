package com.leo.algorithm.sort;

/**
 * @Copyright zm
 * @FileName: Leo-cloud
 * @Author: lihao
 * @Date: 2020/11/12 9:31 下午
 * @Description:
 * @author: lihao
 */
public class SelctionSort {


    public static void main(String[] args) {

    }

    /**
     *  选择排序 1 3 2
     * @param nums
     */
    private void selecionSort(int[] nums){

        for (int end =nums.length-1 ; end>0; end--) {
            int maxIndex=0;
            for (int begin = 1; begin <=end; begin++) {
                if(nums[maxIndex]<=nums[begin]){
                    maxIndex=begin;
                }
            }
            int tmp=nums[maxIndex];
            nums[maxIndex]=nums[end];
            nums[end]=tmp;
        }
    }

    /**
     * 插入排序
     * @param nums
     */
    private void insertSort(int[] nums){

        for (int begin = 0; begin <nums.length; begin++) {
            int cur=begin;
            while (cur>0 && nums[cur]-nums[cur-1]<0){
                int tem= nums[cur];
                nums[cur]=nums[cur-1];
                nums[cur-1]=tem;
                cur--;
            }
        }
    }

}
