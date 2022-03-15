package doubleEndedQueue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleLinkedListTest {
    DoubleLinkedList<Integer> list;
    @BeforeEach
    void setUp() {
        list = new DoubleLinkedList<>();
    }

    @AfterEach
    void tearDown() {
        list = null;
    }
}
