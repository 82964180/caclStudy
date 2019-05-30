package calcStudy2.six;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author: yanghaikun
 * @Date: 2019-05-20 20:26
 */
public class BST<E extends Comparable<E>> {
    private class Node{
        public E e;
        public Node left,right;

        public Node(E e){
            this.e=e;
            left=null;
            right=null;
        }
    }
    private Node root;
    private int size;

    public BST(){
        root=null;
        size=0;
    }
    public int getSize(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public void add(E e){
        root=add(root,e);
    }
    //向以node为跟的二分搜索树种插入元素E,递归算法
    private Node add(Node node,E e){
        /*if(e.equals(node.e)){
            return;
        }else if(e.compareTo(node.e)<0&& node.left==null){
            node.left=new Node(e);
            size++;
            return;
        }else if(e.compareTo(node.e)>0&&node.right==null){
            node.right=new Node(e);
            size++;
            return;
        }*/
        if(node==null){
            size++;
            return new Node(e);
        }
        if(e.compareTo(node.e)<0){
            node.left=add(node.left,e);
        }else if(e.compareTo(node.e)>0){
            node.right=add(node.right,e);
        }
        return node;
    }
    //看二分搜索树种是否包含元素e
    public boolean contains(E e){
        return contains(root,e);
    }

    private boolean contains(Node node,E e){
        if(node==null){
            return false;
        }
        if(e.compareTo(node.e)==0){
            return true;
        }else if(e.compareTo(node.e)<0){
            return contains(node.left,e);
        }else{
            return contains(node.right,e);
        }
    }
    //二分搜索树的前序遍历
    public void preOrder(){
       preOrder(this.root);
    }

    private void preOrder(Node node){
        if(node==null){
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder(){
        inOrder(this.root);
    }

    private void preOrderNR(){
        Stack<Node> stack=new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node cur=stack.pop();
            System.out.println(cur.e);

            if(cur.right!=null){
                stack.push(cur.right);
            }
            if(cur.left!=null){
                stack.push(cur.left);
            }
        }

    }

    private void inOrder(Node node){
        if(node==null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    public static void main(String[] args) {
        //      5
        //   3      6
        // 2  4       8
        BST<Integer> bst=new BST<>();
        int[] nums={5,3,6,8,4,2};
        for (int num:
             nums) {
            bst.add(num);
        }
        bst.remove(3);
        //bst.preOrder();
        //System.out.println(bst.toString());
        //bst.inOrder();
        //bst.preOrderNR();
        bst.levelOrder();
      /*  Random random=new Random();
        int n=1000;
        for (int i=0;i<n;i++){
            bst.add(random.nextInt(10000));
        }
        ArrayList<Integer> nums=new ArrayList<>();
        while (!bst.isEmpty()){
            nums.add(bst.removeMin());
        }
        System.out.println(nums);
        for (int i=1;i<nums.size();i++){
            if(nums.get(i-1)>nums.get(i)){
                throw new IllegalArgumentException("ERROR");
            }
        }
        System.out.println("test completed");*/
    }

    //二分搜索树的层序遍历
    public void levelOrder(){
        Queue<Node> queue=new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            Node cur= queue.remove();
            System.out.println(cur.e);
            if(cur.left!=null){
                queue.add(cur.left);
            }
            if(cur.right!=null){
                queue.add(cur.right);
            }
        }

    }

    //寻找二分搜索树的最小元素
    public E minimum(){
        if(size==0){
            throw new IllegalArgumentException("BST is empty");
        }
       return minimum(root).e;
    }
    //返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node) {
        if(node.left==null){
            return node;
        }
        return minimum(node.left);
    }

    //寻找二分搜索树的最小元素
    public E maximum(){
        if(size==0){
            throw new IllegalArgumentException("BST is empty");
        }
        return minimum(root).e;
    }
    //返回以node为根的二分搜索树的最小值所在的节点
    private Node maximum(Node node) {
        if(node.right==null){
            return node;
        }
        return maximum(node.right);
    }
    public E removeMin(){
        E ret=minimum();
        root=removeMin(root);
        return ret;
    }
    //删除掉以node为根的二分搜索树中最小节点
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
    }

    public E removeMax(){
        E ret=minimum();
        root=removeMax(root);
        return ret;
    }
    //删除掉以node为根的二分搜索树中最小节点
    //返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node){
        //删掉当前节点
        if(node.right==null){
            Node rightNode=node.left;
            node.left=null;
            size--;
            return rightNode;
        }
        node.right=removeMin(node.right);
        return node;
    }

    //从二分搜索树中删除元素为e的节点
    public void remove(E e){
        root=remove(root,e);
    }
    //删除掉以node为根的二分搜索树中值为e的节点，递归算法
    //返回删除节点后新的二分搜索树的根
    private Node remove(Node node, E e) {
        if(node==null){
            return null;
        }
        //e<node.e 要删除的数小于当前数
        if(e.compareTo(node.e)<0){
            node.left=remove(node.left,e);
            return node;
        }else if(e.compareTo(node.e)>0){
            node.right=remove(node.right,e);
            return node;
        }else{
            //查找到需要删除的数
            //待删除节点左子树为空的情况，应该直接把删除节点右子树转接到该节点的位置
            if(node.left==null){
                Node rightNode=node.right;
                node.right=null;
                size--;
                return rightNode;
            }
            //待删除节点右子树为空的情况
            if(node.right==null){
                Node leftNode=node.left;
                node.left=null;
                size--;
                return leftNode;
            }

            //待删除节点左右子树均不为空的情况
            //找到比待删除节点大的最小节点，即待删除节点右子树的最小节点
            //用这个节点顶替待删除节点的位置
            Node successor=minimum(node.right);
            successor.right=removeMin(node.right);
            successor.left=node.left;
            node.left=node.right=null;
            return successor;
        }

    }

    @Override
    public String toString(){
        StringBuilder res=new StringBuilder();
        generateBSTString(root,0,res);
        return res.toString();
    }

    /**
     * 自下而上创建，--递增（depth）
     * @param node
     * @param depth
     * @param res
     */
    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if(node==null){
           res.append(generateDepthString(depth)+"null\n");
           return;
        }
        //访问节点在访问其子节点之前，称为前序遍历   前中后 中序遍历   前后中 后序遍历

        generateBSTString(node.left,depth+1,res);
        res.append(generateDepthString(depth)+node.e+"\n");
        generateBSTString(node.right,depth+1,res);

    }
    private String generateDepthString(int depth) {
        StringBuilder res=new StringBuilder();
        for (int i=depth;i>0;i--){
            res.append("--");
        }
        return res.toString();
    }
}
