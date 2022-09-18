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
        nextFirst = 5;
        nextLast = 5;
    }

    /** Resize the list based on the given capacity and returns the resized list. */
    private void resize(int capacity) {
        T[] copy = (T[]) new Object[capacity];

        int firstPartSize;

        if (nextFirst < nextLast) {
            firstPartSize = size;
        } else {
            firstPartSize = items.length - nextFirst - 1;
        }

        int firstPartStartIndex = Math.floorMod(capacity - firstPartSize, copy.length) ;

        int secondPartSize = size - firstPartSize;

        System.arraycopy(items, Math.floorMod(nextFirst + 1, items.length), copy, firstPartStartIndex, firstPartSize);
        System.arraycopy(items, 0, copy, 0, secondPartSize);
        items = copy;
        nextFirst = Math.floorMod(firstPartStartIndex - 1, items.length);
        nextLast = Math.floorMod(secondPartSize, items.length);

    }

    @Override
    /* Adds an item of type T to the front of the deque. You can assume that item is never null. */
    public void addFirst(T item) {
        if (this.isEmpty()) {
            nextLast = Math.floorMod(nextLast + 1, items.length);
        }
        if (size + 2 >= items.length) {
            int resizeLength = (int) ((size + 2) * 1.25);
            resize(resizeLength);
        }
        items[nextFirst] = item;
        nextFirst = Math.floorMod(nextFirst - 1, items.length);
        size++;

    }

    @Override
    /* Adds an item of type T to the back of the deque. You can assume that item is never null. */
    public void addLast(T item) {

        if (this.isEmpty()) {
            nextFirst = Math.floorMod(nextFirst - 1, items.length);
        }
        if (size + 2 >= items.length) {
            int resizeLength = (int) ((size + 2) * 1.25);
            resize(resizeLength);
        }
        items[nextLast] = item;
        nextLast = Math.floorMod(nextLast + 1, items.length);
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
        if (this.isEmpty())
            return null;
        if (size == 1) {
            nextLast = Math.floorMod(nextLast - 1, items.length);
        }
        if (items.length >= 16 && size - 1 <= items.length * 0.25) {
            int resizeLength = (int) (size * 1.25);
            resize(resizeLength);
        }
        int removeIndex = Math.floorMod(nextFirst + 1, items.length);
        T removedItem = items[removeIndex];
        items[removeIndex] = null;
        nextFirst = removeIndex;
        size--;
        return removedItem;
    }

    @Override
    /* Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast() {
        if (this.isEmpty())
            return null;
        if (size == 1){
            nextFirst = Math.floorMod(nextFirst + 1, items.length);
        }
        if (items.length >= 16 && size - 1 < items.length * 0.25) {
            int resizeLength = (int) (size * 1.25);
            resize(resizeLength);
        }
        int removeIndex = Math.floorMod(nextLast - 1, items.length);
        T removedItem = items[removeIndex];
        items[removeIndex] = null;
        nextLast = removeIndex;
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
