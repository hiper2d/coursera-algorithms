import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;

class PercolationTest {

    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");

    private Percolation percolation;
    private Scanner scanner;

    @Test
    public void testInput1Percolation() throws IOException, URISyntaxException {
        initForInputFile("samples/input1.txt");
        runPercolation();
        assertEquals(1, percolation.numberOfOpenSites());
        assertTrue(percolation.percolates());
    }

    @Test
    public void testInput4() throws IOException, URISyntaxException {
        initForInputFile("samples/input4.txt");
        runPercolation();
        assertEquals(8, percolation.numberOfOpenSites());
        assertTrue(percolation.percolates());
    }

    @Test
    public void testInput6Percolation() throws IOException, URISyntaxException {
        initForInputFile("samples/input6.txt");
        runPercolation();
        assertEquals(18, percolation.numberOfOpenSites());
        assertTrue(percolation.isOpen(4, 4));
        assertTrue(percolation.percolates());
    }

    @Test
    @Disabled
    public void testInput10Percolation() throws IOException, URISyntaxException {
        initForInputFile("samples/input10.txt");
        runPercolation();
        assertEquals(56, percolation.numberOfOpenSites());
        assertTrue(percolation.percolates());
    }

    @Test
    public void testInput10NoPercolation() throws IOException, URISyntaxException {
        initForInputFile("samples/input10-no.txt");
        runPercolation();
        assertEquals(55, percolation.numberOfOpenSites());
        assertFalse(percolation.percolates());
    }

    @Test
    public void testInput20() throws IOException, URISyntaxException {
        initForInputFile("samples/input20.txt");
        runPercolation();
        assertEquals(250, percolation.numberOfOpenSites());
        assertTrue(percolation.percolates());
        assertFalse(percolation.isFull(18, 1));
    }

    @Test
    public void testGreeting57Percolation() throws IOException, URISyntaxException {
        initForInputFile("samples/greeting57.txt");
        runPercolation();
        assertEquals(2522, percolation.numberOfOpenSites());
        assertFalse(percolation.percolates());
    }

    @Test
    public void testHeart25Percolation() throws IOException, URISyntaxException {
        initForInputFile("samples/heart25.txt");
        runPercolation();
        assertEquals(352, percolation.numberOfOpenSites());
        assertFalse(percolation.percolates());
    }

    @Test
    @Disabled
    public void testWayne98Percolation() throws IOException, URISyntaxException {
        initForInputFile("samples/wayne98.txt");
        runPercolation();
        assertEquals(5079, percolation.numberOfOpenSites());
        assertTrue(percolation.percolates());
    }

    private void runPercolation() {
        int n = scanner.nextInt();
        percolation = new Percolation(n);
        while(scanner.hasNext()) {
            int p = scanner.nextInt();
            int q = scanner.nextInt();
            percolation.open(p, q);
        }
    }

    private void initForInputFile(String file) throws URISyntaxException, IOException {
        Path path = Paths.get(getClass().getClassLoader().getResource(file).toURI());
        scanner = new Scanner(Files.newBufferedReader(path));
        scanner.useDelimiter(WHITESPACE_PATTERN);
    }
}
