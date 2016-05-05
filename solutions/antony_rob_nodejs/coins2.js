// A more elegant solution itterating through solutions using a filter to look back and reduce to select the optimal solution

function solveOptimalCoins(availableCoins, targetValue){

   var solutions = [0];
   for(var currentValue=1; currentValue<=targetValue; currentValue++){
      solutions.push(undefined);

      var coinsWeCanUse = availableCoins.filter(function(currentCoin){
         return currentCoin <= currentValue;      
      });

      var coinWeShouldUse = coinsWeCanUse.reduce(function(previousCoin, currentCoin){
         return (solutions[currentValue - currentCoin] >= solutions[currentValue - previousCoin] ? previousCoin : currentCoin);
      }, 0);

      solutions[currentValue] = solutions[currentValue - coinWeShouldUse] + 1;
   }

   return solutions[targetValue];
}

function assert(message, expected, actual){
   if(expected === actual){
      console.log("PASS: " + message + ": expected " + expected);
   }else{
      console.log("FAIL: " + message + ": expected [" + expected + "] actual [" + actual + "]");
   }
}

var coinSet1 = [1,2,5,10, 20, 50];
assert(" 0 from " + coinSet1, 0, solveOptimalCoins(coinSet1, 0));
assert(" 1 from " + coinSet1, 1, solveOptimalCoins(coinSet1, 1));
assert(" 2 from " + coinSet1, 1, solveOptimalCoins(coinSet1, 2));
assert(" 3 from " + coinSet1, 2, solveOptimalCoins(coinSet1, 3));
assert(" 5 from " + coinSet1, 1, solveOptimalCoins(coinSet1, 5));
assert(" 9 from " + coinSet1, 3, solveOptimalCoins(coinSet1, 9));
assert("11 from " + coinSet1, 2, solveOptimalCoins(coinSet1, 11));
assert("23 from " + coinSet1, 3, solveOptimalCoins(coinSet1, 23));
assert("49 from " + coinSet1, 5, solveOptimalCoins(coinSet1, 49));
assert("87 from " + coinSet1, 5, solveOptimalCoins(coinSet1, 87));
var coinSet2 = [1, 4, 15, 20, 50];
assert(" 3 from " + coinSet2, 3, solveOptimalCoins(coinSet2, 3));
assert("10 from " + coinSet2, 4, solveOptimalCoins(coinSet2, 10));
assert("23 from " + coinSet2, 3, solveOptimalCoins(coinSet2, 23));
assert("32 from " + coinSet2, 4, solveOptimalCoins(coinSet2, 32));
