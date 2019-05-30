package calcStudy2.ten;

import java.util.TreeMap;

/**
 * @Author: yanghaikun
 * @Date: 2019-05-26 15:58
 */
public class MapSum {
    private class Node{
        public int value;
        public TreeMap<Character, Node> next;
        public Node(int value){
            this.value=value;
            next=new TreeMap<>();
        }

        public Node(){
            this(0);
        }
    }
    private Node root;
    public MapSum(){
        root=new Node();
    }
    public void insert(String word,int val){
        Node cur=root;
        for (int i=0;i<word.length();i++){
            char c=word.charAt(i);
            //将字符添加到节点，如果节点中没有这个字符
            if(cur.next.get(c) == null){
                cur.next.put(c,new Node());
            }
            //将当前节点跳到下一个节点
            cur = cur.next.get(c);
        }

        cur.value=val;
    }
    public int sum(String prefix){
        //查找到特定的前缀，并把接下来的相加。
        Node cur=root;
        for (int i=0;i<prefix.length();i++){
            char c=prefix.charAt(i);
            if(cur.next.get(c) == null){
                return 0;
            }
            cur=cur.next.get(c);
        }
        return sum(cur);
    }
    private int sum(Node node){
        //对当前节点下的所有值进行添加
        int res=node.value;
        for (char c:node.next.keySet()){
            res += sum(node.next.get(c));
        }
        return res;
    }
}
