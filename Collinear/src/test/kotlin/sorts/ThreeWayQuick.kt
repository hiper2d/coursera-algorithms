package sorts

import utils.exch
import utils.shuffle

fun <T: Comparable<T>> threeWayQuickSort(a: Array<T>){
    if (a.size <= 7) {
        insertionSort(a)
        return
    }
    println("Three way quick sort")
    shuffle(a)
    sort(a, 0, a.lastIndex)
}

private fun <T: Comparable<T>> sort(a: Array<T>, lo: Int, hi: Int) {
    if (lo >= hi) {
        return
    }

    var lt = lo
    var gt = hi
    var i = lo
    val partitionVal: T = a[lo]

    while (i <= gt) {
        val comparison: Int = a[i].compareTo(partitionVal)
        when (comparison) {
            -1 -> exch(a, lt++, i++)
            1 -> exch(a, gt--, i)
            0 -> i++
        }
    }

    sort(a, lo, lt - 1)
    sort(a, gt + 1, hi)
}

fun main(args: Array<String>) {
    val arr = arrayOf(1, 2, 5, 7, 5, 2, 9)
    threeWayQuickSort(arr)
    arr.forEach { println(it) }

    println()
    val arr2 = arrayOf(1, 2, 5, 7, 5, 9, 100, 100, 200, 999)
    threeWayQuickSort(arr2)
    arr2.forEach { println(it) }
}
