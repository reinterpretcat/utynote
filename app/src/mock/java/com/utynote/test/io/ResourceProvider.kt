package com.utynote.test.io

object ResourceProvider {
    fun readString(path: String): String {
        return listOf(path)
                .map { p ->  ResourceProvider::class.java.classLoader.getResource(p) }
                .first()
                .readText()
    }

    fun readBytes(path: String): ByteArray {
        return listOf(path)
                .map { p ->  ResourceProvider::class.java.classLoader.getResource(p) }
                .first()
                .readBytes()
    }
}
