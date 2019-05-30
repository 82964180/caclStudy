package calcStudy1.two;

import java.util.Random;

/**
 * @Author: yanghaikun
 * @Date: 2019-05-29 16:53
 */
public class SortTestHelper {

    public static Integer[] generateRandomArray(int n,int rangeL,int rangeR){
        Random random = new Random();
        Integer[] arr = new Integer[n];
        for (int i = 0;i<n;i++){
            arr[i] = random.nextInt(rangeR - rangeL + 1) + rangeL;
        }
        return arr;
    }

    public static void printArray(Integer[] a){
        for (int i = 0;i<a.length;i++){
            System.out.println(a[i]);
        }
    }


}
