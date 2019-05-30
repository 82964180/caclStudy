package calcStudy2.three;

/**
 * @Author: yanghaikun
 * @Date: 2019-05-19 16:32
 */
public interface Stack<E> {
    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();


}
