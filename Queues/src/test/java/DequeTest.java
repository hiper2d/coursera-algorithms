import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class DequeTest {
    Deque<String> deque;

    @BeforeEach
    public void setup() {
        deque = new Deque<>();
    }

    @Test
    public void isEmpty() throws Exception {
        assertTrue(deque.isEmpty());
        deque.addFirst("Hey");
        assertFalse(deque.isEmpty());
    }

    @Test
    public void size() throws Exception {
        assertTrue(deque.size() == 0);
        deque.addLast("Hey");
        assertTrue(deque.size() == 1);
        deque.addFirst("One more");
        assertTrue(deque.size() == 2);
        deque.removeFirst();
        assertTrue(deque.size() == 1);
        deque.removeLast();
        assertTrue(deque.size() == 0);
    }

    @Test
    public void addFirst() throws Exception {
        deque.addFirst("Hey");
        deque.addFirst("One more");
        assertEquals("One more", deque.removeFirst());
        assertEquals("Hey", deque.removeFirst());
    }

    @Test
    public void addFirstNull() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> deque.addFirst(null));
    }

    @Test
    public void addLast() throws Exception {
        deque.addLast("Hey");
        deque.addLast("One more");
        assertEquals("One more", deque.removeLast());
        assertEquals("Hey", deque.removeLast());
    }

    @Test
    public void addLastNull() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> deque.addLast(null));
    }

    @Test
    public void removeFirstAndLastFromEmpty() throws Exception {
        assertThrows(NoSuchElementException.class, () -> deque.removeFirst());
        assertThrows(NoSuchElementException.class, () -> deque.removeLast());
    }

    @Test
    public void iteratorTest() {
        deque.addFirst("A");
        deque.addFirst("B");
        deque.addFirst("C");
        Iterator iterator = deque.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("C", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("B", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("A", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void removeOnIteratorShouldThrowException() {
        deque.addFirst("A");
        Iterator iterator = deque.iterator();
        assertThrows(UnsupportedOperationException.class, iterator::remove);
    }

    @Test
    public void randomOperations() {
        Deque<Integer> deque2 = new Deque<>();
        deque2.addFirst(1);
        deque2.addLast(2);
        deque2.addLast(3);
        deque2.addFirst(4);
        deque2.addFirst(5);
        deque2.addFirst(6);
        assertEquals(new Integer(3), deque2.removeLast());
        Iterator iterator = deque2.iterator();
        int counter = 0;
        while (iterator.hasNext()) {
            iterator.next();
            counter++;
        }
        assertEquals(5, counter);
    }

    @Test
    public void randomOperations2() {
        Deque<Integer> deque2 = new Deque<>();
        deque2.addFirst(1);
        deque2.removeFirst();
        deque2.addFirst(3);
        deque2.removeLast();
        Iterator iterator = deque2.iterator();
        int counter = 0;
        while (iterator.hasNext()) {
            iterator.next();
            counter++;
        }
        assertEquals(0, counter);
    }
}