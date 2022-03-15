# Double Ended Queue

## Interface

These are the classes interfaces that will be tested.

### Double Ended Queue

Interface of Double Ended Queue.

```
public interface DoubleEndedQueue<T> {
  // Basic operations
  void append(DequeNode<T> node) ;
  void appendLeft(DequeNode<T> node) ;
  void deleteFirst() ;
  void deleteLast() ;
  DequeNode<T> peekFirst() ;
  DequeNode<T> peekLast() ;
  int size() ;

  // Complex operations
  // (Estas operaciones se incluirán más adelante para ser realizadas en la segunda
  // sesión de laboratorio de esta práctica.)
}
```

### Queue Node

Class representing a node of a double-ended queue (deque). 
Each node has pointers to the next and previous nodes. 
The previous and next of the first and last node of the queue is null.

```
public class QueueNode<T> {
  private T item ;
  private QueueNode<T> next ;
  private QueueNode<T> previous ;

  public T getItem() {
    return item;
  }

  public QueueNode<T> getNext() {
    return next;
  }

  public QueueNode<T> getPrevious() {
    return previous;
  }

  public DequeNode(T item, QueueNode<T> next, QueueNode<T> previous) {
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
```

## Test Cases

### Double Ended Queue

- Append.
  - append null then peekLast null.
  - appendLeft null then peekFirst null.
  - append item then peekLast return this item.
  - appendLeft item then peekFirst return this item.
- Delete.
  - deleteFirst over empty queue throws Exception.
  - deleteLast over empty queue throws Exception.
  - deleteFirst deletes left most node.
  - deleteLast deletes right most node.
- Peek. 
  - peekFirst when null throws Exception.
  - peekLast when null throws Exception.
  - peekFirst peeks left most node.
  - peekLast peeks right most node.
- Size 
  - append increase size by one.
  - appendLeft increase size by one.
  - deleteFirst decrease size by one.
  - deleteLast decrease size by one.
  - peekFirst doesn't change size.
  - peekLast doesn't change size.

### Queue Node 

- Construction.
  - Get the item at constructor.
  - Get the next at constructor.
  - Get the prev at constructor.
- Terminal node.
  - Is terminal node if next is null.
  - Is terminal node if prev is null.
  - Is terminal node if both next and prev are null.
