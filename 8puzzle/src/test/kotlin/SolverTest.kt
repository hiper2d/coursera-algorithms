import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

class SolverTest {

    @Test
    fun constructorNullArgument() {
        assertThrows(IllegalArgumentException::class.java) { Solver(null) }
    }

    @Test
    fun correctMoves() {
        assertEquals(0, Solver(readBoard("puzzle2x2-00.txt")).moves())
        assertEquals(2, Solver(readBoard("puzzle2x2-02.txt")).moves())
        assertEquals(6, Solver(readBoard("puzzle2x2-06.txt")).moves())
        assertEquals(12, Solver(readBoard("puzzle3x3-12.txt")).moves())
        assertEquals(17, Solver(readBoard("puzzle3x3-17.txt")).moves())
        assertEquals(20, Solver(readBoard("puzzle3x3-20.txt")).moves())
        assertEquals(8, Solver(readBoard("puzzle4x4-08.txt")).moves())
    }

    @Test
    fun detectUnsolvable() {
        assertTrue(Solver(readBoard("puzzle2x2-00.txt")).isSolvable)
        assertTrue(Solver(readBoard("puzzle3x3-09.txt")).isSolvable)
        assertFalse(Solver(readBoard("puzzle2x2-unsolvable1.txt")).isSolvable)
        assertFalse(Solver(readBoard("puzzle2x2-unsolvable2.txt")).isSolvable)
        assertFalse(Solver(readBoard("puzzle2x2-unsolvable3.txt")).isSolvable)
        assertFalse(Solver(readBoard("puzzle3x3-unsolvable.txt")).isSolvable)
        assertFalse(Solver(readBoard("puzzle3x3-unsolvable1.txt")).isSolvable)
        assertFalse(Solver(readBoard("puzzle3x3-unsolvable2.txt")).isSolvable)
        assertFalse(Solver(readBoard("puzzle4x4-unsolvable.txt")).isSolvable)
    }
}