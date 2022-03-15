package doubleEndedQueue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

class DoubleLinkedListTest implements DoubleEndedQueueTest{
    DoubleLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new DoubleLinkedList<Integer>();
    }

    @AfterEach
    void tearDown() {
        list = null;
    }

    @Override
    public DoubleEndedQueue<Integer> getQueue() {
        return list;
    }
}