
package calcStudy2.seven;

import calcStudy2.six.BST;

/**
 * @Author: yanghaikun
 * @Date: 2019-05-22 15:15
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {
    private BST<E> bst;

    public BSTSet() {
        this.bst=new BST<>();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
    public static void main(String[] args) {
        BSTSet<Integer> bstSet=new BSTSet();
        bstSet.add(1);
        bstSet.add(1);
        bstSet.add(2);
        bstSet.remove(2);
        bstSet.bst.inOrder();
    }

}
