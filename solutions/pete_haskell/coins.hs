import Test.Hspec
import Data.Array

main :: IO ()
main = hspec $
    describe "change calculator" $
        it "should find the minimum number of coins required to reach a target value" $ do
            minNumCoins [1,2,5] 0 `shouldBe` 0
            minNumCoins [1,2,5] 1 `shouldBe` 1
            minNumCoins [1,2,5] 2 `shouldBe` 1
            minNumCoins [1,2,5] 3 `shouldBe` 2
            minNumCoins [1,2,5] 4 `shouldBe` 2
            minNumCoins [1,2,5] 5 `shouldBe` 1
            minNumCoins [1,2,5] 6 `shouldBe` 2
            minNumCoins [1,2,5] 7 `shouldBe` 2
            minNumCoins [1,2,5] 8 `shouldBe` 3
            minNumCoins [1,2,5] 9 `shouldBe` 3
            minNumCoins [1,2,5] 10 `shouldBe` 2
            minNumCoins [1,2,5] 20 `shouldBe` 4
            minNumCoins [1,2,5] 30 `shouldBe` 6
            minNumCoins [1,2,5, 10, 20, 50, 100] 999 `shouldBe` 15
            minNumCoins [1,2,5, 21, 25] 63 `shouldBe` 3

minNumCoins :: [Int] -> Int -> Int
minNumCoins coins target = lookup ! target
    where
        lookup = listArray (0, target) $ map numCoins [0..]
        numCoins 0 = 0
        numCoins i | i `elem` coins = 1
        numCoins i = minimum [1 + lookup ! (i-c) | c <- coins, c < i]
