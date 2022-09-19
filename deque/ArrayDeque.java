package deque;

import java.util.Iterator;


public class ArrayDeque<T> implements Iterable<T>, Deque<T>{
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /** Constructor: creates an empty ArrayDeque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    /** Resize the list based on the given capacity and returns the resized list. */
    private void resize(int capacity) {
        T[] copy = (T[]) new Object[capacity];

        int startPos = (int) ((capacity - size) / 2);

        System.arraycopy(items, nextFirst + 1, copy, startPos, size);
        items = copy;
        nextFirst = startPos - 1;
        nextLast = startPos + size;

    }

    /** Checks if the array should be expanded. If so resize the array. */
    private void expand() {
        if (size * 1.5 + 2 > items.length && items.length >= 8) {
            int resizeLength = (int) (size * 2 + 2);
            resize(resizeLength);
        }
        if (nextFirst == 0 || nextLast == items.length - 1) {
            int resizeLength = items.length + 2;
            resize(resizeLength);
        }
    }

    @Override
    public void addFirst(T item) {
        this.expand();
        items[nextFirst] = item;
        nextFirst--;
        size++;

    }

    @Override
    public void addLast(T item) {
        this.expand();
        items[nextLast] = item;
        nextLast++;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    /** Checks if the array should be shrunk. If so resize the array. */
    private void shrink() {
        if (items.length >= 16 && size <= items.length * 0.25) {
            int resizeLength = (int) (size * 1.5 + 2);
            resize(resizeLength);
        }
        if (nextFirst == 0 || nextLast == items.length - 1) {
            int resizeLength = items.length + 2;
            resize(resizeLength);
        }
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty())
            return null;
        this.shrink();
        nextFirst ++;
        T removedItem = items[nextFirst];
        items[nextFirst] = null;
        size--;
        return removedItem;
    }

    @Override
    public T removeLast() {
        if (this.isEmpty())
            return null;
        this.shrink();
        nextLast--;
        T removedItem = items[nextLast];
        items[nextLast] = null;
        size--;
        return removedItem;
    }

    @Override
    /* Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. */
    public T get(int i) {
        if (this.isEmpty())
            return null;
        if (i >= size)
            return null;
        int whatIndex = Math.floorMod(nextFirst + 1 + i, items.length);
        return items[whatIndex];
    }

    @Override
    /* Make the ArrayDeque object and create the iterator that iterates the iterable. */
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {

        private int position;
        public ArrayDequeIterator() {
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
