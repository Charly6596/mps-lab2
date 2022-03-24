package doubleEndedQueue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;

class DoubleLinkedListTest implements DoubleEndedQueueTest{
    DoubleLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new DoubleLinkedList<>();
    }

    @AfterEach
    void tearDown() {
        list = null;
    }

    @Override
    public DoubleEndedQueue<Integer> getQueue() {
        return list;
    }



    @Nested
    class GetAtTest {
        // GetAt tests

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3})
        void getAtEmptyListThrowsException(int pos) {
            assertThrows(RuntimeException.class, () -> getQueue().getAt(pos));
        }

        @ParameterizedTest
        @ValueSource(ints = {-1, -2, -3, -1000})
        void getAtNegativeIndexThrowsException(int pos) {
            assertThrows(IndexOutOfBoundsException.class, () -> getQueue().getAt(pos));
        }

        @ParameterizedTest
        @CsvSource({
                "0,0", // empty, size+1
                "0,10", // empty, size+10
                "1,1", // 1 item, size+1
                "2,100", // ...
                "30,35"
        })
        void getAtNonExistingIndexThrowsException(int initialSize, int pos) {
            append(initialSize);
            assertThrows(IndexOutOfBoundsException.class, () -> getQueue().getAt(pos));
        }

        @ParameterizedTest
        @CsvSource({
                "0, 1, 0",
        })
        void getAtExistingPositionReturnsCorrectElement(int rightSize, Integer value, int pos) {
            var queue = getQueue();
            append(pos - 1);
            queue.append(value);
            var actual = queue.getAt(pos).getItem();
            assertEquals(value, actual, "when does not have items at the right");

            append(rightSize);
            assertEquals(value, actual, "when have items at the right");
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2})
        void getItemAtFirstIndexEqualsToPeekFirst(int initialSize) {
            append(initialSize);
            var queue = getQueue();
            Integer item = 100;
            queue.appendLeft(item);
            assertSame(queue.getAt(0).getItem(), getQueue().peekFirst());
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2})
        void getItemAtLastIndexEqualsToPeekLast(int initialSize) {
            append(initialSize);
            var queue = getQueue();
            Integer item = 100;
            queue.append(item);
            assertSame(getQueue().peekLast(), queue.getAt(initialSize).getItem());
        }
    }
}