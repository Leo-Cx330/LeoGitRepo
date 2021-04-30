package com.leo.algorithm;

import com.alibaba.nacos.client.utils.JSONUtils;
import com.google.common.collect.Comparators;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Copyright zm
 * @FileName: Leo-cloud
 * @Author: lihao
 * @Date: 2020/6/5 9:57 AM
 * @Description:
 * @author: lihao
 */
public class Test {

    public static void main(String[] args) {
        test("12单元，2，A单元，1，b，3单元");

    }
    /**
     * {"12单元","2","A单元","1","b","3单元"}
     * 12单元，2，A单元，1，b，3单元；按照 1，2，b,3单元，12单元，A单元
     */
    public static void test(String str) {
        String[] split = str.split("，");
        List<String> list1 = Arrays.stream(split).filter(s -> s.contains("单元")).collect(Collectors.toList());
        List<String> list2 = Arrays.stream(split).filter(s -> !s.contains("单元")).sorted().collect(Collectors.toList());
        list1.sort((s1, s2) -> {
            String x = s1.substring(0, s1.indexOf("单元"));
            String x1 = s2.substring(0, s2.indexOf("单元"));
            if (isInteger(x) && isInteger(x1)) {
                Integer a = Integer.valueOf(x);
                Integer b = Integer.valueOf(x1);
                return a.compareTo(b);
            }
            return x.compareTo(x1);
        });
        list2.forEach(System.out::println);
        list1.forEach(System.out::println);

    }

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
