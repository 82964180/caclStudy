package calcStudy2.three;

/**
 * @Author: yanghaikun
 * @Date: 2019-05-19 18:47
 */
public interface Queue<E> {
    void enqueue(E e);
    E dequeue();
    E getFront();
    int getSize();
    boolean isEmpty();
}
