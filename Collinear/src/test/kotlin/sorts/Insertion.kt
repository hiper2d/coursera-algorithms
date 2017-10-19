package sorts

import utils.exch
import utils.less

fun <T: Comparable<T>> insertionSort(a: Array<T>) {
    println("Insertion sort")
    for (i in 0..a.lastIndex) {
        var current = i
        var prev = i - 1
        while (prev >= 0 && less(a, current, prev)) {
            exch(a, current--, prev--)
        }
    }
}

fun main(args: Array<String>) {
    val arr = arrayOf(1, 2, 5, 7, 5, 2, 9)
    insertionSort(arr)
    arr.forEach { println(it) }
}