import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import utils.readPointsFromFileToList

class FastCollinearPointsTest {

    @Test
    fun testConstructorExceptions() {
        Assertions.assertThrows(IllegalArgumentException::class.java) { FastCollinearPoints(null) }
        Assertions.assertThrows(IllegalArgumentException::class.java) { FastCollinearPoints(arrayOf(Point(0, 0), null)) }
        Assertions.assertThrows(IllegalArgumentException::class.java) { FastCollinearPoints(arrayOf(Point(0, 0), Point(0, 0))) }
    }

    @Test
    fun testFastCollinearPointsSegmentsCountForInput6() {
        runFast("input6.txt", 1)
    }

    @Test
    fun testFastCollinearPointsSegmentsCountForHorizontal5() {
        runFast("horizontal5.txt", 5)
    }

    @Test
    fun testFastCollinearPointsSegmentsCountForHorizontal00() {
        runFast("horizontal100.txt", 100)
    }

    @Test
    fun testFastCollinearPointsSegmentsCountForVertical5() {
        runFast("vertical5.txt", 5)
    }

    @Test
    fun testFastCollinearPointsSegmentsCountForVertical100() {
        runFast("vertical100.txt", 100)
    }

    @Test
    fun testFastCollinearPointsSegmentsCountForEquidistant() {
        runFast("equidistant.txt", 4)
    }

    @Test
    fun testFastCollinearPointsSegmentsCountForInput20() {
        runFast("input20.txt", 5)
    }

    @Test
    fun testFastCollinearPointsSegmentsCountForInput48() {
        runFast("input48.txt", 6)
    }

    private fun runFast(filename: String, expected: Int) {
        val points = readPointsFromFileToList(filename)
        val dcp = FastCollinearPoints(points.toTypedArray())
        dcp.segments().forEach { println(it) }
        Assertions.assertEquals(expected, dcp.numberOfSegments())
    }
}