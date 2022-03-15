package doubleEndedQueue;

public interface DoubleEndedQueue<T> {
    // Basic operations
    void append(DequeueNode<T> node) ;
    void appendLeft(DequeueNode<T> node) ;
    void deleteFirst() ;
    void deleteLast() ;
    DequeueNode<T> peekFirst() ;
    DequeueNode<T> peekLast() ;
    int size() ;

    // Complex operations
    // (Estas operaciones se incluirán más adelante para ser realizadas en la segunda
    // sesión de laboratorio de esta práctica.)
}