package leetcode;

/**
 * @author yinrongjie
 * @version 1.0
 * @date 2023/1/29
 * @description
 */
public class Solution31 {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return;
        }

        int n = nums.length - 2;

        while (n >= 0) {
            // 从后往前找，找到第一个非升序的数
            if (nums[n] < nums[n + 1]) {
                break;
            }
            --n;
        }

        if (n < 0) {
            revers(nums, 0, nums.length - 1);
        } else {
            int t = n + 1;
            while (t < nums.length && nums[n] < nums[t]) {
                ++t;
            }

            t -= 1;
            int temp = nums[n];
            nums[n] = nums[t];
            nums[t] = temp;

            revers(nums, n + 1, nums.length - 1);
        }
    }

    /**
     * 翻转数组从start到end之间的值
     * @param nums
     * @param start
     * @param end
     */
    private void revers(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

}
