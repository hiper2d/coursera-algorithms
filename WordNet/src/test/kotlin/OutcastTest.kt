import edu.princeton.cs.algs4.In
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class OutcastTest {

    @Test
    fun testOutcast() {
        val wordnet = WordNet("synsets.txt", "hypernyms.txt")
        val outcast = Outcast(wordnet)

        var input = In("outcast5.txt")
        var nouns = input.readAllStrings()
        assertEquals("table", outcast.outcast(nouns))

        input = In("outcast11.txt")
        nouns = input.readAllStrings()
        assertEquals("potato", outcast.outcast(nouns))
    }
}