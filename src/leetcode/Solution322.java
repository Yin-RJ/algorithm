package leetcode;

import java.util.Arrays;

/**
 * @author yinrongjie
 * @version 1.0
 * @date 2023/1/31
 * @description Coin Change
 */
public class Solution322 {
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return 0;
        }

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int coin : coins) {
            for (int j = coin; j <= amount; ++j) {
                if (dp[j - coin] == Integer.MAX_VALUE) {
                    continue;
                }
                dp[j] = Math.min(dp[j], dp[j - coin] + 1);
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

}
