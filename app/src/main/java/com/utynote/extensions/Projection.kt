package com.utynote.extensions

sealed class Projection<out T> {

    abstract fun <U> map(fn: (T) -> U): Projection<U>

    class Value<out T>(val value: T) : Projection<T>() {

        override fun <U> map(fn: (T) -> U): Projection<U> = Value(fn(value))
    }

    class Empty<out T> : Projection<T>() {

        override fun <U> map(fn: (T) -> U): Projection<U> = Empty()
    }
}

fun <T> Projection<T>.getOrElse(or: () -> T): T = when (this) {

    is Projection.Empty -> or()

    is Projection.Value -> this.value
}

fun <T> Projection<T>.getOrThrow(): T = when (this) {

    is Projection.Empty -> throw IllegalArgumentException()

    is Projection.Value -> this.value
}
