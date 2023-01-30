package leetcode;

import java.util.Arrays;

/**
 * @author yinrongjie
 * @version 1.0
 * @date 2023/1/30
 * @description Last Stone Weight II
 */
public class Solution1049 {
    public int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        int v = sum / 2;

        int[] dp = new int[v + 1];

        for (int stone : stones) {
            for (int j = v; j >= stone; --j) {
                dp[j] = Math.max(dp[j], dp[j - stone] + stone);
            }
        }

        return sum - 2 * dp[v];
    }
}
