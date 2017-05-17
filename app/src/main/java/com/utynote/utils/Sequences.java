package com.utynote.utils;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Sequences {

    /**  Merges array into list. */
    public static <T, R extends List<T>> R merge(R list, T[] array) {
        list.addAll(Arrays.asList(array));
        return list;
    }

    /** Maps iterable to collection. */
    public static <E> List<E> asList(Iterable<E> iterable) {
        List<E> list = new ArrayList<>();
        for (E item : iterable) {
            list.add(item);
        }
        return list;
    }

    /** Gets size of iterable. */
    public static <T> int size(@NonNull Iterable<T> iterable) {
        if (iterable instanceof Collection) {
            return ((Collection<?>) iterable).size();
        } else {
            int count = 0;
            Iterator iterator = iterable.iterator();
            while(iterator.hasNext()) {
                iterator.next();
                count++;
            }
            return count;
        }
    }
}
