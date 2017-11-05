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

    private fun createKdTreeFromFile(filename: String): KdTree {
        val kdTree = KdTree()
        val reader = In(filename)
        while (!reader.isEmpty) {
            kdTree.insert(Point2D(reader.readDouble(), reader.readDouble()))
        }
        return kdTree
    }
}