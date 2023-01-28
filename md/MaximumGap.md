# Maximum Gap (Leetcode 164)
![](https://yin-typora.oss-cn-beijing.aliyuncs.com/idea/2023-01-28-3Zt6Y5.png)

## Solution
前提：数组中的所有数都是整数

考虑将数组（`n`个数的数组可以有`n-1`个差值区间）分成`n-1`段，每一段的长度是`x`，每一段中的数是一个左开右闭的区间。

如果要满足题目中要求的排序后相邻两个数的最大差值，则需要保证这个最大差值不会出现在被分割出来的每一段中，换言之就是最大差值一定是出现在两段之间（也就是后一段的最小值与前一段的最大值之间的差值）。

假设数组中的最大值是`max`，最小值是`min`，分成`n-1`段后，每一段的长度是`x`，由于是左开右闭区间，那么每个区间最多有`x-1`个数（0被忽略了），所以当`(x-1)(n-1)
`小于`max-min`的时候，最大差值就一定出现在两段之间，而不会出现在段中。所以`(x-1)(n-1)`一定小于等于`max-min-1`，只要计算出`x`即可。

## Coding
```java
package leetcode;

import java.util.Arrays;

/**
 * @author yinrongjie
 * @version 1.0
 * @date 2023/1/28
 * @description Maximum Gap
 */
public class Solution164 {

    static class Range {
        int max;
        int min;
        boolean used;

        public Range() {
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            used = false;
        }
    }

    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).min().getAsInt();

        if (max == min) {
            return 0;
        }

        int n = nums.length;
        // 分成n-1段
        Range[] ranges = new Range[n - 1];
        for (int i = 0; i < n - 1; ++i) {
            ranges[i] = new Range();
        }

        // 每个区间的长度x需要小于等于len
        int len = (max - min - 1) / (n - 1) + 1;
        for (int num : nums) {
            if (num == min) {
                // 左开右闭区间，所以不需要考虑最小值
                continue;
            }
            // num分到哪一段中
            int k = (num - min - 1) / len;
            ranges[k].used = true;
            ranges[k].max = Math.max(ranges[k].max, num);
            ranges[k].min = Math.min(ranges[k].min, num);
        }

        int res = 0;
        for (int i = 0, last = min; i < n - 1; ++i) {
            // last表示上一个区间的最大值
            if (ranges[i].used) {
                res = Math.max(res, ranges[i].min - last);
                last = ranges[i].max;
            }
        }
        return res;
    }

}

```