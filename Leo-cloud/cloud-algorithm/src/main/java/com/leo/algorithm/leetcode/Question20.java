package com.leo.algorithm.leetcode;

import java.util.Stack;

/**
 * @Copyright zm
 * @FileName: Leo-cloud
 * @Author: lihao
 * @Date: 2020/10/17 1:40 下午
 * @Description:
 * @author: lihao
 */
public class Question20 {

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     * ([)]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/valid-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public boolean isValid(String s) {

        Stack<Character> stack=new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i <chars.length ; i++) {
            char c = chars[i];
            if(c=='(')stack.push(')');
            if(c=='{')stack.push('}');
            if(c=='[')stack.push(']');
            if(stack.isEmpty()||c!=stack.pop())return false;
        }
        return stack.isEmpty();

    }

    public static void main(String[] args) {
        Question20 question20=new Question20();
        System.out.println( question20.isValid("([)]"));
    }
}
