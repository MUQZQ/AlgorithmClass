package class03;

/**
 * @description: 求arr中的最大值
 * @author: QZQ
 * @date: 2022/10/7
 **/
public class Code08_GetMax {
    public static void main(String[] args) {
        int[] arr = {12, 4, 45, 23, 55, 89};
        System.out.print("最大值为：" + getMax(arr));
    }

    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    /**
     * arr[L..R]范围上求最大值  L ... R   N
     *
     * @param arr 数组
     * @param L   左边界
     * @param R   右边界
     * @return
     */
    private static int process(int[] arr, int L, int R) {
        // arr[L..R]范围上只有一个数，直接返回，base case
        if (L == R) return arr[L];

        // L...R 不只一个数
        int mid = L + ((R - L) >> 1);
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }
}
