package calcStudy2.twelve;

import calcStudy2.seven.Map;

/**
 * @Author: yanghaikun
 * @Date: 2019-05-22 20:28
 */
public class AVLTree<K extends Comparable,V> implements Map<K,V> {

    private class Node{
        public K key;
        public V value;
        public Node left,right;
        public int height;
        public Node(K key,V value){
            this.key=key;
            this.value=value;
            left=null;
            right=null;
            height = 1; //一定是叶子节点，默认高度值为1
        }
    }
    private Node root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }
    //获取节点node的高度
    private int getHeight(Node node){
        if(node == null){
            return 0;
        }
        return node.height;
    }

    private Node rightRotate(Node y){
        Node x = y.left;
        Node T3 = x.right;

        //向右旋转过程
        x.right = y;
        y.left =T3;

        //更新height
        y.height = Math.max(getHeight(y.left),getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right)) + 1;
        return x;
    }
    private Node leftRotate(Node y){
        Node x = y.right;
        Node T2 = x.left;

        //向右旋转过程
        x.left = y;
        y.right = T2;

        //更新height ,对x，y的height重新赋值。
        y.height = Math.max(getHeight(y.left),getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right)) + 1;
        return x;
    }
    //计算节点node的平衡因子
    private int getBalanceFactor(Node node){
        if(node == null){
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }
    @Override
    public void add(K key, V value) {
        root=add(root,key,value);
    }
    //向二分搜索树中添加新的元素（key，value)）
    //新增高度添加
    private Node add(Node node, K key, V value) {
        if(node==null){
            size++;
            return new Node(key,value);
        }
        if(key.compareTo(node.key)<0){
            node.left=add(node.left,key,value);
        }else if(key.compareTo(node.key)>0){
            node.right=add(node.right,key,value);
        }else{
            node.value=value;
        }
        //更新height
        node.height = 1 + Math.max(getHeight(node.left),getHeight(node.right));
        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1){
            System.out.println("unbalanced:" + balanceFactor);
        }

        //平衡维护  左侧叶子多 需要右旋转 LL
        if(balanceFactor > 1 && getBalanceFactor(node.left) >= 0){
            return rightRotate(node);
        }
        //RR
        if(balanceFactor < -1 && getBalanceFactor(node.right) <= 0){
            return leftRotate(node);
        }
        //LR
        if(balanceFactor >1 && getBalanceFactor(node.left) < 0){
            node.left=leftRotate(node.left);
            return rightRotate(node);
        }
        //RL   右子树       &&         左子树
        if(balanceFactor < -1 && getBalanceFactor(node.right) > 0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }
    private Node getNode(Node node,K key){
        if(node == null){
            return null;
        }
        if(key.compareTo(node.key) == 0){
            return node;
        }else if(key.compareTo(node.key) < 0){
            return getNode(node.left,key);
        }else{
            return getNode(node.right,key);
        }
    }

    @Override
    public V remove(K key) {
        Node node=getNode(root,key);
        if(node!=null){
            root=remove(root,key);
            return node.value;
        }
        return null;
    }
    private Node remove(Node node, K key) {
        if(node==null){
            return null;
        }
        //保存要返回的node，找找的返回会破坏平衡性
        Node retNode;
        //e<node.e 要删除的数小于当前数
        if(key.compareTo(node.key)<0){
            node.left=remove(node.left,key);
            retNode=node;
        }else if(key.compareTo(node.key)>0){
            node.right=remove(node.right,key);
            retNode=node;
        }else{
            //查找到需要删除的数
            //待删除节点左子树为空的情况，应该直接把删除节点右子树转接到该节点的位置
            if(node.left==null){
                Node rightNode=node.right;
                node.right=null;
                size--;
                retNode = rightNode;
            }
            //待删除节点右子树为空的情况
           else if(node.right==null){
                Node leftNode=node.left;
                node.left=null;
                size--;
                retNode = leftNode;
            }
            else {
                //待删除节点左右子树均不为空的情况
                //找到比待删除节点大的最小节点，即待删除节点右子树的最小节点
                //用这个节点顶替待删除节点的位置
                Node successor = minimum(node.right);

                //在这部有可能右打破平衡
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;
            }
        }
        if(retNode == null){
            return null;
        }
        //对retNode进行判断，判断是否需要维护树的平衡
        //更新height
        retNode.height = 1 + Math.max(getHeight(retNode.left),getHeight(retNode.right));
        //计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);
        if(Math.abs(balanceFactor) > 1){
            System.out.println("unbalanced:" + balanceFactor);
        }

        //平衡维护  左侧叶子多 需要右旋转 LL
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) >= 1){
            return rightRotate(retNode);
        }
        //RR
        if(balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0){
            return leftRotate(retNode);
        }
        //LR
        if(balanceFactor >1 && getBalanceFactor(retNode.left) < 0){
            retNode.left=leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        //RL   右子树       &&         左子树
        if(balanceFactor < -1 && getBalanceFactor(retNode.right) > 0){
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;
    }

    /*//删除掉以node为根的二分搜索树中最小节点
    //返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node){
        //删掉当前节点
        if(node.left==null){
            //如果node.right也等于null
            Node rightNode=node.right;
            node.right=null;
            size--;
            return rightNode;
        }
        node.left=removeMin(node.left);
        return node;
    }*/
    //返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node) {
        if(node.left==null){
            return node;
        }
        return minimum(node.left);
    }
    @Override
    public boolean contains(K key) {
        return getNode(root,key) != null;
    }

    @Override
    public V get(K key) {
        Node node=getNode(root,key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root,key);
        if(node == null){
            throw new IllegalArgumentException(key+"doesn't exist!");
        }
        node.value=newValue;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size==0;
    }
}
