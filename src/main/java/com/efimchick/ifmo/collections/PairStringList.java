package com.efimchick.ifmo.collections;

import java.util.*;

public class PairStringList implements List<String> {

    private static final int DEFAULT_CAPACITY = 20;

    private static final String[] DEFAULT_CAPACITY_ELEMENT_DATA = new String[DEFAULT_CAPACITY];

    public String[] elementData;

    private int size;

    public PairStringList() {

        this.elementData = DEFAULT_CAPACITY_ELEMENT_DATA;
    }

    @Override
    public String get(int index) {

        Objects.checkIndex(index, size);
        return elementData[index];
    }

    @Override
    public String set(int index, String element) {

        if (index % 2 != 0) {
            index--;
        }

        Objects.checkIndex(index, size);
        String oldValue = elementData[index];
        int indexBrother;
        if (elementData[index + 1].equals(oldValue)) {
            indexBrother = index + 1;
        } else {
            indexBrother = index - 1;
        }
        elementData[index] = element;
        elementData[indexBrother] = element;
        return oldValue;
    }

    @Override
    public boolean add(String string) {

        add(string, elementData, size);
        return true;
    }

    @Override
    public boolean remove(Object o) {

        final Object[] es = elementData;
        final int size = this.size;
        int i = 0;
    found:
        {
            if (o == null) {
                for (; i < size; i++) {
                    if (es[i] == null) {
                        break found;
                    }
                }
            } else {
                for (; i < size; i++) {
                    if (o.equals(es[i])) {
                        break found;
                    }
                }
            }
            return false;
        }
        fastRemove((String[]) es, i);
        fastRemove((String[]) es, i);

        return true;
    }

    private void fastRemove(String[] es, int index) {

        final int newSize;
        if ((newSize = size - 1) > index) {
            System.arraycopy(es, index + 1, es, index, newSize - index);
        }
        es[size = newSize] = null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {

        int numNew = c.size();
        if (numNew == 0)
            return false;
        final String[] buf = this.elementData;
        String[] elementData;
        final int s;

        if (numNew > (elementData = this.elementData).length - (s = size)) {
            elementData = grow(s + numNew);
        }
        System.arraycopy(buf, 0, elementData, 0, buf.length);

        for (String str : c) {
            add(str);
        }
        return true;
    }

    @Override
    public String toString() {

        return "PairStringList{" +
            "elementData=" + Arrays.toString(elementData) +
            '}';
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {

        for (String s : c) {
            add(index, s);
            index += 2;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {

        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {

        return false;
    }

    @Override
    public void clear() {

        final String[] es = elementData;
        for (int to = size, i = size = 0; i < to; i++) {
            es[i] = null;
        }
    }

    @Override
    public void add(int index, String element) {

        rangeCheckForAdd(index);

        int i, j;
        final int poz;
        String[] before = new String[size + 2];
        String[] last = new String[size + 2];
        last[0] = element;
        last[1] = element;

        if (index % 2 == 0) {
            poz = index;
        } else {
            poz = index + 1;
        }

        for (i = 0; i < poz; i++) {
            before[i] = elementData[i];
        }

        for (i = 0, j = poz; j < size; j++, i++) {
            last[i + 2] = elementData[j];
        }

        for (i = 0; i < size - poz + 2; i++) {
            before[poz + i] = last[i];
        }
        elementData = before;
        size = size + 2;
    }

    @Override
    public String remove(int index) {

        Objects.checkIndex(index, size);
        final String[] es = elementData;
        String oldValue = es[index];
        int indexBrother;
        if (oldValue.equals(es[index + 1])) {
            indexBrother = index + 1;
        } else {
            indexBrother = index - 1;
        }
        fastRemove(es, index);
        fastRemove(es, indexBrother);

        return oldValue;
    }

    @Override
    public int indexOf(Object o) {

        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {

        return 0;
    }

    @Override
    public ListIterator<String> listIterator() {

        return null;
    }

    @Override
    public ListIterator<String> listIterator(int index) {

        return null;
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {

        return null;
    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public boolean isEmpty() {

        return false;
    }

    @Override
    public boolean contains(Object o) {

        return false;
    }

    @Override
    public Iterator<String> iterator() {

        String[] elementData = this.elementData;
        return new Iterator<>() {

            int cursor;
            int lastRet = -1;

            @Override
            public boolean hasNext() {

                return cursor != size;
            }

            @Override
            public String next() {

                int i = cursor;
                if (i >= size)
                    throw new NoSuchElementException();
                if (i >= elementData.length)
                    throw new ConcurrentModificationException();
                cursor = i + 1;
                return elementData[lastRet = i];
            }
        };
    }

    @Override
    public Object[] toArray() {

        return Arrays.copyOf(elementData, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {

        if (a.length < size) {
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        }
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    private void add(String string, String[] elementData, int size) {

        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size] = string;
        elementData[size + 1] = string;
        this.size = size + 2;
    }

    private String[] grow() {

        return grow(size + 2);
    }

    private String[] grow(int minCapacity) {

        return elementData = new String[Math.max(DEFAULT_CAPACITY, minCapacity)];
    }

    private void rangeCheckForAdd(int index) {

        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }
}
