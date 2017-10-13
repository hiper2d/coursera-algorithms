import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class DequeTest {
    Deque<String> deque;

    @Before
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

    @Test(expected = IllegalArgumentException.class)
    public void addFirstNull() throws Exception {
        deque.addFirst(null);
    }

    @Test
    public void addLast() throws Exception {
        deque.addLast("Hey");
        deque.addLast("One more");
        assertEquals("One more", deque.removeLast());
        assertEquals("Hey", deque.removeLast());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addLastNull() throws Exception {
        deque.addLast(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void removeFirstFromEmpty() throws Exception {
        deque.removeFirst();
        deque.removeLast();
    }

    @Test(expected = NoSuchElementException.class)
    public void removeLastFromEmpty() throws Exception {
        deque.removeLast();
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

    @Test(expected = UnsupportedOperationException.class)
    public void removeOnIteratorShouldThrowException() {
        deque.addFirst("A");
        Iterator iterator = deque.iterator();
        iterator.remove();
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