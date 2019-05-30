package calcStudy2.seven;

import calcStudy2.four.LinkedList;

/**
 * @Author: yanghaikun
 * @Date: 2019-05-22 16:46
 */
public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> list;

    public LinkedListSet() {
        list=new LinkedList<>();
    }

    @Override
    public void add(E e) {
        if(list.contains(e)){
            list.addFirst(e);
        }
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
