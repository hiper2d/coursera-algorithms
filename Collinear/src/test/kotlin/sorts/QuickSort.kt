package sorts

import utils.exch
import utils.shuffle

fun <T: Comparable<T>> quickSort(arr: Array<T>) {
    shuffle(arr)
    sort(arr, 0, arr.lastIndex)
}

private fun <T: Comparable<T>> sort(arr: Array<T>, lo: Int, hi: Int) {
    if (lo >= hi) {
        return
    }
    val part = partition(arr, lo, hi)
    sort(arr, lo, part - 1)
    sort(arr, part + 1, hi)
}

private fun <T: Comparable<T>> partition(arr: Array<T>, lo: Int, hi: Int): Int {
    var lt = lo
    var gt = hi + 1
    val x = arr[lo]
    while (true) {
        while (arr[++lt] < x && lt < hi);
        while (x < arr[--gt] && gt > lo);
        if (gt <= lt) {
            break
        }
        exch(arr, lt, gt)
    }
    exch(arr, lo, gt)
    return lt
}

fun main(args: Array<String>) {
    val arr = arrayOf(1, 9, 1, 9)
    quickSort(arr)
    arr.forEach { println(it) }
}
