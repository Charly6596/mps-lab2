package doubleEndedQueue;

import java.util.Comparator;

public class DoubleLinkedList<T> implements DoubleEndedQueue<T> {
    private DequeNode<T> first;
    private DequeNode<T> last;
    private int size;

    public DoubleLinkedList() {
        size = 0;
    }

    @Override
    public void append(T item) {
        if (size() == 0) {
            appendWhenEmpty(item);
        } else {
            DequeNode<T> node = new DequeNode<>(item, last, null);
            last.setPrevious(node);
            last = node;
        }

        size++;
    }

    @Override
    public void appendLeft(T item) {
        if (size() == 0) {
            appendWhenEmpty(item);
        } else {
            DequeNode<T> node = new DequeNode<>(item, null, first);
            first.setNext(node);
            first = node;
        }

        size++;
    }

    @Override
    public void deleteFirst() {
        if (size() == 0)
            throw new RuntimeException("Error at delete. Can't delete over empty queue");

        if (size() == 1) {
            deleteWhenOnlyOneNode();
        } else {
            first = first.getPrevious();
            first.setNext(null);
        }

        size--;
    }

    @Override
    public void deleteLast() {
        if (size() == 0)
            throw new RuntimeException("Error at delete. Can't delete over empty queue");

        if (size() == 1) {
            deleteWhenOnlyOneNode();
        } else {
            last = last.getNext();
            last.setPrevious(null);
        }

        size--;
    }

    @Override
    public T peekFirst() {
        if (size() == 0)
            throw new RuntimeException("Error at peek. Can't peek over empty queue");
        return first.getItem();
    }

    @Override
    public T peekLast() {
        if (size() == 0)
            throw new RuntimeException("Error at peek. Can't peek over empty queue");
        return last.getItem();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public DequeNode<T> getAt(int position) {
        return null;
    }

    @Override
    public DequeNode<T> find(T item) {
        return null;
    }

    @Override
    public void delete(DequeNode<T> node) {

    }

    @Override
    public void sort(Comparator<?> comparator) {

    }

    private void appendWhenEmpty(T item) {
        DequeNode<T> node;
        node = new DequeNode<>(item, null, null);
        first = node;
        last = node;
    }

    private void deleteWhenOnlyOneNode() {
        first = null;
        last = null;
    }
}
