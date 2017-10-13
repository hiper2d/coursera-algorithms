import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int last;
    private int size;
    private int capcity = 1;

    public RandomizedQueue() {
        queue = (Item[]) new Object[capcity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot insert null");
        }
        resize();
        queue[last++] = item;
        size++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        resize();
        int randomIndex = StdRandom.uniform(last);
        Item itemToReturn = queue[randomIndex];
        queue[randomIndex] = queue[last - 1];
        queue[--last] = null;
        size--;
        return itemToReturn;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        int randomIndex = StdRandom.uniform(last);
        return queue[randomIndex];
    }

    public Iterator<Item> iterator() {
        return new LocalIterator();
    }

    private class LocalIterator implements Iterator<Item> {

        private final Item[] randomizedQueueCopy;
        private int currentIndex;

        LocalIterator() {
            randomizedQueueCopy = (Item[]) new Object[size];
            int[] randomizedIndexes = StdRandom.permutation(size);
            for (int i = 0; i < size; i++) {
                randomizedQueueCopy[i] = queue[randomizedIndexes[i]];
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return currentIndex <= size - 1;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("The Deque is empty");
            }
            return randomizedQueueCopy[currentIndex++];
        }
    }

    private void resize() {
        if (size == capcity) {
            capcity *= 2;
            copyArray();
        }
        if (capcity >= 4 && size <= capcity / 4) {
            capcity /= 2;
            copyArray();
        }
    }

    private void copyArray() {
        Item[] newArray = (Item[]) new Object[capcity];
        System.arraycopy(queue, 0, newArray, 0, size);
        queue = newArray;
    }
}