package leetcode;

import java.util.LinkedList;

/**
 * @author yinrongjie
 * @version 1.0
 * @date 2023/1/29
 * @description Remove K Digits
 */
public class Solution402 {
    public String removeKdigits(String num, int k) {
        if (num == null || num.length() == 0) {
            return num;
        }

        // 删去尽可能大的高位
        LinkedList<Character> stack = new LinkedList<>();

        for (char c : num.toCharArray()) {
            // 后一位比前一位小就代表可以替代前一位，此时获得更小的数
            while (!stack.isEmpty() && c < stack.getLast() && k > 0) {
                // 一直可以往前比较
                --k;
                stack.removeLast();
            }
            stack.add(c);
        }

        while (k > 0 && !stack.isEmpty()) {
            stack.removeLast();
            --k;
        }

        while (!stack.isEmpty() && stack.getFirst() == '0') {
            // 去除前面的0
            stack.removeFirst();
        }

        String res = "";
        while (!stack.isEmpty()) {
            res += stack.getFirst();
            stack.removeFirst();
        }

        return "".equals(res) ? "0" : res;
    }
}
