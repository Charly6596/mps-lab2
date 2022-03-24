package doubleEndedQueue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class DoubleLinkedListTest implements DoubleEndedQueueTest {
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
                "1, 10, 3",
                "5, 1, 8",
        })
        void getAtExistingPositionReturnsCorrectElement(int rightSize, Integer value, int pos) {
            var queue = getQueue();
            append(pos);
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

    @Nested
    class FindTests {
        @Test
        void shouldReturnNullWhenQueueIsEmpty() {
            var queue = getQueue();
            var actual = queue.find(100);
            assertNull(actual);
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 30})
        void shouldReturnNullWhenQueueDoesNotHaveItem(int initialSize) {
            var queue = getQueue();
            append(initialSize);
            var actual = queue.find(100);
            assertNull(actual);
        }

        @ParameterizedTest
        @CsvSource({
                "0,0",
                "0,10", // item at first pos
                "1,1", //item at the middle
                "2,10",
                "30,35"
        })
        void shouldReturnElementWhenFound(int sizeLeft, int sizeRight) {
            var queue = getQueue();
            var item = 100;
            append(sizeLeft);
            queue.append(item);
            append(sizeRight);
            var actual = queue.find(item);
            assertEquals(item, actual.getItem());
        }
    }

    @Nested
    class DeleteTests {
        @ParameterizedTest
        @ValueSource(ints = {0, 1, 10})
        void shouldThrowExceptionWhenItemNotInQueue(int initialSize) {
            var queue = getQueue();
            append(initialSize);
            var node = new DequeNode<>(1, null, null);
            assertThrows(RuntimeException.class, () -> queue.delete(node));
        }

        @Test
        void sizeShouldDecreaseWhenNodeDeleted() {
            var queue = getQueue();
            append(10);
            var expected = queue.size() - 1;
            var node = queue.getAt(2);
            queue.delete(node);
            assertEquals(expected, queue.size());
        }

        @Test
        void itemNotFoundWhenItIsDeleted() {
            var queue = getQueue();
            append(10);
            var node = queue.getAt(2);
            var item = node.getItem();
            queue.delete(node);
            assertNull(queue.find(item));
        }

        @Test
        void shouldThrowExceptionWhenNodeIsNull() {
            var queue = getQueue();
            assertThrows(RuntimeException.class, () -> queue.delete(null));
        }
    }
}