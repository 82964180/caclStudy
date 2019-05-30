package calcStudy2.seven;

/**
 * @Author: yanghaikun
 * @Date: 2019-05-22 20:09
 */
public interface Map<K,V> {
    void add(K key,V value);
    V remove(K key);
    boolean contains(K key);
    V get(K key);
    void set(K key,V newValue);
    int getSize();
    boolean isEmpty();
}
