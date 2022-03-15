package doubleEndedQueue;

/**
 * Class representing a node of a double-ended queue (deque). Each node has pointers to
 * the next and previous nodes.
 * The previous and next of the first and last node of the deque is null.
 *
 * @param <T>
 */
public class DequeueNode<T> {
    private T item ;
    private DequeueNode<T> next ;
    private DequeueNode<T> previous ;

    public T getItem() {
        return item;
    }

    public DequeueNode<T> getNext() {
        return next;
    }

    public DequeueNode<T> getPrevious() {
        return previous;
    }

    public DequeueNode(T item, DequeueNode<T> next, DequeueNode<T> previous) {
        this.item = item ;
        this.next = next ;
        this.previous = previous ;
    }

    public boolean isFirstNode() {
        return previous == null ;
    }

    public boolean isLastNode() {
        return next == null ;
    }

    public boolean isNotATerminalNode() {
        return (!isFirstNode() && !isLastNode()) ;
    }
}
