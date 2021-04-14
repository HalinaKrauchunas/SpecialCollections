package com.efimchick.ifmo.collections;

import java.util.*;

class MedianQueue implements Queue<Integer> {

    private ArrayList<Integer> elementData;

    public MedianQueue() {

        this.elementData = new ArrayList<>();
    }

    @Override
    public int size() {

        return elementData.size();
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
    public Iterator<Integer> iterator() {

        return elementData.iterator();
    }

    @Override
    public Object[] toArray() {

        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {

        return null;
    }

    @Override
    public boolean add(Integer integer) {

        return false;
    }

    @Override
    public boolean remove(Object o) {

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {

        return false;
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

    }

    @Override
    public boolean offer(Integer integer) {

        elementData.add(integer);
        return true;
    }

    @Override
    public Integer remove() {

        return null;
    }

    @Override
    public Integer poll() {

        int buf = elementData.get(0);
        elementData.remove(0);
        return buf;
    }

    @Override
    public Integer element() {

        return null;
    }

    @Override
    public Integer peek() {

        elementData.sort(Comparator.naturalOrder());
        if (elementData.size() >= 3) {
            elementData = medianFirstInLList(elementData);
        }
        return elementData.get(0);
    }

    private ArrayList<Integer> medianFirstInLList(ArrayList<Integer> elementData) {

        ArrayList<Integer> result = new ArrayList<>();
        int size = elementData.size();

        for (int i = 0, j = size - 1; i < (size / 2) + 1; i++, j--) {
            result.add(elementData.get(j));
            result.add(elementData.get(i));
        }
        result.remove(result.size() - 1);

        if (size % 2 == 0) {
            result.remove(result.size() - 1);
        }

        Collections.reverse(result);
        return result;
    }
}