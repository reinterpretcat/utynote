package com.utynote.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Sequences {

    /**  Merges array into list. */
    public static <T, R extends List<T>> R merge(R list, T[] array) {
        list.addAll(Arrays.asList(array));
        return list;
    }

    /** Maps iterable to collection. */
    public static <E> Collection<E> map(Iterable<E> iterable) {
        Collection<E> list = new ArrayList<>();
        for (E item : iterable) {
            list.add(item);
        }
        return list;
    }
}
