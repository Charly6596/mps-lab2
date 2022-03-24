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
            var node = new DequeNode<>(item, null, last);
            last.setNext(node);
            last = node;
        }

        size++;
    }

    @Override
    public void appendLeft(T item) {
        if (size() == 0) {
            appendWhenEmpty(item);
        } else {
            var node = new DequeNode<>(item, first, null);
            first.setPrevious(node);
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
            first = first.getNext();
            first.setPrevious(null);
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
            last = last.getPrevious();
            last.setNext(null);
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
        if(position >= size() || position < 0) {
            throw new IndexOutOfBoundsException();
        }

        int i = 0;
        var last = first;
        var current = last.getNext();

        while(i < position && current != null) {
            i++;
            last = current;
            current = current.getNext();
        }

        return last;
    }

    @Override
    public DequeNode<T> find(T item) {
        return null;
    }

    @Override
    public void delete(DequeNode<T> node) {

    }

    @Override
    public void sort(Comparator<T> comparator) {
        for(int iter = 0; iter < size()-1; iter++){
            for(int position = 0; position <= size() - iter; position++){
                if(comparator.compare(getAt(position).getItem(),getAt(position + 1).getItem()) < 0) {
                    T aux = getAt(position).getItem();
                    getAt(position).setItem(getAt(position + 1).getItem());
                    getAt(position + 1).setItem(aux);
                }
            }
        }
    }

    private void appendWhenEmpty(T item) {
        var node = new DequeNode<>(item, null, null);
        first = node;
        last = node;
    }

    private void deleteWhenOnlyOneNode() {
        first = null;
        last = null;
    }
}
