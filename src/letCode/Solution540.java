package letCode;


/**
 * 540. 有序数组中的单一元素
 */
public class Solution540 {
    public static int singleNonDuplicate(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res = res ^ num;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8}) = " + singleNonDuplicate(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8}));
    }
}

