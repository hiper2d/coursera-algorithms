import edu.princeton.cs.algs4.In
import edu.princeton.cs.algs4.Point2D
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class KdTreeTest {

    @Test
    fun insertAndContains() {
        val tree = createKdTreeFromFile("circle4.txt")
        assertFalse(tree.isEmpty)
        assertEquals(4, tree.size())
        assertTrue(tree.contains(Point2D(1.0, .5)))
        assertFalse(tree.contains(Point2D(.5, .5)))
    }

    @Test
    fun test1() {
        val tree = createKdTreeFromFile("test1.txt")
        assertTrue(tree.contains(Point2D(0.0, 0.25)))
    }

    @Test
    fun test2() {
        val tree = createKdTreeFromFile("test2.txt")
        assertTrue(tree.contains(Point2D(0.375, 0.5)))
    }

    @Test
    fun test3nearest() {
        val tree = createKdTreeFromFile("test3.txt")
        assertEquals(Point2D(0.499, 0.208), tree.nearest(Point2D(0.356, 0.057)))
    }

    @Test
    fun test4nearest() {
        val tree = createKdTreeFromFile("test4.txt")
        assertEquals(Point2D(0.4375, 0.65625), tree.nearest(Point2D(0.59375, 0.5625)))
    }

    private fun createKdTreeFromFile(filename: String): KdTree {
        val kdTree = KdTree()
        val reader = In(filename)
        while (!reader.isEmpty) {
            kdTree.insert(Point2D(reader.readDouble(), reader.readDouble()))
        }
        return kdTree
    }
}