package leetcode;

import java.util.Arrays;

/**
 * @author yinrongjie
 * @version 1.0
 * @date 2023/1/30
 * @description Partition Equal Subset Sum (01背包问题)
 */
public class Solution416 {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return true;
        }

        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) {
            // 如果总和是一个奇数，那么一定不能平均分为两个部分
            return false;
        }

        int n = nums.length;
        int v = sum / 2;
        // dp[i]表示是否能恰好装满
        boolean[] dp = new boolean[v + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int j = v; j >= num; --j) {
                dp[j] = dp[j] | dp[j - num];
            }
        }

        return dp[v];
    }
}
