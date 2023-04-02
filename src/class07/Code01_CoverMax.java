package class07;

import utils.Line;

import java.util.Arrays;
import java.util.PriorityQueue;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Comparator.comparingInt;
import static utils.Line.StartComparator;
import static utils.Line.generateLines;

public class Code01_CoverMax {
    public static void main(String[] args) {
        Line l1 = new Line(4, 9);
        Line l2 = new Line(1, 4);
        Line l3 = new Line(7, 15);
        Line l4 = new Line(2, 4);
        Line l5 = new Line(4, 6);
        Line l6 = new Line(3, 7);

        // 底层堆结构，heap
        PriorityQueue<Line> heap = new PriorityQueue<>(new StartComparator());
        heap.add(l1);
        heap.add(l2);
        heap.add(l3);
        heap.add(l4);
        heap.add(l5);
        heap.add(l6);

        while (!heap.isEmpty()) {
            Line cur = heap.poll();
            System.out.println(cur.start + "," + cur.end);
        }


        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = maxCover1(lines);
            int ans2 = maxCover2(lines);
            int ans3 = maxCover3(lines);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }

    /**
     * 和maxCover2过程是一样的
     * 只是代码更短
     * 不使用类定义的写法
     *
     * @param m 线段集
     * @return
     */
    private static int maxCover3(int[][] m) {
        /*
         m是二维数组，可以认为m内部是一个一个的一维数组
         每一个一维数组就是一个对象，也就是线段
         如下的code，就是根据每一个线段的开始位置排序
         比如, m = { {5,7}, {1,4}, {2,6} } 跑完如下的code之后变成：{ {1,4}, {2,6}, {5,7} }
        */
        Arrays.sort(m, comparingInt(a -> a[0]));

        int max = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int[] line : m) {
            while (!heap.isEmpty() && heap.peek() <= line[0]) {
                heap.poll();

            }
            heap.add(line[1]);
            max = Math.max(max, heap.size());
        }
        return max;
    }

    /**
     * 1、将线段集按照线段头的大小进行排序
     * 2、将堆中<=cur.start的数据弹出
     * 3、将cur.end加到堆中
     * 4、计算当前的堆大小，取max
     *
     * @param m 线段集
     * @return int
     */
    private static int maxCover2(int[][] m) {
        int length = m.length;
        Line[] lines = new Line[length];
        for (int i = 0; i < length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);
        }
        Arrays.sort(lines, new StartComparator());

        // 小根堆，每一条线段的结尾数值，使用默认的
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (Line line : lines) {
            // lines[i] -> cur 在黑盒中，把<=cur.start 东西都弹出
            while (!heap.isEmpty() && heap.peek() <= line.start) {
                heap.poll();
            }
            heap.add(line.end);
            max = Math.max(max, heap.size());
        }
        return max;
    }

    private static int maxCover1(int[][] lines) {
        int min = MAX_VALUE;
        int max = MIN_VALUE;
        for (int[] line : lines) {
            min = min(min, line[0]);
            max = max(max, line[1]);
        }
        int cover = 0;
        for (double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            for (int[] line : lines) {
                if (line[0] < p && line[1] > p) {
                    cur++;
                }
            }
            cover = max(cover, cur);
        }
        return cover;
    }
}
