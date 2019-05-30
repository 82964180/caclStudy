package calcStudy2.two;

/**
 * @Author: yanghaikun
 * @Date: 2019-05-18 20:33
 */
public class Array<E> {
    private E[] data;
    //data有多少有效容量
    private int size;

    /**
     * 构造函数，传入数组的容量capacity构造Array
     * @param capacity
     */
    public Array(int capacity){
        data= (E[])new Object[capacity];
        size=0;
    }

    /**
     * 无参构造函数，默认数组的容量capacity=10
     */
    public Array(){
        this(10);
    }

    public Array(E[] arr){
        data= (E[]) new Object[arr.length];
        for (int i=0;i<arr.length;i++){
            data[i] = arr[i];
        }
        size=arr.length;
    }

    public int getSize(){
        return size;
    }
    public int getCapacity(){
        return data.length;
    }
    public boolean isEmpty(){
        return size==0;
    }

    public void addLast(E e){
        add(size,e);
    }
    public void addFirst(E e){
        add(0,e);
    }
    //在第index个位置插入新元素e
    public void add(int index,E e){
        if(index<0||index>size){
            throw new IllegalArgumentException("Require index in 0 to size");
        }
        if(size==data.length){
            resize(2*data.length);
        }
        for(int i=size-1;i>=index;i--){
            data[i+1]=data[i];
        }
        data[index]=e;
        size++;
    }
    public E get(int index){
        if(index<0||index>size){
            throw  new IllegalArgumentException("Get failed.Index is illegal.");
        }
        return data[index];
    }

    public void set(int index,E e){
        if(index<0||index>=size){
            throw  new IllegalArgumentException("Set failed.Index is illegal.");
        }
        data[index]=e;
    }

    public boolean contains(E e){
        for (int i=0;i<size;i++){
            if(data[i].equals(e)){
                return true;
            }
        }
        return false;
    }
    //查找数组中元素e所在的索引，如果不存在，返回-1
    public int find(E e){
        for (int i=0;i<size;i++){
            if(data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }
    //从数组中删除index位置的元素，返回删除的元素
    public E remove(int index){
        if(index<0||index>=size){
            throw  new IllegalArgumentException("Remove failed.Index is illegal.");
        }
        E ret=data[index];
        for (int i=index+1;i<size;i++){
            data[i-1]=data[i];
        }
        size--;
        data[size]=null;//loitering objects!=memory leak
        if(size==data.length/4&&data.length/2!=0){
            resize(data.length/2);
        }
        return ret;
    }
    public E removeFirst(){
        return remove(0);
    }
    public E removeLast(){
        return remove(size-1);
    }

    public void removeElement(E e){
        int index=find(e);
        if(index!=-1){
            remove(index);
        }
    }

    private void resize(int newCapacity) {
       E[] newData= (E[]) new Object[newCapacity];
       for(int i=0;i<size;i++){
           newData[i]=data[i];
       }
        data=newData;
    }

    public E getLast(){
        return get(size-1);
    }
    public E getFirst(){
        return get(0);
    }

    @Override
    public String toString(){
        StringBuilder res=new StringBuilder();
        res.append(String.format("Array:size=%d,capacity=%d\n",size,data.length));
        res.append('[');
        for (int i=0;i<size;i++){
            res.append(data[i]);
            if(i!=size-1){
                res.append(",");
            }
        }
        res.append(']');
        return res.toString();
    }

    public void swap(int i,int j){
        if(i < 0 || i >= size || j <0 || j>=size){
            throw new IllegalArgumentException("index is illegal.");
        }
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }
}