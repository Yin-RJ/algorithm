# Remove K Digits(Leetcode 402)
![](https://yin-typora.oss-cn-beijing.aliyuncs.com/idea/2023-01-29-QBg57U.png)

## Solution
删除k个数要让整个数其变得尽可能小，那么最直观的想法就是从高位删起，高位的数越小，整个数也就会变得越小。

假设是abcd，删除一位数，从高位看起，考虑是否删去a，abcd删除一位之后是一个三位数，删除a之后b就是百位数，不删a，a就是百位数，在a小于b的情况下，a作为百位数肯定比b作为百位数更小，所以这种情况下不能删a，相反，如果a大于b
，那么就应该删除a。

## Code
```java
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

```