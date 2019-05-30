package calcStudy2.nine;

/**
 * @Author: yanghaikun
 * @Date: 2019-05-24 11:25
 */
public class SegmentTree<E> {
    private E[] tree;
    private E[] data;
    private Merger<E> merger;
    public SegmentTree(E[] arr,Merger<E> merger){
        //创建一个与构造参数数组大小相同的成员变量数组
        //将构造参数数组中的值传入成员变量数组
        //对tree数组赋值 大小为4倍的arr.length

        this.merger=merger;//用来表示两个区间如何融合
        data=(E[])new Object[arr.length];
        for (int i=0;i<arr.length;i++){
            data[i]=arr[i];
        }
        tree= (E[]) new Object[4*arr.length];
        buildSegmentTree(0,0,data.length-1);
    }

    private void buildSegmentTree(int treeIndex,int l,int r) {
        //应考虑使用递归创建线段树，需要当前索引，以及区间范围
        //递归考虑结束条件为递归到底
        //如果没有递归到底，即当前索引一定有左右孩子,创建线段树的左右子树以及左右子树的区级范围
        //上一级为下一级节点之和
        if(l == r){
            tree[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex=leftChild(treeIndex);
        int rightTreeIndex=rightChild(treeIndex);

        int mid = (l + r) / 2;
        buildSegmentTree(leftTreeIndex,l,mid);
        buildSegmentTree(rightTreeIndex,mid+1,r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex] , tree[rightTreeIndex]);

    }

    public void set(int index,E e){
        if(index < 0 || index >= data.length){
            throw new IllegalArgumentException("Index is illegal");
        }
        data[index]=e;
        set(0,0,data.length - 1,index,e);
    }

    private void set(int treeIndex, int l, int r, int index, E e) {
        //遍历所有的节点，找到符合的节点并更新
        if(l == r){
            tree[treeIndex] = e;
            return;
        }

        //计算出所需要的左中右节点
        int mid = l+(r + l) / 2;
        int leftTreeIndex=leftChild(treeIndex);
        int rightTreeIndex=rightChild(treeIndex);
        //根据index的索引，去对应区间，直到找到该值。
        if(index >= mid+1){
            set(rightTreeIndex,mid + 1,r,index,e);
        }else //index <= mid
        {
            set(leftTreeIndex,l,mid,index,e);
        }
        //对线段树的区间的值进行更新。
        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }


    public E query(int queryL,int queryR){
        //对传入的值进行有效区级查询,分别有有效值校验以及r必须大于l
        if(queryL<0||queryL>=data.length||
           queryL<0||queryR>=data.length||
           queryL>queryR){
            throw new IllegalArgumentException("Index is illegal.");
        }
        return query(0,0,data.length-1,queryL,queryR);
    }
    //在以treeID为根的线段树中 l-r 的范围里，搜索区级区级（qeryL...queryR）的值
    private E query(int treeIndex, int l, int r,int queryL,int queryR) {
         //结束条件为l和r的信息与用户想要查找的queryL和queryR重合
        if(l == queryL && r == queryR){
            return tree[treeIndex];
        }
        //获取以treeID为根的线段是的左右子树节点，以及中间数
        int mid = l + (r-l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex=rightChild(treeIndex);

        //如果搜索区间在左子树，右子树，或者都有应该怎么办
        if(queryL >= mid + 1){
            return query(rightTreeIndex,mid + 1,r,queryL,queryR);
        }else if(queryR<=mid){
            return query(leftTreeIndex,l,mid,queryL,queryR);
        }

        E leftResult = query(leftTreeIndex, l, mid, queryL, queryR);
        E rightResult=query(rightTreeIndex,mid + 1,r,mid+1,queryR);
       return merger.merge(leftResult,rightResult);
    }

    public E get(int index){
        //判断当前索引是否合法
        //返回索引所在位置的成员变量数组的值
        if(index < 0 || index >= data.length){
            throw new IllegalArgumentException("Index is illegal");
        }

        return data[index];
    }
    private int leftChild(int index){
        return 2*index+1;
    }
    private int rightChild(int index){
        return 2*index+2;
    }
    @Override
    public String toString(){
        StringBuilder res=new StringBuilder();
        res.append("[");
        for (int i=0;i<tree.length;i++){
            if(tree[i]!=null){
                res.append(tree[i]);
            }else {
                res.append("null");
            }
           /* if(i!=tree.length-1){

            }*/
        }
        return res.toString();
    }
    public static void main(String[] args) {
        Integer[] nums={-2,0,3,-5,2,-1};
        SegmentTree<Integer> segmentTree=new SegmentTree<>(nums, new Merger<Integer>() {
            @Override
            public Integer merge(Integer a, Integer b) {
                //求和返回a+b 或者max(a,b)
                return a + b;
            }
        });
        System.out.println(segmentTree.query(0,2));
    }
}
