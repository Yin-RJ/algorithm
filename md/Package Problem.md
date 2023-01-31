# 背包问题

## 01背包问题

**问题描述：** 总共有 `N` 个物品，第 `i` 个物品的体积是 `v[i]`，价值是 `w[i]`。有一个体积是 `V` 的背包。在每个物品**只能使用一次**的前提下，在背包能装得下的前提下，物品的最大价值是多少。

**特点：** 每个物品只能用一次

![image-20230130212939390](https://yin-typora.oss-cn-beijing.aliyuncs.com/uPic/image-20230130212939390.png)

![image-20230130214516360](https://yin-typora.oss-cn-beijing.aliyuncs.com/uPic/image-20230130214516360.png)

**关于初始化：** 当题目要求必须刚好填满整个背包的时候，我们需要将`dp`数组初始化成$-\infty$，否则只需要全部初始化成`0`。

```java
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
```

在状态转移方程中，两种情况下，`f[i][j]`的取值在`i`层面只依赖`i-1`，`j`这一层也只依赖小于等于`j`的结果，所以可以将空间进行优化，优化成为一维数组。

```java
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
```

### 练习题

#### Leetcode 416

![image-20230130222204207](https://yin-typora.oss-cn-beijing.aliyuncs.com/uPic/image-20230130222204207.png)

假设给的数组`nums`的总和是`sum`，那么分成两个数组之后，每个数组的总和应该就是`sum/2`。所以题目可以变成：

有`n`个物品，`n`是`nums`的长度，每个物品的体积是`nums[i]`，每个物品的价值是`1`，每个物品只能选一次，有一个容量是`sum/2`的背包，看是否存在刚好将背包装满的方案。

```java
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
```

#### Leetcode 474

![image-20230130225402873](https://yin-typora.oss-cn-beijing.aliyuncs.com/uPic/image-20230130225402873.png)



题目可以转化成一个二维数组的01背包问题，一个维度是0的个数（可以看成体积），一个维度是1的个数（可以看成重量）。

```java
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
```

#### Leetcode 1049

![image-20230130231519615](https://yin-typora.oss-cn-beijing.aliyuncs.com/uPic/image-20230130231519615.png)

在石头粉碎的过程中，我们可以认为最后剩下的结果一定是一个由石头重量组成的，有正有负的代数表达式，即
$$
\sum_{i=0}^{n-1}k_i\cdot stones_i
$$
其中$k_i$一定是有正有负，即$k_i$的取值有两种情况，`1`或者`-1`。

我们记石头的总重量是`sum`，$k_i=-1$的石头的总重量是`neg`，$k_i=1$的石头的总重量是`count`，也就是`sum-neg`，所以就有
$$
\sum_{i=0}^{n-1}k_i\cdot stones_i =count - neg=(sum - neg) - neg = sum - 2\cdot neg
$$
我们为了让$\sum_{i=0}^{n-1}k_i\cdot stones_i$的结果尽可能的小，则只需要让`neg`尽可能的大，同时由于题目限制，我们可以知道$sum-2\cdot neg$一定是大于`0`的，所以`neg`需要在不超过`sum/2`的情况下尽可能大，此时问题就变成了一个01背包问题。

```java
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
```

## 完全背包问题

**特点：** 每个物品可以无限使用

### 练习题

#### Leetcode 322

![image-20230131195724636](https://yin-typora.oss-cn-beijing.aliyuncs.com/uPic/image-20230131195724636.png)

```java
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
```



## 多重背包问题

**特点：** 第 `i` 个物品只有 `s[i]` 个，即每个物品的数量是有限的，只能使用有限个数该物品

### 优化

## 分组背包问题

**特点：** 有 `t` 组物品，每一组中有若干个物品，每一组中只能选一种物品
