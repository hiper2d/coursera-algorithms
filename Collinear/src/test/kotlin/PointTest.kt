import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import utils.readPointsFromFileToList

class PointTest {

    private val p00 = Point(0, 0)
    private val p01 = Point(0, 1)
    private val p10 = Point(1, 0)
    private val p11 = Point(1, 1)

    @Test
    fun testComparing() {
        assertTrue(p00.compareTo(p00) == 0)

        assertTrue(p00.compareTo(p01) == -1)
        assertTrue(p00.compareTo(p10) == -1)
        assertTrue(p00.compareTo(p11) == -1)

        assertTrue(p10.compareTo(p11) == -1)
    }

    @Test
    fun testSlopeTo() {
        assertEquals(1.0, p00.slopeTo(p11))
        assertEquals(1.0, p11.slopeTo(p00))
        assertEquals(0.0, p00.slopeTo(p10))
        assertEquals(Double.POSITIVE_INFINITY, p00.slopeTo(p01))
    }

    @Test
    fun pointsSlopeCompareTest() {
        var p = Point(7, 5)
        var q = Point(7, 5)
        val r = Point(5, 7)
        assertEquals(-1, p.slopeOrder().compare(q, r))

        p = Point(25, 83)
        q = Point(25, 83)
        assertEquals(Double.NEGATIVE_INFINITY, p.slopeTo(q))
    }

    @Test
    fun testSlopeOrdering() {
        val slopeComparator:  Comparator<Point> = p00.slopeOrder()
        assertTrue(slopeComparator.compare(p01, p11) == 1)
        assertTrue(slopeComparator.compare(p11, p10) == 1)
        assertTrue(slopeComparator.compare(p11, p11) == 0)
    }

    @Test
    @DisplayName("slopeTo() should return positive 0 all the time")
    fun testSlopeToPositiveZero() {
        val points = readPointsFromFileToList("input6.txt")
        assertEquals(0.0, points[0].slopeTo(points[1]))
        assertEquals(0.0, points[0].slopeTo(points[2]))
    }
}