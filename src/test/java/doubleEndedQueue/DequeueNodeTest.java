package doubleEndedQueue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
        DequeNode<Integer> next, previous, auxNext, auxPrevious;
        node = new DequeNode<>(3, null, null);
        auxNext = new DequeNode<>(5, null, null);
        auxPrevious = new DequeNode<>(1, null, null);
        next = new DequeNode<>(4, auxNext, node);
        previous = new DequeNode<>(2, node, auxPrevious);

        auxNext.setPrevious(next);
        auxPrevious.setNext(previous);
        node.setNext(next);
        node.setPrevious(previous);
    }

    @AfterEach
    void tearDown() {
        node = null;
    }

    @Test
    public void getItemCorrect(){
        Integer integer = Integer.valueOf(3);
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
        DequeNode<Integer> nxt = node.getNext();
        assertEquals(Integer.valueOf(actual), nxt.getItem());
        assertEquals(Integer.valueOf(n), nxt.getNext().getItem());
        //assertEquals(node.getItem(), next.getPrevious().getItem());
        assertEquals(Integer.valueOf(p), nxt.getPrevious().getItem());
    }

    static Stream<Arguments> nextCorrect(){
        return Stream.of(
                Arguments.of(4, 5, 3)
        );
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
        DequeNode<Integer> prv = node.getPrevious();
        assertEquals(Integer.valueOf(actual), prv.getItem());
        //assertEquals(node.getItem(), previous.getNext().getItem());
        assertEquals(Integer.valueOf(n), prv.getNext().getItem());
        assertEquals(Integer.valueOf(p), prv.getPrevious().getItem());
    }

    static Stream<Arguments> previousCorrect(){
        return Stream.of(
                Arguments.of(2, 3, 1)
        );
    }

    @Test
    public void isFirstNodePreviousNull(){
        DequeNode<Integer> aux = node;
        while(!aux.isFirstNode()){
            aux = aux.getPrevious();
        }
        assertNull(aux.getPrevious());
    }

    @Test
    public void isLastNodeNextNull(){
        DequeNode<Integer> aux = node;
        while(!aux.isLastNode()){
            aux = aux.getNext();
        }
        assertNull(aux.getNext());
    }

    @Test
    public void isNotATerminalNodeCorrect(){
        assertTrue(node.isNotATerminalNode());
        //assertFalse(node.isNotATerminalNode());

        /*
        assertTrue(!node.isFirstNode() && !node.isLastNode());
        assertFalse(node.isLastNode() || node.isFirstNode());

        assertFalse(node.isFirstNode());
        assertFalse(node.isLastNode());
         */
    }

    /** Cosas a tener en cuenta:
     * El setUp no se si es  demasiado específico, ya que he creado 5 nodos, teniendo un lodo local con dos anteriores y dos siguientes.
     *
     * El getNextCorrect y getPreviousCorrect no se si sería correcto, ya que tanto para el nodo siguiente del siguiente,
     * y para el nodo anterior del siguiente (es decir, el actual) se compara por el item, en este caso Integers,
     * cuando se debería compara el nodo entero. No lo hago porque habría que ir creando nodos anteriores y siguiente en bucle.
     *
     * En el isFirstNodePreviousNull y isLastNodeNextNull, se me hace raro estar comparando los nodos previous y next
     * respectivamente con null en el while, y al final poner ese nodo en el assertNull. Si ya sabemos que es null.
     *
     * En el caso de isNotATerminalNodeCorrect, encuentro varias maneras de hacerlo, aunque no se si realmente se
     * tiene que hacer de esa manera.
     *
     * Respecto a lo de probar que el previous del 1er nodo sea null y probar que el next del ultimo sea null,
     * pienso que ya está implementado de maera indirecta en los tests de isFirstNodePreviousNull y isLastNodeNextNull.
     *
     * He puesto aquí mis dudas principales para que sepais mis dudas y en caso de que falte algo o algo esté mal,
     * lo cambieis.
     */
}