package leetcode;

import java.util.Arrays;

/**
 * @author yinrongjie
 * @version 1.0
 * @date 2023/1/28
 * @description
 */
public class Solution1979 {
    public int findGCD(int[] nums) {
        int min = Arrays.stream(nums).min().getAsInt();
        int max = Arrays.stream(nums).max().getAsInt();
        return gcd(min, max);
    }

    /**
     * 更相减损术
     * @param min
     * @param max
     * @return
     */
    private int gcd(int min, int max) {
        if (min > max) {
            return gcd(max, min);
        }
        if (min == max) {
            return min;
        }
        return gcd(max - min, min);
    }

    /**
     * 辗转相除
     * @param min
     * @param max
     * @return
     */
    private int gcd2(int min, int max) {
        if (min > max) {
            return gcd2(max, min);
        }
        if (max % min == 0) {
            return min;
        }
        return gcd2(max % min, min);
    }


    /**
     * 最优解
     * @param min
     * @param max
     * @return
     */
    private int gcd1(int min, int max) {
        if (min == max) {
            return min;
        }

        if (min > max) {
            return gcd1(max, min);
        }

        if ((min & 1) == 0 && (max & 1) == 0) {
            // 两者都是偶数
            return gcd1(min >> 1, max >> 1) << 1;
        } else if ((min & 1) == 0) {
            return gcd1(min >> 1, max);
        } else if ((max & 1) == 0) {
            return gcd1(min, max >> 1);
        } else {
            return gcd1(max - min, min);
        }
    }
}
