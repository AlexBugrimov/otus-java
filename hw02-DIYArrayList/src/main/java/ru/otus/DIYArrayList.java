package ru.otus;

import java.util.*;

public class DIYArrayList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private E[] array;
    private int size = 0;

    public DIYArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public DIYArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException(String.format("Illegal Capacity: %s", initialCapacity));
        }
        array = (E[]) new Object[initialCapacity];
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
        for (E element : array) {
            if (element.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator<>(array, size);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.array, size);
    }

    @Override
    public <T> T[] toArray(T[] array) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E element) {
        if (size >= array.length) {
            array = Arrays.copyOf(this.array, size + DEFAULT_CAPACITY);
        }
        array[size++] = element;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> elements) {
        boolean isAddedAll = false;
        for (E element : elements)
            isAddedAll = this.add(element);
        return isAddedAll;
    }

    @Override
    public E get(int index) {
        if (index < size) {
            return array[index];
        }
        return null;
    }

    @Override
    public E set(int index, E element) {
        if (index < size) {
            array[index] = element;
            return element;
        }
        return null;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ArrayIterator<>(array, size);
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

    private static class ArrayIterator<E> implements ListIterator<E> {

        private int index;
        private final E[] array;
        private final int size;

        public ArrayIterator(E[] array, int size) {
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
