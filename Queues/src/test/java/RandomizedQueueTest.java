import org.hamcrest.core.AnyOf;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class RandomizedQueueTest {
    RandomizedQueue<String> queue;

    @Before
    public void setup() {
        queue = new RandomizedQueue<>();
    }

    @Test
    public void isEmpty() throws Exception {
        assertTrue(queue.isEmpty());
        queue.enqueue("A");
        assertFalse(queue.isEmpty());
    }

    @Test
    public void size() throws Exception {
        assertEquals(0, queue.size());
        queue.enqueue("A");
        assertEquals(1, queue.size());
        queue.dequeue();
        assertEquals(0, queue.size());
    }

    @Test
    public void enqueue() throws Exception {
        queue.enqueue("A");
        queue.enqueue("B");
        assertThat(queue.dequeue(), AnyOf.anyOf(IsEqual.equalTo("A"), IsEqual.equalTo("B")));
        assertThat(queue.dequeue(), AnyOf.anyOf(IsEqual.equalTo("A"), IsEqual.equalTo("B")));
    }

    @Test
    public void sample() throws Exception {
        queue.enqueue("A");
        queue.enqueue("B");
        assertThat(queue.sample(), AnyOf.anyOf(IsEqual.equalTo("A"), IsEqual.equalTo("B")));
        assertThat(queue.sample(), AnyOf.anyOf(IsEqual.equalTo("A"), IsEqual.equalTo("B")));
        assertThat(queue.sample(), AnyOf.anyOf(IsEqual.equalTo("A"), IsEqual.equalTo("B")));
        assertFalse(queue.isEmpty());
    }

    @Test
    public void iterator() throws Exception {
    }

    @Test(expected = IllegalArgumentException.class)
    public void enqueueNull() {
        queue.enqueue(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void dequeueFromEmpty() {
        queue.dequeue();
    }

    @Test(expected = NoSuchElementException.class)
    public void sampleFromEmpty() {
        queue.sample();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void removeOnIteratorShouldThrowException() {
        queue.enqueue("A");
        Iterator iterator = queue.iterator();
        iterator.remove();
    }

    @Test
    public void testResize() {
        for (int i = 0; i < 100; i++) {
            queue.enqueue("A" + i);
        }
        assertEquals(100, queue.size());

        for (int i = 0; i < 100; i++) {
            queue.dequeue();
        }
        assertEquals(0, queue.size());

        for (int i = 0; i < 100; i++) {
            queue.enqueue("A" + i);
        }
        assertEquals(100, queue.size());

        for (int i = 0; i < 100; i++) {
            queue.dequeue();
        }
        assertEquals(0, queue.size());
    }

    @Test
    public void testIterator() {
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        Iterator iterator = queue.iterator();
        validateNextItem(iterator);
        validateNextItem(iterator);
        validateNextItem(iterator);
        assertFalse(iterator.hasNext());
    }

    private void validateNextItem(Iterator iterator) {
        assertTrue(iterator.hasNext());
        assertThat(
                iterator.next(),
                AnyOf.anyOf(
                        IsEqual.equalTo("A"),
                        IsEqual.equalTo("B"),
                        IsEqual.equalTo("C")
                )
        );
    }
}