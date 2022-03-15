package doubleEndedQueue;

public class DoubleLinkedList<T> implements DoubleEndedQueue<T> {
    @Override
    public void append(DequeueNode<T> node) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void appendLeft(DequeueNode<T> node) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void deleteFirst() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void deleteLast() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public DequeueNode<T> peekFirst() {
        return null;
    }

    @Override
    public DequeueNode<T> peekLast() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
