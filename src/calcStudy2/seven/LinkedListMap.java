package calcStudy2.seven;

/**
 * @Author: yanghaikun
 * @Date: 2019-05-22 20:11
 */
public class LinkedListMap<K,V> implements Map<K,V> {
    private class Node{
        public K key;
        public V value;
        public Node next;

        public Node(K key,V value,Node next){
            this.key=key;
            this.value=value;
            this.next=next;
        }
        public Node(K key){
            this(key,null,null);
        }
        public Node(){
            this(null,null,null);
        }
        @Override
        public String toString(){
            return this.key+":"+this.value;
        }
    }
    private Node dummyHead;
    private int size;

    public LinkedListMap() {
        this.dummyHead = new Node();
        this.size = 0;
    }

    private Node getNode(K key){
        Node cur=dummyHead.next;
        while (cur!=null){
            if(cur.key.equals(key)){
                return cur;
            }
            cur=cur.next;
        }
        return null;
    }
    @Override
    public void add(K key, V value) {
        Node node=getNode(key);
        if(node==null){
            //添加在头部
            dummyHead.next=new Node(key,value,dummyHead.next);
            size++;
        }else{
            node.value=value;
        }
    }

    @Override
    public V remove(K key) {
        //查找元素，查找到则退出
        Node prev=dummyHead;
        while (prev.next!=null){
            if(prev.next.key.equals(key)){
                break;
            }
            prev=prev.next;
        }
        if(prev.next!=null){
            Node delNode=prev.next;
            prev.next=delNode.next;
            delNode.next=null;
            return delNode.value;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key)!=null;
    }

    @Override
    public V get(K key) {
        Node node=getNode(key);
        return node==null?null:node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node=getNode(key);
        if(node==null){
            throw new IllegalArgumentException(key+"doesn't exist!");
        }
        node.value=newValue;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
