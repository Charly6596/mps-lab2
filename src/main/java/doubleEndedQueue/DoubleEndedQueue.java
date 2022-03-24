package doubleEndedQueue;

import java.util.Comparator;

public interface DoubleEndedQueue<T> {
    // Basic operations
    void append(T item);

    void appendLeft(T item);

    void deleteFirst();

    void deleteLast();

    T peekFirst();

    T peekLast();

    int size();
  
    // Complex operations
    DequeNode<T> getAt(int position);
    DequeNode<T> find (T item);
    void delete(DequeNode<T> node);
    void sort(Comparator<T> comparator);
}