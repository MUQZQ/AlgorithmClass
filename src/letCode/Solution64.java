package letCode;

/**
 * 64. 最小路径和
 */
public class Solution64 {

    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];

        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int j = 1; j < m; j++) {
            dp[j][0] = dp[j - 1][0] + grid[j][0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[j][i] = Math.min(dp[j - 1][i], dp[j][i - 1]) + grid[j][i];
            }
        }

        return dp[m - 1][n - 1];
    }

//    public static void main(String[] args) {
//        int[][] grid =new int[][]{};
//        minPathSum()
//    }
}