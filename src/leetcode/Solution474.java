package leetcode;

/**
 * @author yinrongjie
 * @version 1.0
 * @date 2023/1/30
 * @description Ones and Zeroes
 */
public class Solution474 {
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0) {
            return 0;
        }

        int[][] dp = new int[m + 1][n + 1];

        for (String str : strs) {
            int[] count = count(str);
            for (int i = m; i >= count[0]; --i) {
                for (int j = n; j >= count[1]; --j) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - count[0]][j - count[1]] + 1);
                }
            }
        }
        return dp[m][n];
    }

    private int[] count(String s) {
        int zeroCount = 0;
        int oneCount = 0;

        for (char c : s.toCharArray()) {
            if (c == '0') {
                zeroCount++;
            } else {
                oneCount++;
            }
        }

        return new int[]{zeroCount, oneCount};
    }
}
