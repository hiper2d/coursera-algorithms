import edu.princeton.cs.algs4.Point2D
import edu.princeton.cs.algs4.RectHV
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PointSetTest {

    @Test
    fun nearest() {
        val set = PointSET().apply {
            insert(Point2D(0.0, 0.0))
            insert(Point2D(1.0, 1.0))
        }
        assertEquals(Point2D(0.0, 0.0), set.nearest(Point2D(0.3, 0.3)))
    }

    @Test
    fun range() {
        val set = PointSET().apply {
            insert(Point2D(0.2, 0.2))
            insert(Point2D(0.4, 0.4))
        }
        val rect0 = RectHV(0.0, 0.0, .1, .1)
        val rect1 = RectHV(0.0, 0.0, .3, .3)
        val rect2 = RectHV(0.0, 0.0, .8, .8)
        assertEquals(0, set.range(rect0).count())
        assertEquals(1, set.range(rect1).count())
        assertEquals(2, set.range(rect2).count())
    }

    @Test
    fun rangeCircle4() {
        val set = PointSET().apply {
            insert(Point2D(.0, .5))
            insert(Point2D(.5, .0))
            insert(Point2D(1.0, .5))
            insert(Point2D(.5, 1.0))
        }
        val rect = RectHV(0.2, 0.3, .7, .8)
        assertEquals(0, set.range(rect).count())
    }
}