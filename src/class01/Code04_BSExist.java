package class01;

/**
 * @description: 二分查找是否存在
 * @author: QZQ
 * @date: 2022/8/14
 **/
public class Code04_BSExist {
    public static boolean exist(int[] sortedArray, int target) {
        if (sortedArray == null || sortedArray.length == 0) {
            return false;
        }

        int L = 0;
        int R = sortedArray.length - 1;
        int mid = 0;

        //L...R
        while (L < R) {// L..R 至少两个数的时候
            if (sortedArray[mid] == target) {
                return true;
            }
            if (target > sortedArray[mid]) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return sortedArray[R] == target;//只有一个数的时候
    }

    //for test
    public static boolean comparator(int[] sortedArrary, int target) {
        if (sortedArrary == null) {
            return false;
        }
        for (int x : sortedArrary) {
            if (x == target) {
                return true;
            }
        }
        return false;
    }

}
