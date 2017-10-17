import org.hamcrest.core.AnyOf;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class RandomizedQueueTest {
    RandomizedQueue<String> queue;

    @BeforeEach
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

    @Test
    public void enqueueNull() {
        assertThrows(IllegalArgumentException.class, () -> queue.enqueue(null) );
    }

    @Test
    public void dequeueFromEmpty() {
        assertThrows(NoSuchElementException.class, queue::dequeue );
    }

    @Test
    public void sampleFromEmpty() {
        assertThrows(NoSuchElementException.class, queue::sample );
    }

    @Test
    public void removeOnIteratorShouldThrowException() {
        queue.enqueue("A");
        Iterator iterator = queue.iterator();
        assertThrows(UnsupportedOperationException.class, iterator::remove);
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