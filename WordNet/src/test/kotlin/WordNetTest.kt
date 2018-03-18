import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class WordNetTest {

    @Test
    fun nouns() {
        val net2 = WordNet("synsets.txt", "hypernyms.txt")
        assertEquals(119_188, net2.nouns().spliterator().exactSizeIfKnown)
    }

    @Test
    fun sap() {
        val net2 = WordNet("synsets.txt", "hypernyms.txt")
        assertEquals("animal animate_being beast brute creature fauna", net2.sap("worm", "bird"))
    }

    @Test
    fun distance() {
        val net = WordNet("synsets6.txt", "hypernyms6TwoAncestors.txt")
        assertEquals(1, net.distance("f", "a"))
        assertEquals(2, net.distance("b", "d"))
        assertEquals(2, net.distance("b", "d"))

        val net2 = WordNet("synsets.txt", "hypernyms.txt")
        assertEquals(1, net2.distance("1780s", "decade"))
        assertEquals(3, net2.distance("municipality", "region"))
        assertEquals(5, net2.distance("edible_fruit", "physical_entity"))
        assertEquals(23, net2.distance("white_marlin", "mileage"))
        assertEquals(33, net2.distance("Black_Plague", "black_marlin"))
    }
}