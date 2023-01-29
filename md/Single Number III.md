# Single Number III (Leetcode 260)

![](https://yin-typora.oss-cn-beijing.aliyuncs.com/idea/2023-01-29-J7sv12.png)

## Solution
分治+异或运算

所有数中只有两个出现了一次，剩下都出现了两次，所以对整个数组的数进行了异或运算之后，结果中至少有一位是1（如果没有则与题目不符）。

根据异或运算的特性可知，对题目要求的数组全部元素做异或运算的结果与对两个只出现一次的数做异或运算的结果相同，所以我们可以认为最后的异或运算结果是由两个只出现一次的数字做异或的结果。

假设两个数是A和B，异或运算后第i位是1，那么表示A和B的第i位一定不同，一个是0，一个是1，所以我们可以根据第i位是0或者是1将整个数组分成两个部分，这样A和B一定被分开了，再对两个部分分别做异或运算即可得到结果。

## Code
```java
package leetcode;

import java.util.Arrays;

/**
 * @author yinrongjie
 * @version 1.0
 * @date 2023/1/29
 * @description
 */
public class Solution206 {
    public int[] singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        int count = Arrays.stream(nums).reduce(0, (a, b) -> a ^ b);

        // 找到异或结果中哪一位为1(防止溢出)
        int index = count == Integer.MIN_VALUE ? count : (count & -count);

        int[] res = new int[2];
        Arrays.fill(res, 0);

        for (int num : nums) {
            if ((num & index) == 0) {
                res[0] ^= num;
            } else {
                res[1] ^= num;
            }
        }

        return res;
    }
}

```