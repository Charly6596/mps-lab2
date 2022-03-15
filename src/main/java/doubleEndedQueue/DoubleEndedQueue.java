package doubleEndedQueue;

public interface DoubleEndedQueue<T> {
    // Basic operations
    void append(T item) ;
    void appendLeft(T item) ;
    void deleteFirst() ;
    void deleteLast() ;
    T peekFirst() ;
    T peekLast() ;
    int size() ;
}