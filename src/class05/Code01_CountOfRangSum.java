package class05;


/**
 * @description: ：<a href="https://leetcode.com/problems/count-of-range-sum/">327. 区间和的个数</a>
 * @author: QZQ
 * @date: 2023/3/6
 **/
public class Code01_CountOfRangSum {
    public static int countRangSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long[] sum = new long[nums.length];
        sum[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        return process(sum, 0, sum.length - 1, lower, upper);
    }

    private static int process(long[] sum, int L, int R, int lower, int upper) {
        // 递归头，当只有一个元素是跳出
        if (L == R) {
            return sum[L] >= lower && sum[L] <= upper ? 1 : 0;
        }

        int mid = L + ((R - L) >> 1);
        return process(sum, L, mid, lower, upper) + process(sum, mid + 1, R, lower, upper) + merger(sum, L, mid, R, lower, upper);
    }

    private static int merger(long[] arr, int l, int mid, int r, int lower, int upper) {
        int ans = 0;
        int windowL = l;
        int windowR = r;

        // [windowL,windowR)
        for (int i = mid + 1; i <= r; i++) {
            long min = arr[i] - upper;
            long max = arr[i] - lower;
            while (windowR <= mid && arr[windowR] <= max) {
                windowR++;
            }
            while (windowR <= mid && arr[windowL] < min) {
                windowL++;
            }
            ans += windowR - windowL;
        }

        // 数据结构merge部分
        long[] help = new long[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            help[i] = arr[p1] > arr[p2] ? arr[p2++] : arr[p1++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        //每次排完序，都要将原数组排序部分刷新
        System.arraycopy(help, 0, arr, l, help.length);
        return ans;
    }
}
