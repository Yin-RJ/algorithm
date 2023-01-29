package leetcode;

import java.util.Arrays;

/**
 * @author yinrongjie
 * @version 1.0
 * @date 2023/1/29
 * @description
 */
public class Solution206 {
    public int[] singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        int count = Arrays.stream(nums).reduce(0, (a, b) -> a ^ b);

        // 找到异或结果中哪一位为1(防止溢出)
        int index = count == Integer.MIN_VALUE ? count : (count & -count);

        int[] res = new int[2];
        Arrays.fill(res, 0);

        for (int num : nums) {
            if ((num & index) == 0) {
                res[0] ^= num;
            } else {
                res[1] ^= num;
            }
        }

        return res;
    }
}
