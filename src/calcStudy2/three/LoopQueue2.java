package calcStudy2.three;

/**
 * @Author: yanghaikun
 * @Date: 2019-05-19 19:05
 */
public class LoopQueue2<E> implements Queue<E> {
    private E[] data;
    private int front,tail;
    //private int size; size代表当前队列中元素个数  tail-front

    public int getSize(){
        if(tail>front){
            return (tail-front)+1;
        }else if(tail<front){
            return tail+getCapacity()-front;
        }else{
            return 0;
        }
    }
    public LoopQueue2(int capacity){
        data=(E[])new Object[capacity+1];
        front=0;
        tail=0;
    }
    public LoopQueue2(){
        this(10);
    }
    public int getCapacity(){
        return data.length-1;
    }

    @Override
    public void enqueue(E e) {
       if((tail+1)%data.length==front){
           resize(getCapacity()*2);
       }
       data[tail]=e;
       tail=(tail+1)%data.length;
    }

    private void resize(int newCapacity) {
        E[] newData= (E[]) new Object[newCapacity+1];
        for (int i=0;i<getSize();i++){
            newData[i]=data[(i+front)%data.length];
        }
        data=newData;
        front=0;
        tail=getSize();
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");
        }
        E ret=data[front];
        data[front]=null;
        front=(front+1)%data.length;
        if(getSize()==getCapacity()/4&&getCapacity()/2!=0){
            resize(getCapacity()/2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("Queue is empty");
        }
        return data[front];
    }

    /*@Override
    public int getSize() {
        return size;
    }*/

    @Override
    public boolean isEmpty() {
        return front==tail;
    }

    @Override
    public String toString(){
        StringBuilder res=new StringBuilder();
        res.append(String.format("Queue:size=%d,capacity=%d\n",getSize(),getCapacity()));
        //res.append("Queue: ");
        res.append("front [");
        for (int i=front;i!=tail;i=(i+1)%data.length){
            res.append(data[i]);
            if((i+1)%data.length!=tail){
                res.append(",");
            }
        }
        res.append("] tail");
        return res.toString();
    }
}
