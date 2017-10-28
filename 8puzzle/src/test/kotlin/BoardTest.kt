import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BoardTest {

    private val puzzle00Board = readBoard("puzzle00.txt")
    private val puzzle04Board = readBoard("puzzle04.txt")
    private val custom03Board = readBoard("custom03.txt")
    private val puzzle01Board = readBoard("puzzle01.txt")

    @Test
    fun testDimension() {
        assertEquals(3, puzzle04Board.dimension())
        assertEquals(3, custom03Board.dimension())
        assertEquals(10, puzzle00Board.dimension())
    }

    @Test
    fun testHamming() {
        assertEquals(4, puzzle04Board.hamming())
        assertEquals(5, custom03Board.hamming())
        assertEquals(0, puzzle00Board.hamming())
    }

    @Test
    fun testManhattan() {
        assertEquals(4, puzzle04Board.manhattan())
        assertEquals(10, custom03Board.manhattan())
        assertEquals(0, puzzle00Board.manhattan())
    }

    @Test
    fun testIsGoal() {
        assertFalse(puzzle04Board.isGoal)
        assertFalse(custom03Board.isGoal)
        assertTrue(puzzle00Board.isGoal)
    }

    @Test
    fun testTwinAndEquals() {
        assertNotEquals(puzzle04Board, puzzle04Board.twin())
        assertEquals(puzzle04Board, puzzle04Board.twin().twin())

        assertNotEquals(puzzle01Board, puzzle01Board.twin())
        assertEquals(puzzle01Board, puzzle01Board.twin().twin())
    }

    @Test
    fun equalsCorrectness() {
        assertFalse(puzzle04Board == null)
        assertFalse(puzzle04Board == puzzle00Board)
        assertTrue(
                Board(arrayOf(intArrayOf(1, 2), intArrayOf(3, 4), intArrayOf(5, 6))) ==
                        Board(arrayOf(intArrayOf(1, 2), intArrayOf(3, 4), intArrayOf(5, 6)))

        )
    }

    @Test
    fun testNeighbors() {
        assertEquals(2, puzzle01Board.neighbors().count())
        assertEquals(4, custom03Board.neighbors().count())
    }

    @Test
    fun toStringFoematCorrectness() {
        val b = readBoard("puzzle06.txt")
        val res = "4\n" +
                " 0  1  2  3 \n" +
                " 5  6  7  4 \n" +
                " 9 10 11  8 \n" +
                "13 14 15 12 \n"
        assertEquals(b.toString(), res)
    }
}