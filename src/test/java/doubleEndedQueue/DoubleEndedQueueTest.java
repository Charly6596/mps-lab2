package doubleEndedQueue;

import auxiliaryClasses.IntegerComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
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

    @ParameterizedTest
    @MethodSource("sortSize")
    @DisplayName("sort doesn't change size")
    default void sortDoesntChangeSize(int[] list){
        addAll(list);
        assertEquals(list.length, getQueue().size());
    }

    // Sort

    @Test
    @DisplayName("sort over empty queue don't throw exception")
    default void sortOverEmptyDontThrowException(){
        getQueue().sort(new IntegerComparator());
    }

    @ParameterizedTest
    @MethodSource("unorderedList")
    @DisplayName("frequency of elements are the same after sort")
    default void sortKeepsElementsFrequency(int[] list){
        addAll(list);
        getQueue().sort(new IntegerComparator());
        assertTrue(isPermutation(list));
    }

    private void sortAndAssertItsRight(int[] list) {
        addAll(list);

        IntegerComparator comparator = new IntegerComparator();
        getQueue().sort(comparator);

        for(int i = 0; i < list.length - 1; i++){
            assertTrue(compareConsecutive(comparator, i) <= 0);
        }
    }

    @ParameterizedTest
    @MethodSource("unorderedSet")
    @DisplayName("sort sorts correctly unordered list without repetition")
    default void sortsCorrectlyUnorderedSet(int[] list){
        sortAndAssertItsRight(list);
    }
    
    @ParameterizedTest
    @MethodSource("unorderedList")
    @DisplayName("sort sorts correctly unordered list with repetition")
    default void sortsCorrectlyUnorderedList(int[] list){
        sortAndAssertItsRight(list);
    }

    @ParameterizedTest
    @MethodSource("reverseOrderSet")
    @DisplayName("sort sorts correctly a list in reverse order without repetition")
    default void sortsCorrectlyReverseOrderSet(int[] list){
        sortAndAssertItsRight(list);
    }

    @ParameterizedTest
    @MethodSource("reverseOrderList")
    @DisplayName("sort sorts correctly a list in reverse order with repetition")
    default void sortsCorrectlyReverseOrderList(int[] list){
        sortAndAssertItsRight(list);
    }

    @ParameterizedTest
    @MethodSource("orderedSet")
    @DisplayName("Doesnt change the order of a list already ordered without repetition")
    default void dontChangeTheOrderOfOrderedSet(int[] list){
        sortAndAssertItsRight(list);
    }

    @ParameterizedTest
    @MethodSource("orderedList")
    @DisplayName("Doesnt change the order of a list already ordered with repetition")
    default void dontChangeTheOrderOfOrderedList(int[] list){
        sortAndAssertItsRight(list);
    }

    // Arguments method

    static Stream<Arguments> sortSize(){
        return Stream.of(
                Arguments.of(new int[]{}),
                Arguments.of(new int[]{23}),
                Arguments.of(new int[]{2, 1, 1, 2})
        );
    }

    static Stream<Arguments> unorderedSet(){
        return Stream.of(
                Arguments.of(new int[]{2, 1}),
                Arguments.of(new int[]{2, 1, 3}),
                Arguments.of(new int[]{2, 1, 3, 5, 4}),
                Arguments.of(new int[]{100, 1, 98, 56, 9}),
                Arguments.of(new int[]{5, 7, 2, 9, 3, 4, 8})
        );
    }

    static Stream<Arguments> unorderedList(){
        return Stream.of(
                Arguments.of(new int[]{2, 1, 1, 2}),
                Arguments.of(new int[]{2, 1, 3, 2, 3}),
                Arguments.of(new int[]{2, 1, 3, 5, 4, 3, 3, 3}),
                Arguments.of(new int[]{100, 1, 98, 56, 9, 98, 9}),
                Arguments.of(new int[]{5, 7, 2, 9, 3, 4, 8, 2, 2, 2})
        );
    }

    static Stream<Arguments> reverseOrderSet(){
        return Stream.of(
                Arguments.of(new int[]{2, 1}),
                Arguments.of(new int[]{3, 2, 1}),
                Arguments.of(new int[]{545, 21, 4})
        );
    }

    static Stream<Arguments> reverseOrderList(){
        return Stream.of(
                Arguments.of(new int[]{2, 2, 1}),
                Arguments.of(new int[]{3, 3, 2, 2, 1, 1, 1, 1}),
                Arguments.of(new int[]{545, 21, 21, 21, 21, 4, 4, 4})
        );
    }

    static Stream<Arguments> orderedSet(){
        return Stream.of(
                Arguments.of(new int[]{1, 2}),
                Arguments.of(new int[]{1, 2, 3}),
                Arguments.of(new int[]{3, 100, 2349})
        );
    }

    static Stream<Arguments> orderedList(){
        return Stream.of(
                Arguments.of(new int[]{1, 2, 2, 2}),
                Arguments.of(new int[]{1, 1, 1, 2, 3, 3}),
                Arguments.of(new int[]{3, 3, 100, 100, 2349})
        );
    }

    // Auxiliary methods

    default void append(int numberOfItems){
        for (int i = 0; i < numberOfItems; i++){
            getQueue().append(i);
        }
    }

    private void addAll(int[] list) {
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

    default boolean isPermutation(int[] originalList){
        Boolean[] checklist = new Boolean[originalList.length];

        Arrays.fill(checklist, Boolean.FALSE);

        for (int queueIndex = 0; queueIndex < getQueue().size(); queueIndex++){
            int originalAndCheckIndex = 0;
            boolean found = false;
            while (originalAndCheckIndex < originalList.length && !found) {
                if(!checklist[originalAndCheckIndex]){
                    found = getItemAt(queueIndex).equals(originalList[originalAndCheckIndex]);
                    checklist[originalAndCheckIndex] = found;
                }
                originalAndCheckIndex++;
            }
        }

        return allTrue(checklist);
    }

    default boolean allTrue(Boolean[] list){
        boolean allTrue = true;
        int index = 0;

        while (allTrue && index < list.length){
            allTrue = list[index];
            index++;
        }

        return allTrue;
    }

}
