package leetcodeplug.leetcode.editor.cn;

//实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xⁿ）。不得使用库函数，同时不需要考虑大数问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：x = 2.00000, n = 10
//输出：1024.00000
// 
//
// 示例 2： 
//
// 
//输入：x = 2.10000, n = 3
//输出：9.26100 
//
// 示例 3： 
//
// 
//输入：x = 2.00000, n = -2
//输出：0.25000
//解释：2⁻² = 1/2² = 1/4 = 0.25 
//
// 
//
// 提示： 
//
// 
// -100.0 < x < 100.0 
// -2³¹ <= n <= 2³¹-1 
// -10⁴ <= xⁿ <= 10⁴ 
// 
//
// 
//
// 注意：本题与主站 50 题相同：https://leetcode-cn.com/problems/powx-n/ 
//
// Related Topics 递归 数学 👍 376 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double myPow(double x, int n) {
        // x的n次方可以把n表示成二进制，二进制位上为1的时候需要乘
        boolean isMinus = n < 0;
        double res = 1;


        for (long i = Math.abs(Long.valueOf(n)); i > 0; i >>= 1) {
            if ((i & 1) == 1) {
                res *= x;
            }
            x *= x;
        }


        if (isMinus) {
            return 1 / res;
        }
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
