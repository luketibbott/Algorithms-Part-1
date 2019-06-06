import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        private Item item;
        private Node next;
        private Node previous;

        Node(Item data) {
            item = data;
        }
    }

    private Node first;
    private Node last;
    private int size;

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return (first == null && last == null);
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null.");
        }

        Node newFirst = new Node(item);
        if (isEmpty()) {
            first = newFirst;
            last = newFirst;
        } else {
            newFirst.previous = null;
            newFirst.next = first;
            first.previous = newFirst;
            first = newFirst;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null.");
        }
        Node newLast = new Node(item);

        if (isEmpty()) {
            first = newLast;
            last = newLast;
        } else {
            newLast.next = null;
            newLast.previous = last;
            last.next = newLast;
            last = newLast;
        }
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("You are trying to remove first when Deque is empty.");
        }
        Item toBeRemoved = first.item;
        first = first.next;
        first.previous = null;
        size--;
        return toBeRemoved;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("You are trying to remove last when Deque is empty.");
        }
        Item toBeRemoved = last.item;
        last = last.previous;
        last.next = null;
        size--;
        return toBeRemoved;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return (current.next != null);
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove is not supported.");
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }

            Item item = current.item;
            current = current.next;
            return item;
        }

    }

    public static void main(String[] args) {
        Deque<Integer> deck = new Deque<Integer>();
        for (int i = 0; i < 50; i++) {
            deck.addFirst(i);
        }
        for (int i = 0; i < 50; i++) {
            System.out.println(deck.removeLast());
        }
    }
}
