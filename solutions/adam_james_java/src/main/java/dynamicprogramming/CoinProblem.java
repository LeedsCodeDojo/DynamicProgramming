package dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.IntConsumer;
import static java.util.stream.Collectors.joining;
import java.util.stream.IntStream;

/**
 * Dynamic Programming, solving the "Coin Problem" in Java.
 *
 * @author Adam Prigmore / James Pittendreigh
 */
public class CoinProblem {

    private final Set<Integer> availableCoins = new HashSet<>();
    // Memoization cache (int value => Result Mapping)
    private final List<Result> coinValueToResult = new ArrayList(Arrays.asList(new Result(0, 0, -1)));

    public CoinProblem(Integer... vals) {
        Collections.addAll(availableCoins, vals);
    }

    public List<Integer> getCoinsForAmount(int amount) {
        IntStream.rangeClosed(1,amount).filter(n -> n >= coinValueToResult.size()).forEach(populateCache);
        return toCoinList(amount);
    }

    private IntConsumer populateCache = (amount) -> {
            availableCoins.stream()
                    .filter(coin -> coin <= amount)
                    .map(coin -> coinValueToResult.get(amount - coin))
                    .sorted((a, b) -> Integer.compare(a.noOfCoins, b.noOfCoins))
                    .findFirst()
                    .map(r -> new Result(r.noOfCoins + 1, amount, amount - r.value))
                    .map(coinValueToResult::add);
    };

    private List<Integer> toCoinList(int amount) {
        List<Integer> coinsUsed = new ArrayList<>();
        while (amount > 0) {
            coinsUsed.add(coinValueToResult.get(amount).lastCoin);
            amount -= coinValueToResult.get(amount).lastCoin;
        }
        return coinsUsed;
    }
    
    public void prettyPrintMemoizationCache() {
        System.out.format("%nMemoization cache for coins: %s %n", availableCoins.stream().map(Object::toString).collect(joining(", ")));
        coinValueToResult.stream().forEach(System.out::println);
    }
}