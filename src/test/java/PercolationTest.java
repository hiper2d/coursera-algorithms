import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Pattern;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PercolationTest {

    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");

    private Percolation percolation;
    private Scanner scanner;

    @Test
    public void testInput1Percolation() throws IOException, URISyntaxException {
        initForInputFile("samples/input1.txt");
        runPercolation();
        assertThat(percolation.numberOfOpenSites(), is(1));
        assertThat(percolation.percolates(), is(true));
    }

    @Test
    public void testInput4Percolation() throws IOException, URISyntaxException {
        initForInputFile("samples/input4.txt");
        runPercolation();
        assertThat(percolation.numberOfOpenSites(), is(8));
        assertThat(percolation.percolates(), is(true));
    }

    @Test
    public void testInput10NoPercolation() throws IOException, URISyntaxException {
        initForInputFile("samples/input10-no.txt");
        runPercolation();
        assertThat(percolation.numberOfOpenSites(), is(55));
        assertThat(percolation.percolates(), is(false));
    }

    @Test
    public void testGreeting57Percolation() throws IOException, URISyntaxException {
        initForInputFile("samples/greeting57.txt");
        runPercolation();
        assertThat(percolation.numberOfOpenSites(), is(2522));
        assertThat(percolation.percolates(), is(false));
    }

    @Test
    public void testHeart25Percolation() throws IOException, URISyntaxException {
        initForInputFile("samples/heart25.txt");
        runPercolation();
        assertThat(percolation.numberOfOpenSites(), is(352));
        assertThat(percolation.percolates(), is(false));
    }

    @Test
    public void testWayne98Percolation() throws IOException, URISyntaxException {
        initForInputFile("samples/wayne98.txt");
        runPercolation();
        assertThat(percolation.numberOfOpenSites(), is(5079));
        assertThat(percolation.percolates(), is(true));
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
