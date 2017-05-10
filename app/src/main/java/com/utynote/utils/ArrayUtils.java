package com.utynote.utils;

import java.util.Arrays;
import java.util.List;

public class ArrayUtils {

    public static <T, R extends List<T>> R merge(R list, T[] array) {
        list.addAll(Arrays.asList(array));
        return list;
    }
}
