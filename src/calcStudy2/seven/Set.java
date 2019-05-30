package calcStudy2.seven;

/**
 * @Author: yanghaikun
 * @Date: 2019-05-22 15:12
 */
public interface Set<E> {
    void add(E e);
    void remove(E e);
    boolean contains(E e);
    int getSize();
    boolean isEmpty();
}
