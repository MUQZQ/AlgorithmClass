package class02;

import java.util.Arrays;

/**
 * @description:
 * @author: QZQ
 * @date: 2022/8/15
 **/
public class Code01_EvenTimesOddTimes {

    // arr中，只有一种数，出现奇数次
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int j : arr) {
            eor ^= j;
        }
        System.out.println(eor);
    }

    // arr中，有两种数，出现奇数次
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int j : arr) eor ^= j;

        // a 和 b是两种数
        // eor != 0
        // eor最右侧的1，提取出来
        // eor :     00110010110111000
        // rightOne :00000000000001000
        int rightOne = eor & (-eor);

        int onlyOne = Arrays.stream(arr).filter(j -> (j & rightOne) != 0).reduce(0, (a, b) -> a ^ b);
        //  arr[1] =  111100011110000
        // rightOne=  000000000010000
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }

    public static int bit2counts(int N) {
        int count = 0;
        while (N != 0) {
            int rightOne = N & (~N + 1);
            count++;
            N ^= rightOne;
        }
        return count;
    }


    public static void main(String[] args) {
        int[] arr1 = {3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1};
        printOddTimesNum1(arr1);

        int[] arr2 = {4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2};
        printOddTimesNum2(arr2);
    }
}
