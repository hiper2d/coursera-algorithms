import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    public Deque() {
    }

    private class Node {
        Item item;
        Node next;
        Node previous;

        Node(Item item) {
            this.item = item;
        }
    }

    public boolean isEmpty() {
        return size == new Integer(0);
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null");
        }
        Node newFirst = new Node(item);
        if (isEmpty()) {
            first = newFirst;
            last = first;
        } else {
            newFirst.next = first;
            first.previous = newFirst;
            first = newFirst;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null");
        }
        Node newLast = new Node(item);
        if (isEmpty()) {
            first = newLast;
            last = first;
        } else {
            newLast.previous = last;
            last.next = newLast;
            last = newLast;
        }
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("The Deque is empty");
        }
        Item removedItem = first.item;
        first = first.next;
        if (first != null) {
            first.previous = null;
        } else {
            last = null;
        }
        size--;
        return removedItem;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("The Deque is empty");
        }
        Item removedItem = last.item;
        last = last.previous;
        if (last != null) {
            last.next = null;
        } else {
            first = null;
        }
        size--;
        return removedItem;
    }

    public Iterator<Item> iterator() {
        return getItemIterator();
    }

    private Iterator<Item> getItemIterator() {
        return new LocalIterator();
    }

    private class LocalIterator implements Iterator<Item> {
        Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) {
                throw new NoSuchElementException("The Deque is empty");
            }
            Item itemToReturn = current.item;
            current = current.next;
            return itemToReturn;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}