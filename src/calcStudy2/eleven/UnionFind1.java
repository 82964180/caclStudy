package calcStudy2.eleven;

/**
 * @Author: yanghaikun
 * @Date: 2019-05-26 20:25
 */
public class UnionFind1 implements UF {
    private int [] id;
    public UnionFind1(int size){
        id=new int[size];
        //初始的时候，每一个节点都指向自己
        for (int i=0;i<id.length;i++){
            id[i]=i;
        }
    }
    @Override
    public int getSize() {
        return id.length;
    }

    //查找元素p所对应的集合编号
    private int find(int p){
        if(p<0&&p>=id.length){
            throw new IllegalArgumentException("p is out of bound");
        }
        return id[p];
    }
    //O(1)
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
    //O(n) 合并元素p和元素q所属的集合
    @Override
    public void unionElements(int p, int q) {
        int pID=find(p);
        int qID=find(q);

        if(pID == qID){
            return;
        }
        for (int i=0;i<id.length;i++){
            if(id[i] == pID){
                id[i] = qID;
            }
        }
    }
}
