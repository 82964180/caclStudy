package calcStudy1.two;

import java.util.Random;

/**
 * @Author: yanghaikun
 * @Date: 2019-05-29 16:18
 */
public class SelectSort<T extends Comparable> {
    public  void selectionSort(T[] arr,int n){
        for (int i = 0;i < n;i ++){
            int minIndex = i;
            //查找出 i+1 到 n 的最小值并放到第一位
            for (int j = i + 1;j < n; j ++){
                if(arr[j] .compareTo(arr[minIndex])<0){
                    minIndex = j;
                }
            }
            swap(arr,i,minIndex);

        }

    }
    private  void swap(T[] arr, int i, int minIndex) {
        T temp = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex] = temp;
    }
    //插入排序
    public  void insertSort(T[] arr,int n){
        for (int i = 0;i < n;i ++){
            //寻找元素arr[i]合适的插入位置
            //优化每一次循环都要大量交换的问题
            T e=arr[i];
            int j;
            for ( j = i;j>0 && arr[j-1].compareTo(e) > 0;j--){
                arr[j] = arr[j-1];
            }
            arr[j] = e;
        }
    }

    //冒泡排序
    public  void bubbleSort(T[] arr,int n){
        for (int i = 0;i < n;i ++){

            for (int j = 0;j< n - i -1;j++){
                if(arr[j] .compareTo(arr[j+1])>0){
                     swap(arr,j,j+1);
                }
            }
        }
    }


    public void mergeSort(T[] arr,int n){
        mergeSort(arr,0,n-1);
    }

    //递归使用归并排序，对arr【l。。。r】的范围进行排序
    private void mergeSort(T[] arr, int l, int r) {
        //先处理递归到底的情况,即当前区间只剩下一个数的情况
        if(l >= r){
            return;
        }
        //当前区间的中间值。
        int mid = (l + r) / 2; //可能发生溢出
        //先将mergeSort归并到 0 0
        mergeSort(arr,l,mid);
        //再将其归并到 0 1
        mergeSort(arr,mid + 1,r);
        //归并已经排序好的两部分函数,只要这样就不需要排序
        if(arr[mid] .compareTo(arr[mid + 1]) > 0){
            mergeSort(arr,l,mid,r);
        }

    }


    //将arr[l...mid]和arr[mid+1...r]两部分进行归并
    private void mergeSort(T[] arr, int l, int mid, int r) {
       T[] aux = (T[])new Comparable[r - l + 1];
       // 0 - 2
       for (int i = l;i <= r;i ++){
           //这边又一个i - l的偏移量 aux需要从 0 开始
           aux[i - l] = arr[i];
       }

       //指定两个数组的开头
       int i = l,j = mid + 1;
       for (int k = l ; k <= r ; k ++){

           if(i > mid){
               //如果i已经遍历完全
               arr[k] = aux[j - l];
               j ++;
           }else if(j > r){
               //如果j已经遍历完全
               arr[k] = aux[i -l];
               i ++;
           } else if(aux[i - l] .compareTo(aux[j - l]) < 0){
               //aux需要从 0 开始
               arr[k] = aux[i - l];
               i ++;
           }else{
               arr[k] = aux[j - l];
               j ++;
           }
       }
    }

    public void mergeSortTwo(T[] arr,int n){
        //整个数组的大小
        for (int sz = 1;sz <= n;sz += sz){
            //对arr[i .... i+sz-1] 和 arr[i + sz ...i+2*sz-1] 进行归并
            //需要解决 i + sz -1 与 i + sz + sz -1 可能越界的问题
            for (int i = 0;i + sz < n;i += sz + sz){
                  mergeSort(arr,i + sz - 1,i + sz + sz -1< n - 1?i+sz+sz-1:n - 1);
            }
        }
    }
    public void quickSort(T[] arr,int n){
        quickSort(arr,0,n-1);
    }

    private void quickSort(T[] arr, int l, int r) {
        if(l >= r){
            return;
        }

        int p =partition(arr,l,r);
        quickSort(arr,l,p - 1);
        quickSort(arr,p+1,r);
    }

    //对arr[l...r]部分进行partition操作
    //返回p，使得arr[l..p - 1] < arr[p];arr[P +1 .. r] > arr[p]
    private int partition(T[] arr, int l, int r) {
        Random random=new Random();
        swap(arr,l,random.nextInt(r -l + 1) + l);
        T v = arr[l];
        int j = l;
        for (int i = l + 1;i <= r;i++){
            if(arr[i].compareTo(v) < 0){
                swap(arr,j+1,i);
                j ++;
            }

        }
        swap(arr,l,j);
        return j;
    }

    public void quickSort2(T[] arr,int n){
        quickSort2(arr,0,n-1);
    }

    private void quickSort2(T[] arr, int l, int r) {
        if(l >= r){
            return;
        }

        int p =partition2(arr,l,r);
        quickSort2(arr,l,p - 1);
        quickSort2(arr,p+1,r);
    }

    private int partition2(T[] arr, int l, int r) {
        Random random=new Random();
        swap(arr,l,random.nextInt(r -l + 1) + l);
        T v = arr[l];

        //arr[l+1...j] <=v; arr[j...r] >= v
        int i = l + 1,j = r;
        while (true){
            //i会停在第一个arr[i] > v 上，i从前往后遍历
            while (i <= r && arr[i].compareTo(v) < 0){ i++;}
            //j 会停在第一个arr[j] < v上，j从后往前遍历
            while (j >= l+1 && arr[j].compareTo(v) > 0){
                j -- ;
            }
            if(i > j){
                break;
            }
            swap(arr,i,j);
            i++;
            j--;
        }
        swap(arr,l,j);
        return j;
    }

    public void quickSort3(T[] arr,int n){
        quickSort3(arr,0,n-1);
    }

    private void quickSort3(T[] arr, int l, int r) {
        if(l >= r){
            return;
        }

        Random random=new Random();
        swap(arr,l,random.nextInt(r -l + 1) + l);
        T v = arr[l];
        int lt = l;//arr[l + 1 ... lt] <v;
        int gt =r + 1;//arr[gt .. r] >v
        int i = l + 1;//arr[lt+1...i] == v
        while (i < gt){
            if(arr[i].compareTo(v) < 0){
                swap(arr,i,lt + 1);
                lt ++;
                i ++;
            }else if(arr[i].compareTo(v) > 0){
                swap(arr,i,gt - 1);
                gt -- ;
            }else{
                i++;
            }
        }
        swap(arr,l,lt);
        quickSort3(arr,l,lt - 1);
        quickSort3(arr,gt,r);

    }

    public static void main(String[] args) {
        int n = 10000000;
        SelectSort<Integer> selectSort=new SelectSort<>();
        Integer[] a = SortTestHelper.generateRandomArray(n, 0, n);
        Integer[] cloneA = a.clone();
        Integer[] cloneA2 = a.clone();
        Integer[] cloneA3 = a.clone();
        Integer[] cloneA4 = a.clone();
        /*long startTime=System.currentTimeMillis();
        selectSort.selectionSort(a,n);
        System.out.println("selectSort = "+(System.currentTimeMillis() - startTime));
        startTime=System.currentTimeMillis();
        selectSort.insertSort(cloneA,n);
        System.out.println("insertSort = "+(System.currentTimeMillis() - startTime));

        startTime=System.currentTimeMillis();
        selectSort.bubbleSort(cloneA2,n);
        System.out.println("bubbleSort = "+(System.currentTimeMillis() - startTime));
        //SortTestHelper.printArray(cloneA2);
*/
        //mergeSort 是nlogn的算法
        long startTime=System.currentTimeMillis();
        selectSort.mergeSortTwo(cloneA,n);
        System.out.println("mergeSort = "+(System.currentTimeMillis() - startTime));

        //mergeSort 是nlogn的算法
        startTime=System.currentTimeMillis();
        selectSort.quickSort(cloneA2,n);
        System.out.println("quickSort = "+(System.currentTimeMillis() - startTime));

        //mergeSort 是nlogn的算法
        startTime=System.currentTimeMillis();
        selectSort.quickSort2(cloneA3,n);
        System.out.println("quickSort2 = "+(System.currentTimeMillis() - startTime));

        startTime=System.currentTimeMillis();
        selectSort.quickSort3(cloneA4,n);
        System.out.println("quickSort3 = "+(System.currentTimeMillis() - startTime));
        //SortTestHelper.printArray(cloneA4);


    }
}
