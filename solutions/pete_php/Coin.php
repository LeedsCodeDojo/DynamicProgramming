<?php

function minNumCoins(array $coins, int $target): int
{
    return count(minCoins($coins, $target));
}

function minCoins(array $coins, int $target): array
{
    if ($target <= 0) {
        return [];
    }
    if ($target < min($coins)) {
        throw new InvalidArgumentException('No coins small enough to make the change!');
    }

    $minCoinMap = [];

    for ($i = 0; $i <= $target; ++$i) {
        if (in_array($i, $coins)) {
            $minCoinMap[$i] = [$i];
        } else {
            $smallerCoins = array_filter($coins, function ($coin) use ($i) {
                return $coin < $i;
            });

            foreach ($smallerCoins as $c) {
                $v = $i - $c;
                if (!array_key_exists($i, $minCoinMap) || count($minCoinMap[$v]) + 1 < count($minCoinMap[$i])) {
                    $minCoinMap[$i] = array_merge([$c], $minCoinMap[$v]);
                }
            }
        }
    }

    return $minCoinMap[$target];
}
