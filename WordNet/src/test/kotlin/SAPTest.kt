import org.junit.jupiter.api.Test
import edu.princeton.cs.algs4.Digraph
import edu.princeton.cs.algs4.In
import org.junit.jupiter.api.Assertions.assertEquals


class SAPTest {

    @Test
    fun testAncestorAndLength() {
        val input = In("digraph1.txt")
        val d = Digraph(input)
        val sap = SAP(d)
        assertEquals(1, sap.ancestor(3, 11))
        assertEquals(4, sap.length(3, 11))
        assertEquals(3, sap.ancestor(7, 8))
        assertEquals(2, sap.length(7, 8))
    }
}