package doubleEndedQueue;

import auxiliaryClasses.IntegerComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

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
        Integer node = 100;
        getQueue().append(node);
        assertSame(node, getQueue().peekLast());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    @DisplayName("appendLeft item then peekFirst return this item")
    default void appendLeftTest(int initialSize){
        this.append(initialSize);
        Integer node = 100;
        getQueue().appendLeft(node);
        assertSame(node, getQueue().peekFirst());
    }

    // Delete test

    @Test
    @DisplayName("deleteFirst over empty queue throws Exception")
    default void deleteFirstWhenEmptyThrowsException(){
        assertThrows(RuntimeException.class, () -> getQueue().deleteFirst());
    }

    @Test
    @DisplayName("deleteLast over empty queue throws Exception")
    default void deleteLastWhenEmptyThrowsException(){
        assertThrows(RuntimeException.class, () -> getQueue().deleteLast());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName("deleteFirst deletes left most node")
    default void deleteFirstDeleteLeftMostNode(int initialSize){
        this.append(initialSize);
        Integer node = 100;
        getQueue().appendLeft(node);
        getQueue().deleteFirst();
        assertNotSame(node, getQueue().peekFirst());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName("deleteLast deletes right most node")
    default void deleteLastDeleteLeftMostNode(int initialSize){
        this.append(initialSize);
        Integer node = 100;
        getQueue().append(node);
        getQueue().deleteLast();
        assertNotSame(node, getQueue().peekLast());
    }

    // Peek test

    @Test
    @DisplayName("peekFirst when empty throws Exception")
    default void peekFirstWhenEmptyThrowsException(){
        assertThrows(RuntimeException.class, () -> getQueue().peekFirst());
    }

    @Test
    @DisplayName("peekLast when empty throws Exception")
    default void peekLastWhenEmptyThrowsException(){
        assertThrows(RuntimeException.class, () -> getQueue().peekLast());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    @DisplayName("peekFirst peeks left most node")
    default void peekFirstPeeksLeftMostItem(int initialSize){
        this.append(initialSize);
        Integer item = 100;
        getQueue().appendLeft(item);
        assertSame(item, getQueue().peekFirst());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    @DisplayName("peekLast peeks right most item")
    default void peekLastPeeksLeftMostItem(int initialSize){
        this.append(initialSize);
        Integer value = 100;
        getQueue().append(value);
        assertSame(value, getQueue().peekLast());
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

    // Sort
    
    @ParameterizedTest
    @MethodSource("unorderedSet")
    @DisplayName("sort sorts correctly unordered list without repetition")
    default void sortsCorrectly(List<Integer> list){
        sortAndAssertItsRight(list);
    }

    private void sortAndAssertItsRight(List<Integer> list) {
        addAll(list);

        IntegerComparator comparator = new IntegerComparator();
        getQueue().sort(comparator);

        for(int i = 0; i < list.size() - 1; i++){
            assertTrue(compareConsecutive(comparator, i) < 0);
        }
    }


    // Arguments method

    default Stream<Arguments> unorderedSet(){
        return Stream.of(
                Arguments.of(2, 1, 3, 5, 4),
                Arguments.of(100, 1, 98, 56, 9),
                Arguments.of(5, 7, 2, 9, 3, 4, 8)
        );
    }

    // Auxiliary methods

    default void append(int numberOfItems){
        for (int i = 0; i < numberOfItems; i++){
            getQueue().append(i);
        }
    }

    private void addAll(List<Integer> list) {
        for(Integer item : list){
            getQueue().append(item);
        }
    }

    default Integer getItemAt(int pos){
        return getQueue().getAt(pos).getItem();
    }

    default int compareConsecutive(Comparator<Integer> comparator, int pos){
        return comparator.compare(getItemAt(pos), getItemAt(pos + 1));
    }
}
