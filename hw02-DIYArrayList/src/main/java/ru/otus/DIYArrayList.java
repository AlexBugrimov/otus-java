package ru.otus;

import java.util.*;

public class DIYArrayList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private E[] values;
    private int size = 0;

    public DIYArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public DIYArrayList(int initialCapacity) {
        checkCapacity(initialCapacity);
        values = (E[]) new Object[initialCapacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return Arrays.asList(values).contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new DIYArrayIterator<>(values, size);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.values, size);
    }

    @Override
    public <T> T[] toArray(T[] array) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E element) {
        if (size >= values.length) {
            toIncreaseCapacity(DEFAULT_CAPACITY);
        }
        values[size++] = element;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> elements) {
        toIncreaseCapacity(elements.size());
        boolean isAddedAll = false;
        for (E element : elements) {
            isAddedAll = this.add(element);
        }
        return isAddedAll;
    }

    @Override
    public E get(int index) {
        checkRange(index);
        return values[index];
    }

    @Override
    public E set(int index, E element) {
        checkRange(index);
        values[index] = element;
        return element;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new DIYArrayIterator<>(values, size);
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    private void checkCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(String.format("Illegal Capacity: %s", capacity));
        }
    }

    private void checkRange(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.format("Index: %d, Size: %d", index, size));
        }
    }

    private void toIncreaseCapacity(int capacity) {
        values = Arrays.copyOf(this.values, size + capacity);
    }

    private static final class DIYArrayIterator<E> implements ListIterator<E> {

        private final E[] array;
        private final int size;
        private int index;

        public DIYArrayIterator(E[] array, int size) {
            this.array = array;
            this.size = size;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public E next() {
            return array[index++];
        }

        @Override
        public boolean hasPrevious() {
            throw new UnsupportedOperationException();
        }

        @Override
        public E previous() {
            return array[index - 1];
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E e) {
            array[index - 1] = e;
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }
}
