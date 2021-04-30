package com.leo.algorithm.leetcode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Copyright zm
 * @FileName: Leo-cloud
 * @Author: lihao
 * @Date: 2020/11/2 6:46 下午
 * @Description:
 * @author: lihao
 */
public class Question349 {


    /**
     * 给定两个数组，编写一个函数来计算它们的交集。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2]
     * 示例 2：
     * <p>
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[9,4]
     *  
     * <p>
     * 说明：
     * <p>
     * 输出结果中的每个元素一定是唯一的。
     * 我们可以不考虑输出结果的顺序。
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> treeSet = new HashSet<>();
        Set<Integer> treeSet2 = new HashSet<>();
        Arrays.stream(nums1).forEach(item -> treeSet.add(item));
        for (int i = 0; i < nums2.length; i++) {
            if(treeSet.contains(nums2[i])){
                treeSet2.add(nums2[i]);
            }
        }
        int[] m=new int[treeSet2.size()];
        int index=0;
       for (Integer a:treeSet2){
           if(treeSet.contains(a)){
               m[index++]=a;
           }
       }
        return m ;
    }

    public static void main(String[] args) {
        intersection(new int[]{4,9,5},new int[]{9,4,9,8,4});
    }
}
