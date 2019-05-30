package calcStudy2.four;

/**
 * 如果只对链表头进行操作：O(1)
 * 只查链表头的元素：O(1)
 * @Author: yanghaikun
 * @Date: 2019-05-19 20:05
 */
public class LinkedList<E> {
    private class Node{
        public E e;
        public Node next;

        public Node(E e,Node next){
            this.e=e;
            this.next=next;
        }
        public Node(E e){
            this(e,null);
        }
        public Node(){
            this(null,null);
        }
        @Override
        public String toString(){
            return e.toString();
        }
    }
    private Node dummyHead;
    int size;
    public LinkedList(){
        dummyHead=new Node(null,null);
        size=0;
    }

    public int getSize(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }

    //在链表头添加新的元素e
    public void addFirst(E e){
        add(0,e);
    }

    //在链表的index(0-based)位置添加新的元素e
    //在链表中不是一个常用的操作
    public void add(int index,E e){
        if(index<0||index>size){
            throw new IllegalArgumentException("Add failed,Illegal index");
        }

        Node prev=dummyHead;
            for (int i=0;i<index;i++){
                prev=prev.next;
            }
            /*Node node=new Node(e);
            node.next=prev.next;
            prev.next=node;*/

            prev.next=new Node(e,prev.next);
            size++;
    }
    //在链表末尾添加新的元素e
    public void addLast(E e){
        add(size,e);
    }

    //在链表的第index
    public E get(int index){
        if(index<0||index>=size){
            throw new IllegalArgumentException("Get failed,Illegal index");
        }
        Node cur=dummyHead.next;
        for (int i=0;i<index;i++){
            cur=cur.next;
        }
        return cur.e;

    }
    //获取链表的第一个元素
    public E getFirst(){
        return get(0);
    }
    public E getLast(){
        return get(size-1);
    }
    public void set(int index,E e){
        if(index<0||index>=size){
            throw new IllegalArgumentException("Set failed,Illegal index");
        }
        Node cur=dummyHead.next;
        for (int i=0;i<index;i++){
            cur=cur.next;
        }
        cur.e=e;
    }
    //从链表中删除元素e
    public void removeElement(E e){
        //查找元素，查找到则退出
        Node prev=dummyHead;
        while (prev.next!=null){
            if(prev.next.e.equals(e)){
                break;
            }
            prev=prev.next;
        }
        if(prev.next!=null){
            Node delNode=prev.next;
            prev.next=delNode.next;
            delNode.next=null;
        }
    }
    //查找链表中是否有元素e
    public boolean contains(E e){
        Node cur=dummyHead.next;
        while (cur!=null){
            if(cur.e.equals(e)){
                return true;
            }
            cur=cur.next;
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder res=new StringBuilder();
        /*Node cur=dummyHead.next;
        while (cur!=null){
            res.append(cur+"->");
            cur=cur.next;
        }*/
        for (Node cur=dummyHead.next;cur!=null;cur=cur.next){
            res.append(cur+"->");
        }
        res.append("NULL");
        return res.toString();
    }

    //从链表中删除index位置的元素
    public E remove(int index){
        if(index<0||index>=size){
            throw new IllegalArgumentException("Set failed,Illegal index");
        }
        Node prev=dummyHead;
        for (int i=0;i<index;i++){
           prev= prev.next;
        }
        Node retNode=prev.next;
        prev.next=retNode.next;
        retNode.next=null;
        size--;
        return retNode.e;
    }
    public E removeFirst(){
        return remove(0);
    }
    public E removeLast(){
        return remove(size-1);
    }
}
