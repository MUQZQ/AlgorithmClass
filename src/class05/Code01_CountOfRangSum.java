package class05;


/**
 * @description: ：<a href="https://leetcode.com/problems/count-of-range-sum/">327. 区间和的个数</a>
 * @author: QZQ
 * @date: 2023/3/6
 **/
public class Code01_CountOfRangSum {
    public static void main(String[] args) {
        int[] nums = {-2, 5, -1};
        int lower = -2;
        int upper = 2;
        System.out.println(countRangeSum(nums, lower, upper));
    }

    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 前缀和数组
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        return process(sum, 0, sum.length - 1, lower, upper);
    }

    private static int process(long[] sum, int L, int R, int lower, int upper) {
        // baseCase当只有一个元素,前缀和在区间[lower,upper]
        if (L == R) {
            return sum[L] >= lower && sum[L] <= upper ? 1 : 0;
        }

        int mid = L + ((R - L) >> 1);
        return process(sum, L, mid, lower, upper) + process(sum, mid + 1, R, lower, upper) + merger(sum, L, mid, R, lower, upper);
    }

    private static int merger(long[] sum, int L, int M, int R, int lower, int upper) {
        int ans = 0;
        int windowL = L;
        int windowR = L;

        // [windowL,windowR)
        for (int i = M + 1; i <= R; i++) { // 有数组遍历，前缀和转换目标范围
            long min = sum[i] - upper;
            long max = sum[i] - lower;
            while (windowR <= M && sum[windowR] <= max) {
                windowR++;
            }
            while (windowL <= M && sum[windowL] < min) {
                windowL++;
            }
            ans += windowR - windowL; //
        }

        // 数据结构merge部分
        long[] help = new long[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = sum[p1] > sum[p2] ? sum[p2++] : sum[p1++];
        }
        while (p1 <= M) {
            help[i++] = sum[p1++];
        }
        while (p2 <= R) {
            help[i++] = sum[p2++];
        }
        //每次排完序，都要将原数组排序部分刷新
        System.arraycopy(help, 0, sum, L, help.length);
        return ans;
    }
}
