import org.hamcrest.core.Is;
import org.junit.Test;


import static org.junit.Assert.*;

public class PercolationStatsTest {
    @Test
    public void mean() throws Exception {
        PercolationStats stats = new PercolationStats(10, 10);
        assertTrue(stats.mean() < 1);
    }
}