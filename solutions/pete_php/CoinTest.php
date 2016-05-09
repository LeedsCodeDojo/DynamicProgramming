<?php
require __DIR__ . "/vendor/phpunit/phpunit/src/Framework/Assert/Functions.php";
require __DIR__ . "/Coin.php";

class CoinTest extends PHPUnit_Framework_TestCase
{
    public function test_it_returns_the_minimum_number_of_coins_needed_to_make_change()
    {
        $this->isEqual(0, minNumCoins([1, 2, 5], 0));
        $this->isEqual(1, minNumCoins([1, 2, 5], 1));
        $this->isEqual(1, minNumCoins([1, 2, 5], 2));
        $this->isEqual(2, minNumCoins([1, 2, 5], 3));
        $this->isEqual(2, minNumCoins([1, 2, 5], 4));
        $this->isEqual(1, minNumCoins([1, 2, 5], 5));
        $this->isEqual(2, minNumCoins([1, 2, 5], 6));
        $this->isEqual(2, minNumCoins([1, 2, 5], 7));
        $this->isEqual(3, minNumCoins([1, 2, 5], 8));
        $this->isEqual(3, minNumCoins([1, 2, 5], 9));
        $this->isEqual(2, minNumCoins([1, 2, 5], 10));
        $this->isEqual(3, minNumCoins([1, 2, 5], 11));
        $this->isEqual(3, minNumCoins([1, 2, 5], 12));
        $this->isEqual(4, minNumCoins([1, 2, 5], 13));
        $this->isEqual(4, minNumCoins([1, 2, 5], 14));
        $this->isEqual(3, minNumCoins([1, 2, 5], 15));
        $this->isEqual(5, minNumCoins([1, 2, 5, 10, 20, 50, 100], 256));
        $this->isEqual(15, minNumCoins([1, 2, 5, 10, 20, 50, 100], 999));
    }

    public function test_lower_elbonia_coins()
    {
        $this->isEqual(3, minNumCoins([1, 2, 5, 10, 21, 25], 24));
        $this->isEqual(3, minNumCoins([1, 2, 5, 10, 21, 25], 40));
        $this->isEqual(3, minNumCoins([1, 2, 5, 10, 21, 25], 63));
        $this->isEqual(8, minNumCoins([1, 2, 5, 10, 21, 25], 158));
    }

    /**
     * @expectedException InvalidArgumentException
     */
    public function test_it_throws_an_exception_if_there_is_no_coin_small_enough_to_make_change()
    {
        minNumCoins([5, 10], 2);
    }

    public function test_it_records_the_coins_needed_to_make_change()
    {
        $this->isEqual([], minCoins([1, 2, 5], 0));
        $this->isEqual([1], minCoins([1, 2, 5], 1));
        $this->isEqual([2], minCoins([1, 2, 5], 2));
        $this->isEqual([1, 2], minCoins([1, 2, 5], 3));
        $this->isEqual([2, 2], minCoins([1, 2, 5], 4));
        $this->isEqual([5], minCoins([1, 2, 5], 5));
        $this->isEqual([1, 5], minCoins([1, 2, 5], 6));
        $this->isEqual([5, 2], minCoins([1, 2, 5], 7));
        $this->isEqual([1, 2, 5], minCoins([1, 2, 5], 8));
        $this->isEqual([5, 2, 2], minCoins([1, 2, 5], 9));
        $this->isEqual([5, 5], minCoins([1, 2, 5], 10));
        $this->isEqual([100, 100, 100, 100, 100, 100, 100, 100, 100, 50, 20, 20, 5, 2, 2], minCoins([1, 2, 5, 10, 20, 50, 100], 999));
    }

    public function test_it_records_the_lower_elbonia_coins_needed_for_change()
    {
        $this->isEqual([21, 2, 1], minCoins([1, 2, 5, 10, 21, 25], 24));
        $this->isEqual([25, 5, 10], minCoins([1, 2, 5, 10, 21, 25], 40));
        $this->isEqual([21, 21, 21], minCoins([1, 2, 5, 10, 21, 25], 63));
        $this->isEqual([25, 25, 25, 25, 25, 21, 10, 2], minCoins([1, 2, 5, 10, 21, 25], 158));
    }

    private function isEqual($expected, $actual)
    {
        return $this->assertEquals($expected, $actual, '', 0.0, 10, true);
    }
}
