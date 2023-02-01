package leetcodeplug.leetcode.editor.cn;

//给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。
//请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18
//。 
//
// 示例 1： 
//
// 输入: 2
//输出: 1
//解释: 2 = 1 + 1, 1 × 1 = 1 
//
// 示例 2: 
//
// 输入: 10
//输出: 36
//解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36 
//
// 提示： 
//
// 
// 2 <= n <= 58 
// 
//
// 注意：本题与主站 343 题相同：https://leetcode-cn.com/problems/integer-break/ 
//
// Related Topics 数学 动态规划 👍 541 👎 0


/**
 * 分成的数只有2和3，且2的个数最多为两个
 * n = a1 + a2 + ... + ak
 * 1. 所有的ai都会小于5，如果ai大于等于5，将ai可以拆分成3+(ai-3)，此时乘积是3*(ai-3)，在ai大于等于5的情况下，乘积3*(ai-3)一定是大于ai的，所以此时就可以继续拆分
 * 2. 拆分出的数一定没有1
 * 3. 如果拆出了4，那么可以分成2+2
 * 4. 如果拆出了3个2，则可以将3个2变成两个3，此时乘积会变大，所以2的个数最多为两个
 */
//leetcode submit region begin(Prohibit modification and deletion)
class SolutionOffer14 {
    public int cuttingRope(int n) {
        if (n <= 3) {
            return 1 * (n - 1);
        }

        int res = 1;
        while (n >= 5) {
            n -= 3;
            res *= 3;
        }

        // 循环过后就剩下2，3，4中的一个，所以只需要乘起来就好
        return res * n;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
