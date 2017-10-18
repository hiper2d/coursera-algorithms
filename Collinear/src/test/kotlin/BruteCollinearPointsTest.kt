import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import utils.readPointsFromFileToList

class BruteCollinearPointsTest {

    @Test
    fun testConstructorExceptions() {
        Assertions.assertThrows(IllegalArgumentException::class.java) { BruteCollinearPoints(null) }
        Assertions.assertThrows(IllegalArgumentException::class.java) { BruteCollinearPoints(arrayOf(Point(0, 0), null)) }
        Assertions.assertThrows(IllegalArgumentException::class.java) { BruteCollinearPoints(arrayOf(Point(0, 0), Point(0, 0))) }
    }

    @Test
    fun testSlopeOrderInvariantsForInput6() {
        val points = readPointsFromFileToList("input6.txt")
        assertTrue(points[0].slopeOrder().compare(points[1], points[2]) == 0)
        assertTrue(points[0].slopeOrder().compare(points[2], points[1]) == 0)

        assertTrue(points[1].slopeOrder().compare(points[0], points[2]) == 0)
        assertTrue(points[1].slopeOrder().compare(points[2], points[0]) == 0)

        assertTrue(points[2].slopeOrder().compare(points[0], points[1]) == 0)
        assertTrue(points[2].slopeOrder().compare(points[1], points[0]) == 0)
    }

    @Test
    fun testSlopeOrderToItself() {
    }

    @Test
    fun testBruteNumberOfSegments() {
        val points = readPointsFromFileToList("grid3x3.txt")
        val bcp = BruteCollinearPoints(points.toTypedArray())
        assertEquals(10, bcp.numberOfSegments())
    }
}