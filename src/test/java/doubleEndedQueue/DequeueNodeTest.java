package doubleEndedQueue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DequeueNodeTest {

    DequeNode<Integer> node;

    @BeforeEach
    void setUp() {
        node = new DequeNode<>(3, null, null);
    }

    @AfterEach
    void tearDown() {
        node = null;
    }

    @Nested
    class Construction{

        @Nested
        class GetMethods{

            @ParameterizedTest
            @ValueSource(ints = {3})
            public void getItemCorrect(int n){
                Integer integer = Integer.valueOf(n);
                assertEquals(integer, node.getItem());
            }

            @ParameterizedTest
            @CsvSource({
                    "4, 5, 3"
            })
            public void getNextCorrect(int actual, int n, int p){
                //Habría que ir creando nodos anteriores y siguiente en bucle.
                //DequeNode<Integer> aux = new DequeNode<>(Integer.valueOf(actual),
                //                         new DequeNode<Integer>(n, null, null), new DequeNode<Integer>(arg3, null, null));
                //assertEquals(aux, node.getNext));
                addNextNodes();
                DequeNode<Integer> nxt = node.getNext();
                assertEquals(Integer.valueOf(actual), nxt.getItem());
                assertEquals(Integer.valueOf(n), nxt.getNext().getItem());
                //assertEquals(node.getItem(), next.getPrevious().getItem());
                assertEquals(Integer.valueOf(p), nxt.getPrevious().getItem());
            }

            @ParameterizedTest
            @CsvSource({
                    "2, 3, 1"
            })
            public void getPreviousCorrect(int actual, int n, int p){
                //Habría que ir creando nodos anteriores y siguiente en bucle.
                //DequeNode<Integer> aux = new DequeNode<>(Integer.valueOf(arg1),
                //                         new DequeNode<Integer>(n, null, null), new DequeNode<Integer>(p, null, null));
                //assertEquals(aux, node.getPrevious));
                addPreviousNodes();
                DequeNode<Integer> prv = node.getPrevious();
                assertEquals(Integer.valueOf(actual), prv.getItem());
                //assertEquals(node.getItem(), previous.getNext().getItem());
                assertEquals(Integer.valueOf(n), prv.getNext().getItem());
                assertEquals(Integer.valueOf(p), prv.getPrevious().getItem());
            }
        }

        @Nested
        class SetMethods{

            @ParameterizedTest
            @ValueSource(ints = {5, 3, -1})
            public void setItemCorrect(int n){
                node.setItem(n);
                assertEquals(n, node.getItem());
            }

            @ParameterizedTest
            @ValueSource(ints = {5, 3, -1})
            public void setNextCorrect(int n){
                DequeNode<Integer> next = new DequeNode<>(n, null, null);
                node.setNext(next);
                assertEquals(next, node.getNext());
            }

            @ParameterizedTest
            @ValueSource(ints = {5, 3, -1})
            public void setPreviousCorrect(int n){
                DequeNode<Integer> previous = new DequeNode<>(n, null, null);
                node.setPrevious(previous);
                assertEquals(previous, node.getPrevious());
            }
        }
    }

    @Test
    public void isFirstNodePreviousNull(){
        addPreviousNodes();
        DequeNode<Integer> aux = node;
        while(!aux.isFirstNode()){
            aux = aux.getPrevious();
        }
        assertNull(aux.getPrevious());
    }

    @Test
    public void isLastNodeNextNull(){
        addNextNodes();
        DequeNode<Integer> aux = node;
        while(!aux.isLastNode()){
            aux = aux.getNext();
        }
        assertNull(aux.getNext());
    }

    @Test
    public void isNotATerminalNodeCorrect(){
        addNextNodes();
        addPreviousNodes();
        assertTrue(node.isNotATerminalNode());
        //assertFalse(node.isNotATerminalNode());

        /*
        assertTrue(!node.isFirstNode() && !node.isLastNode());
        assertFalse(node.isLastNode() || node.isFirstNode());

        assertFalse(node.isFirstNode());
        assertFalse(node.isLastNode());
         */
    }

    private void addPreviousNodes(){
        DequeNode<Integer> previous, auxPrevious;
        auxPrevious = new DequeNode<>(1, null, null);
        previous = new DequeNode<>(2, node, auxPrevious);

        auxPrevious.setNext(previous);
        node.setPrevious(previous);
    }

    private void addNextNodes(){
        DequeNode<Integer> next, auxNext;
        auxNext = new DequeNode<>(5, null, null);
        next = new DequeNode<>(4, auxNext, node);

        auxNext.setPrevious(next);
        node.setNext(next);
    }
}