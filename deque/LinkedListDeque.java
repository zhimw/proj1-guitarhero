package deque;

import jh61b.junit.In;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {

    /**
     * A Node contains item, a pointer to the previous Node and the next Node
     */
    public class Node {
        public T item;
        public Node prev;
        public Node next;

        public Node (T i, Node p, Node n) {
            this.item = i;
            this.prev = p;
            this.next = n;
        }

    }


    /**
     * The first item (if exists) is at sentinel.next
     * sentinel.prev points to the last item
     * The last item points to the sentinel node
     */
    private Node sentinel;
    private int size;

    /* Constructor: create an empty LinkedListDeque */
    public LinkedListDeque() {
        sentinel = new Node(null,null, null);
        size = 0;
    }

    @Override
    /* Adds an item of type T to the front of the deque. You can assume that item is never null. */
    public void addFirst(T item) {
        if (size == 0) {
            Node p = new Node(item, sentinel, sentinel);
            sentinel.next = p;
            sentinel.prev = p;
            size++;
            return;
        }
        Node p = sentinel.next;
        Node q = new Node(item, sentinel, p);
        p.prev = q;
        sentinel.next = q;
        size++;

    }

    @Override
    /* Adds an item of type T to the back of the deque. You can assume that item is never null. */
    public void addLast(T item) {
        if (size == 0) {
            addFirst(item);
            return;
        }
            Node p = sentinel.prev;
            Node q = new Node(item, p, sentinel);
            sentinel.prev = q;
            p.next = q;
            size++;

    }

    @Override
    /* Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    @Override
    /* Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line. */
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    @Override
    /* Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        Node p = sentinel.next;
        sentinel.next = p.next;
        sentinel.next.prev = sentinel;
        p.next = null;
        p.prev = null;
        size--;
        return p.item;
    }

    @Override
    /* Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        Node p = sentinel.prev;
        sentinel.prev = p.prev;
        sentinel.prev.next = sentinel;
        p.prev = null;
        p.next = null;
        size--;
        return p.item;
    }

    @Override
    /* Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. */
    public T get(int index) {
        if (this.isEmpty()) {
            return null;
        }
        else if (index >= 0 && index < size) {
            int count = 0;
            Node p = sentinel.next;
            while (count < index) {
                p = p.next;
                count++;
            }
            return p.item;
        }
        return null;

    }

    /** Using recursive for the get method. */
    public T getRecursive(int index) {
        if (this.isEmpty())
            return null;
        if (index > size - 1 || index < 0)
            return null;
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, Node n) {
        if (index == 0)
            return n.item;
        else
            return getRecursiveHelper(index - 1, n.next);
    }

    @Override
    /* Make the LinkedListDeque object iterable. */
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {

        private int position;
        public LinkedListDequeIterator() {
            position = 0;
        }

        @Override
        public boolean hasNext() {
            return position < size;
        }

        @Override
        public T next() {
            T returnItem = get(position);
            position++;
            return returnItem;
        }
    }

}
