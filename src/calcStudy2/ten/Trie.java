package calcStudy2.ten;
import java.util.TreeMap;
/**
 * @Author: yanghaikun
 * @Date: 2019-05-25 21:10
 */
public class Trie {
    private class Node{
        public boolean isWord;
        public TreeMap<Character,Node> next;
        public Node(boolean isWord){
            this.isWord=isWord;
            next=new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }
    private Node root;
    private int size;
    public Trie(){
        root=new Node();
        size=0;
    }
    //获得Trie中存储的单词数量
    public int getSize(){
        return size;
    }
    //向Trie中添加一个新的单词word
    public void add(String word){
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

        if(!cur.isWord){
            cur.isWord = true;
            size++;
        }
    }

    //查询单词word是否在trie中
    public boolean contains(String word){
        //根据字符顺序，如果全部便利到，并且isWord为true，则返回包含该单词
        Node cur=root;
        for (int i = 0;i < word.length();i++){
            char c=word.charAt(i);
            if(cur.next.get(c) == null){
                return false;
            }
            //来到了这个字符所在的节点
            cur=cur.next.get(c);
        }
        //如果该节点isWord为true，则true
        return cur.isWord;
    }

    public boolean match(String word){
        return match(root,word,0);
    }
    //查询单词word是否在trie中
    private boolean match(Node node,String word,int index){
        //如果index与字符长度一致，且当前是一个单词，返回true；
        //对所传入的字符进行匹配，如果不为.，匹配成功后进入下一个节点，如果为.，递归遍历接下来的所有可能，匹配到返回true
        if(index == word.length()){
            return node.isWord;
        }
        char c= word.charAt(index);
        if(c != '.'){
            if(node.next.get(c) == null){
                return false;
            }
            return match(node.next.get(c),word,index+1);
        }else {
            for (char nextChar: node.next.keySet()){
                if(match(node.next.get(nextChar),word,index+1)){
                    return true;
                }
            }
            return false;
        }
    }
    //查找是否在trie中有单词以prefix为前缀
    public boolean isPrefix(String prefix){
        //查找有没有这个前缀节点就行
        Node cur=root;
        for (int i=0;i<prefix.length();i++){
            char c=prefix.charAt(i);
            if(cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

}
