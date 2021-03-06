# Double Ended Queue

## Test Interface strategy

Since we have been given an interface to test, we have created a test class that is also an interface. This way the test is extensible to any class that implements the interface.

To use this strategy we have relied on the following StackOverflow thread:

[How to test different implementations for an interface in Junit5 without duplicating the code](https://stackoverflow.com/questions/55437810/how-to-test-different-implementations-for-an-interface-in-junit5-without-duplica)

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
  - Throws exception when node not in queue
  - Throws exception when node is null
  - Throws exception when queue is empty
  - Size decreases when we delete 1 item
  - find = null when node is deleted
- Peek. 
  - peekFirst when empty throws Exception.
  - peekLast when empty throws Exception.
  - peekFirst peeks left most node.
  - peekLast peeks right most node.
- Size 
  - append increase size by one.
  - appendLeft increase size by one.
  - deleteFirst decrease size by one.
  - deleteLast decrease size by one.
  - peekFirst doesn't change size.
  - peekLast doesn't change size.
  - sort doesn't change size.
- GetAt
  - on empty list throws exception
  - on non-existing index throws exception
  - on existing index returns the correct element
  - Get last index = peekLast
  - Get first index = peekFirst
- Find
  - Returns null when item not found
  - Returns null when list is empty
  - Returns element when found
- Sort 
  - sort over empty queue don't throw exception
  - frequency of elements are the same after sort
  - sort sorts correctly unordered list without repetition
  - sort sorts correctly unordered list with repetition
  - sort sorts correctly a list in reverse order without repetition
  - sort sorts correctly a list in reverse order with repetition
  - Doesnt change the order of a list already ordered without repetition
  - Doesnt change the order of a list already ordered with repetition

### Queue Node 
- Construction:
  - GetItemCorrect() -> Compare if getItem() returns the item of a node.
  - GetNextCorrect() -> Compare if getNext() returns the item of a node.
  - GetPreviousCorrect() -> Compare if getPrevious() returns the item of a node.
  - SetItemCorrect() -> Compare if setItem() sets the item of a node.
  - SetNextCorrect() -> Compare if setNext() sets the next node of a node.
  - SetPreviousCorrect() -> Compare if setNext() sets the previous node of a node.
- isFirstNodePreviousNull() -> Checks if isFirstNode() is true, then the previous node is null.
- isFirstNodePreviousNull() -> Checks if isLastNode() is true, then the next node is null.
- Get if a node is a terminal node:
- isNotATerminalNodeCorrect() -> Checks if isNotATerminalNode() is true, then:
  - Is terminal node if next is null.
  - Is terminal node if previous is null.
  - Is terminal node if both next and prev are null.

## Screenshots
Screenshots of the results

### Test passed
![Test Passed](./imagenes/testPassed.png)

### Coverage
![Coverage](./imagenes/coverage.png)
