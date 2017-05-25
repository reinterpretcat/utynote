package com.utynote.test.utils

object Utils {

    /** Copied from TextUtils as it is not mocked for jUnit tests.  */
    fun equals(a: CharSequence?, b: CharSequence?): Boolean {
        if (a === b) return true
        if (a != null && b != null && a.length == b.length) {
            val length = a.length
            if (a is String && b is String) {
                return a == b
            } else {
                for (i in 0..length - 1) {
                    if (a[i] != b[i]) return false
                }
                return true
            }
        }
        return false
    }
}
