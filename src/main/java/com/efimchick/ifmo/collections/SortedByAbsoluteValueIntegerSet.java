package com.efimchick.ifmo.collections;

import java.util.*;

class SortedByAbsoluteValueIntegerSet implements Set<Integer> {

    private transient HashMap<Integer, Object> map;
    private static final Object PRESENT = new Object();

    public SortedByAbsoluteValueIntegerSet() {

        sortByComparator(map = new HashMap<>());
    }

    @Override
    public int size() {

        return map.size();
    }

    @Override
    public String toString() {

        Collection<Integer> collection = map.keySet();
        return String.join(" ", collection.stream()
            .map(i -> Integer.toString(i))
            .toArray(String[]::new));
    }

    @Override
    public boolean isEmpty() {

        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {

        return map.containsKey(o);
    }

    @Override
    public Iterator<Integer> iterator() {

        return map.keySet().iterator();
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

        return map.put(integer, PRESENT) == null;
    }

    @Override
    public boolean remove(Object o) {

        return map.remove(o) == PRESENT;
    }

    @Override
    public boolean containsAll(Collection<?> c) {

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {

        for (Integer integer : c) {
            add(integer);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {

        return false;
    }

    @Override
    public void clear() {

        map.clear();
    }

    private static Map<Integer, Object> sortByComparator(Map<Integer, Object> map) {

        List<Map.Entry<Integer, Object>> list = new LinkedList<>(map.entrySet());
        list.sort(Comparator.comparingInt(o -> Math.abs(o.getKey())));
        Map<Integer, Object> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, Object> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}

