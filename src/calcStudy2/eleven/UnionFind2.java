package calcStudy2.eleven;

/**
 * @Author: yanghaikun
 * @Date: 2019-05-26 20:56
 */
public class UnionFind2 implements UF {
    private int[] parent;

    public UnionFind2(int size) {
        this.parent = new int[size];
        for (int i = 0;i < size;i ++){
            parent[i]=i;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    //获取到根节点
    private int find(int p){
        if(p<0&&p>=parent.length){
            throw new IllegalArgumentException("p is out of bound");
        }
        //查找到对应指向自己的根节点
        while (p != parent[p]){
            p = parent[p];
        }
        return p;
    }
    //查看元素p和元素q是否所属一个集合
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    //合并元素（合并根节点）
    @Override
    public void unionElements(int p, int q) {
        int pRoot=find(p);
        int qRoot=find(q);
        if(pRoot == qRoot){
            return;
        }

        parent[pRoot] = qRoot;
    }
}
