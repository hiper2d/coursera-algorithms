import edu.princeton.cs.algs4.In

fun readBoard(filename: String): Board {
    val reader = In(filename)
    val n = reader.readInt()
    val blocks = Array(n) { IntArray(n) }
    (0 until n).forEach { i ->
        (0 until n).forEach { j ->
            blocks[i][j] = reader.readInt()
        }
    }
    return Board(blocks)
}