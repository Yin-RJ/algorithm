# Next Permutation (Leetcode 31)

![](https://yin-typora.oss-cn-beijing.aliyuncs.com/idea/2023-01-29-mMDilE.png)

## Solution
关键：尽可能保证高位不变，变低位

![](https://yin-typora.oss-cn-beijing.aliyuncs.com/idea/2023-01-29-5ZzciL.png)


基于由尽可能低位来变换的原则，我们可以从后往前遍历。如果遍历的是个非降序序列，那么表示这一段中的最大排列就是原序列，不可能再变大，所以我们第一步要找到第一个降序的位置。

找到这个位置之后，如果想让这个序列变得更大，那么我们只需要在这一位后面找到一个比它更大的数中的最小值，两者交换位置。

最后在这一位的后面的序列变成一个非升序序列即可。

## Code
```java
package leetcode;

/**
 * @author yinrongjie
 * @version 1.0
 * @date 2023/1/29
 * @description
 */
public class Solution31 {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return;
        }

        int n = nums.length - 2;

        while (n >= 0) {
            // 从后往前找，找到第一个非升序的数
            if (nums[n] < nums[n + 1]) {
                break;
            }
            --n;
        }

        if (n < 0) {
            revers(nums, 0, nums.length - 1);
        } else {
            int t = n + 1;
            while (t < nums.length && nums[n] < nums[t]) {
                ++t;
            }

            t -= 1;
            int temp = nums[n];
            nums[n] = nums[t];
            nums[t] = temp;

            revers(nums, n + 1, nums.length - 1);
        }
    }

    /**
     * 翻转数组从start到end之间的值
     * @param nums
     * @param start
     * @param end
     */
    private void revers(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

}

```