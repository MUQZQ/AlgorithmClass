package utils;

import java.util.Comparator;

public class Line {
    public int start;
    public int end;

    public Line(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * 随机生成线段数组
     *
     * @param n 生成线段数量
     * @param l 左边界
     * @param r 右边界
     * @return int[][]
     */
    public static int[][] generateLines(int n, int l, int r) {
        int size = (int) (Math.random() * n) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = l + (int) (Math.random() * (r - l + 1));
            int b = l + (int) (Math.random() * (r - l + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    public static class StartComparator implements Comparator<Line> {
        /**
         * @param o1 the first object to be compared.
         * @param o2 the second object to be compared.
         * @return
         */
        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    public static class EndComparator implements Comparator<Line> {
        /**
         * @param o1 the first object to be compared.
         * @param o2 the second object to be compared.
         * @return
         */
        @Override
        public int compare(Line o1, Line o2) {
            return o1.end - o2.end;
        }
    }
}
