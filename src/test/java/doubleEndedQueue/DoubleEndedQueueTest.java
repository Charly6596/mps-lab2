package doubleEndedQueue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

interface DoubleEndedQueueTest{

    DoubleEndedQueue<Integer> getQueue();

    // Append Test

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    @DisplayName("append null then peekLast null")
    default void appendNullTest(int initialSize){
        this.append(initialSize);
        getQueue().append(null);
        assertNull(getQueue().peekLast());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    @DisplayName("appendLeft null then peekFirst null")
    default void appendLeftNullTest(int initialSize){
        this.append(initialSize);
        getQueue().appendLeft(null);
        assertNull(getQueue().peekFirst());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    @DisplayName("append item then peekLast return this item")
    default void appendTest(int initialSize){
        this.append(initialSize);
        Integer node = Integer.valueOf(100);
        getQueue().append(node);
        assertSame(node, getQueue().peekLast());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    @DisplayName("appendLeft item then peekFirst return this item")
    default void appendLeftTest(int initialSize){
        this.append(initialSize);
        Integer node = Integer.valueOf(100);
        getQueue().appendLeft(node);
        assertSame(node, getQueue().peekFirst());
    }

    // Delete test

    @Test
    @DisplayName("deleteFirst over empty queue throws Exception")
    default void deleteFirstWhenEmptyThrowsException(){
        assertThrows(Exception.class, () -> getQueue().deleteFirst());
    }

    @Test
    @DisplayName("deleteLast over empty queue throws Exception")
    default void deleteLastWhenEmptyThrowsException(){
        assertThrows(Exception.class, () -> getQueue().deleteFirst());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName("deleteFirst deletes left most node")
    default void deleteFirstDeleteLeftMostNode(int initialSize){
        this.append(initialSize);
        Integer node = Integer.valueOf(100);
        getQueue().appendLeft(node);
        getQueue().deleteFirst();
        assertNotSame(node, getQueue().peekFirst());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName("deleteLast deletes right most node")
    default void deleteLastDeleteLeftMostNode(int initialSize){
        this.append(initialSize);
        Integer node = Integer.valueOf(100);
        getQueue().append(node);
        getQueue().deleteLast();
        assertNotSame(node, getQueue().peekLast());
    }

    // Peek test

    @Test
    @DisplayName("peekFirst when null throws Exception")
    default void peekFirstWhenEmptyThrowsException(){
        assertThrows(Exception.class, () -> getQueue().peekFirst());
    }

    @Test
    @DisplayName("peekLast when null throws Exception")
    default void peekLastWhenEmptyThrowsException(){
        assertThrows(Exception.class, () -> getQueue().peekLast());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    @DisplayName("peekFirst peeks left most node")
    default void peekFirstPeeksLeftMostNode(int initialSize){
        this.append(initialSize);
        Integer node = Integer.valueOf(100);
        getQueue().appendLeft(node);
        assertSame(node, getQueue().peekFirst());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    @DisplayName("peekLast peeks right most node")
    default void peekLastPeeksLeftMostNode(int initialSize){
        this.append(initialSize);
        Integer node = Integer.valueOf(100);
        getQueue().append(node);
        assertSame(node, getQueue().peekLast());
    }

    // Size test

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    @DisplayName("append increase size by one")
    default void appendIncreaseSizeByOne(int initialSize){
        this.append(initialSize);
        getQueue().append(100);
        assertEquals(initialSize + 1, getQueue().size());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    @DisplayName("appendLeft increase size by one")
    default void appendLeftIncreaseSizeByOne(int initialSize){
        this.append(initialSize);
        getQueue().appendLeft(100);
        assertEquals(initialSize + 1, getQueue().size());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName("deleteFirst decrease size by one")
    default void deleteFirstDecreaseSizeByOne(int initialSize){
        this.append(initialSize);
        getQueue().deleteFirst();
        assertEquals(initialSize - 1, getQueue().size());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName("deleteLast decrease size by one")
    default void deleteLastDecreaseSizeByOne(int initialSize){
        this.append(initialSize);
        getQueue().deleteLast();
        assertEquals(initialSize - 1, getQueue().size());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName("peekFirst doesn't change size")
    default void peekFirstDoesntChangeSize(int initialSize){
        this.append(initialSize);
        getQueue().peekFirst();
        assertEquals(initialSize, getQueue().size());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName("peekLast doesn't change size")
    default void peekLastDoesntChangeSize(int initialSize){
        this.append(initialSize);
        getQueue().peekLast();
        assertEquals(initialSize, getQueue().size());
    }

    // Auxiliary methods

    default void append(int numberOfItems){
        for (int i = 0; i < numberOfItems; i++){
            getQueue().append(i);
        }
    }
}
