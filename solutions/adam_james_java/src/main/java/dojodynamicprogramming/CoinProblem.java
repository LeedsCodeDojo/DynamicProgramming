package dojodynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static java.util.stream.Collectors.joining;
import java.util.stream.Stream;

/**
 * Dynamic Programming, solving the "Coin Problem" in Java.
 *
 * @author Adam Prigmore / James Pittendreigh
 */
public class CoinProblem {

    // Set of the available coins
    Set<Integer> coinValues = new HashSet<>();
    // Memoization cache (Value => Result Mapping)
    List<Result> coinValueToResult = new ArrayList(Arrays.asList(new Result(0, 0, -1)));

    // Custom coinset constructor
    public CoinProblem(Integer... vals) {
        Collections.addAll(coinValues, vals);
    }

    public List<Integer> getCoinsForAmount(int amount) {
        // Populate the Memoization cache 
        Stream.iterate(1, n -> n++).limit(amount).forEach(this::populateCache);
        return toCoinList(amount);
    }

    private void populateCache(final int amount) {
        // Don't calculate if already in the memoization cache
        if (amount >= coinValueToResult.size()) {
            // Store the possible results in a list, for the coins where the value is less than the amount we're looking for
            Result minResult = coinValues.stream()
                    .filter(coin -> coin <= amount)
                    .map(coin -> coinValueToResult.get(amount - coin))
                    .sorted((a, b) -> Integer.compare(a.noOfCoins, b.noOfCoins))
                    .findFirst().orElse(null);
            
            // Add the new value based on the optimal result to get to the required amount
            coinValueToResult.add(new Result(minResult.noOfCoins + 1, amount, amount - minResult.value));
        }
    }

    private List<Integer> toCoinList(int amount) {
        List<Integer> coinsUsed = new ArrayList<>();
        while (amount > 0) {
            coinsUsed.add(coinValueToResult.get(amount).lastCoin);
            amount -= coinValueToResult.get(amount).lastCoin;
        }
        return coinsUsed;
    }
    
    public void prettyPrintMemoizationCache() {
        System.out.format("%nMemoization cache for coins: %s %n", coinValues.stream().map(Object::toString).collect(joining(", ")));
        coinValueToResult.stream().forEach(System.out::println);
    }
}