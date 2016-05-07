package dynamicprogramming;

import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.Test;
import static org.junit.Assert.*;

public class CoinProblemTest {

    CoinProblem c;

    @Test
    public void testFirstCoinSet() {
        c = new CoinProblem(1, 2, 5, 10, 20, 50);
        assertCoinSolution(1, c, 1);
        assertCoinSolution(3, c, 2, 1);
        assertCoinSolution(5, c, 5);
        assertCoinSolution(9, c, 5, 2, 2);
        assertCoinSolution(11, c, 10, 1);
        assertCoinSolution(23, c, 20, 2, 1);
        assertCoinSolution(49, c, 20, 20, 5, 2, 2);
        assertCoinSolution(87, c, 50, 20, 10, 5, 2);
        c.prettyPrintMemoizationCache();
    }

    @Test
    public void testSecondCoinSet() {
        c = new CoinProblem(1, 4, 15, 20, 50);
        assertCoinSolution(3, c, 1, 1, 1);
        assertCoinSolution(10, c, 4, 4, 1, 1);
        assertCoinSolution(23, c, 15, 4, 4);
        assertCoinSolution(32, c, 15, 15, 1, 1);
        c.prettyPrintMemoizationCache();
    }

    private void assertCoinSolution(int value, CoinProblem c, Integer... values) {
        assertThat(c.getCoinsForAmount(value), containsInAnyOrder(values));
    }
}
