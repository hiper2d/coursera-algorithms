package trees

class BinarySearchTree<K: Comparable<K>, V> {
    private var root: Node? = null

    private inner class Node(val key: K, var value: V) {
        var left: Node? = null
        var right: Node? = null
    }

    fun get(searchKey: K): V? {
        var x: Node? = root
        while (x != null) {
            when (searchKey.compareTo(x.key)) {
                -1 -> x = x.left
                1 -> x = x.right
                0 -> return x.value
            }
        }
        return null
    }

    fun put(key: K, value: V) {
        root = put(root, key, value)
    }

    fun printInOrder() {
        printInOrder(root)
    }

    private fun printInOrder(r: Node?) {
        if (r == null) {
            return
        }
        printInOrder(r.left)
        println(r.value)
        printInOrder(r.right)
    }

    private fun put(x: Node?, putKey: K, putValue: V): Node? {
        if (x == null) {
            return Node(putKey, putValue)
        }
        when (putKey.compareTo(x.key)) {
            -1 -> x.left = put(x.left, putKey, putValue)
            1 -> x.right = put(x.right, putKey, putValue)
            0 -> x.value = putValue
        }
        return x
    }
}

fun main(args: Array<String>) {
    val bst = BinarySearchTree<Int, Int>()
    bst.apply {
        put(1, 1)
        put(3, 3)
        put(2, 2)
    }
    bst.printInOrder()
}

