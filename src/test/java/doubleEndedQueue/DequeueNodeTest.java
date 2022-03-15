package doubleEndedQueue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 2})
    public void getItemCorrect(int n){
        Integer integer = Integer.valueOf(n);
        assertEquals(integer, node.getItem());
    }

    @ParameterizedTest
    @CsvSource({
            "100, 50, 25"
    })
    public void getNextCorrect(int arg1, int arg2, int arg3){
        //Habría que ir creando nodos anteriores y siguiente en bucle.
        //DequeNode<Integer> aux = new DequeNode<>(Integer.valueOf(arg1),
        //                         new DequeNode<Integer>(arg2, null, null), new DequeNode<Integer>(arg3, null, null));
        //assertEquals(aux, node.getNext));
        new DequeNode<Integer>(arg2, null, null);
        DequeNode<Integer> next = node.getNext();
        assertEquals(Integer.valueOf(arg1), next.getItem());
        assertEquals(Integer.valueOf(arg2), next.getNext().getItem());
        //assertEquals(node.getItem(), next.getPrevious().getItem());
        assertEquals(Integer.valueOf(arg3), next.getPrevious().getItem());
    }

    @ParameterizedTest
    @CsvSource({
            "100, 50, 25"
    })
    public void getPreviousCorrect(int arg1, int arg2, int arg3){
        //Habría que ir creando nodos anteriores y siguiente en bucle.
        //DequeNode<Integer> aux = new DequeNode<>(Integer.valueOf(arg1),
        //                         new DequeNode<Integer>(arg2, null, null), new DequeNode<Integer>(arg3, null, null));
        //assertEquals(aux, node.getPrevious));
        new DequeNode<Integer>(arg2, null, null);
        DequeNode<Integer> previous = node.getPrevious();
        assertEquals(Integer.valueOf(arg1), previous.getItem());
        //assertEquals(node.getItem(), previous.getNext().getItem());
        assertEquals(Integer.valueOf(arg2), previous.getNext().getItem());
        assertEquals(Integer.valueOf(arg3), previous.getPrevious().getItem());
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

        /*
        assertTrue(!node.isFirstNode() && !node.isLastNode());
        assertFalse(node.isLastNode() || node.isFirstNode());

        assertFalse(node.isFirstNode());
        assertFalse(node.isLastNode());
         */
    }

    /** Cosas a cambiar:
     * El setUp no se si es suficiente, es decir, he puesto que no tiene nodo anterior ni siguiente.
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
     * pienso que ya está implementado en los tests de isFirstNodePreviousNull y isLastNodeNextNull.
     *
     * He puesto aquí mis dudas principales para que sepais mis dudas y en caso de que falte algo o algo esté mal,
     * lo cambieis.
     */
}