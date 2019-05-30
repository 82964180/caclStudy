package calcStudy2;

/**
 * @Author: yanghaikun
 * @Date: 2019-05-20 16:25
 */
public class Sum {
    public static int sum(int[] arr){
        return sum(arr,0);

    }
    //计算arr这个区间所有数字的和
    private static int sum(int[]arr,int l){
        if(l==arr.length){
            return 0;
        }
        return arr[l]+sum(arr,l+1);
    }

    public static void main(String[] args) {
        int[]nums={1,2,3,4,5,6,7,8};
        System.out.println(sum(nums));
    }
}
