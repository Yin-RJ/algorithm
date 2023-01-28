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
