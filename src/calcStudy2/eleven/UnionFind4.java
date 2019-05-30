package calcStudy2.eleven;

/**
 * @Author: yanghaikun
 * @Date: 2019-05-26 20:56
 */
public class UnionFind4 implements UF {
    private int[] parent;
    private int[] rank;
    public UnionFind4(int size) {
        this.parent = new int[size];
        rank=new int[size];
        for (int i = 0;i < size;i ++){
            parent[i]=i;
            rank[i]=1;
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
        //如果当前跟元素节点下子节点小于需要合并的元素下子节点 （小去大）
        if(rank[pRoot] < rank[qRoot]){
            //如果当前跟元素节点下子节点小于需要合并的元素下子节点 （小去大） 把
            //大元素节点值赋给小元素节点id  把大元素子节点数加上小元素子节点数
            parent[pRoot] = qRoot;
        }else if(rank[qRoot] < rank[pRoot]){
            parent[qRoot] = pRoot;
        }else {
            parent[qRoot] = pRoot;
            rank[pRoot] +=1;
        }

    }
}
