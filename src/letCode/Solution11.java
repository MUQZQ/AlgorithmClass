package letCode;

/**
 * 11. 盛最多水的容器
 */
public class Solution11 {
    public int maxArea(int[] height) {
        int max = 0;
        int l = 0;
        int r = height.length - 1;
        while (r > l) {
            int min = Math.min(height[l], height[r]);
            max = Math.max(max, (r - l) * min);
            if (height[r] > height[l]) { // 双指针，那边高对面向中间收
                l++;
            } else {
                r--;
            }
        }
        return max;
    }
}