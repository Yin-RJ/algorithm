package packageproblem;

/**
 * @author yinrongjie
 * @version 1.0
 * @date 2023/1/30
 * @description 01背包问题
 */
public class ZeroOnePackageProblem {

    /**
     * 普通版01背包问题解法
     * @param n 物品数量
     * @param c 背包容量
     * @param v n个物品的体积
     * @param w n个物品的价值
     * @return 在背包容量的限制下能装下的物品最大价值
     */
    public int maxValue(int n, int c, int[] v, int[] w) {
        int[][] dp = new int[n + 1][c + 1];

        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= c; ++j) {
                dp[i][j] = dp[i - 1][j];
                // 由于i从1开始遍历，所以在从v和w中取值的时候是i-1
                if (j >= v[i - 1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - v[i - 1]] + w[i - 1]);
                }
            }
        }

        return dp[n][c];
    }

    /**
     * 普通版01背包问题解法（一维数组版本）
     * @param n 物品数量
     * @param c 背包容量
     * @param v n个物品的体积
     * @param w n个物品的价值
     * @return 在背包容量的限制下能装下的物品最大价值
     */
    public int maxValuePlus(int n, int c, int[] v, int[] w) {
        int[] dp = new int[c + 1];

        for (int i = 1; i <= n; ++i) {
            for (int j = c; j >= v[i - 1]; --j) {
                dp[j] = Math.max(dp[j], dp[j - v[i - 1]] + w[i - 1]);
            }
        }

        return dp[c];
    }
}
