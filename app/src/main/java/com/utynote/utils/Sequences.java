package com.utynote.utils;

import java.util.Arrays;
import java.util.List;

public class Sequences {

    /**  Merges array into list. */
    public static <T, R extends List<T>> R merge(R list, T[] array) {
        list.addAll(Arrays.asList(array));
        return list;
    }

    /** Merges item into list. */
    public static <T, R extends List<T>> R merge(R list, T item) {
        list.add(item);
        return list;
    }
}
