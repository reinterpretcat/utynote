package com.utynote.extensions


sealed class Either<out T1, out T2, out T3> {

    fun <T> fold(fn1: (T1) -> T, fn2: (T2) -> T, fn3: (T3) -> T): T = when (this) {
        is First  -> fn1(this.value)
        is Second -> fn2(this.value)
        is Third  -> fn3(this.value)
    }

    fun firstProjection(): Projection<out T1> = when (this) {
        is First -> Projection.Value(this.value)
        else -> Projection.Empty<T1>()
    }

    fun secondProjection(): Projection<out T2> = when (this) {
        is Second -> Projection.Value(this.value)
        else -> Projection.Empty<T2>()
    }

    fun thirdProjection(): Projection<out T3> = when (this) {
        is Third -> Projection.Value(this.value)
        else -> Projection.Empty<T3>()
    }

    class First<out T1>(val value: T1) : Either<T1, Nothing, Nothing>()

    class Second<out T2>(val value: T2) : Either<Nothing, T2, Nothing>()

    class Third<out T3>(val value: T3) : Either<Nothing, Nothing, T3>()
}

