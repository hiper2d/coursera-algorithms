import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PercolationStatsTest {
    @Test
    public void mean() throws Exception {
        PercolationStats stats = new PercolationStats(10, 10);
        assertTrue(stats.mean() < 1);
    }
}