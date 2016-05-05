// The solution to the coins problem that Rob and I came up with during the Code Dojo session

function solveOptimalCoins(availableCoins, targetValue){
   var solutions = [];
   for(var i=0; i<=targetValue; i++){
      solutions.push(undefined);
   }

   solutions.forEach(function(currentValue, index){
      var coinsWeCanUse = availableCoins.filter(function(currentCoin){
         return currentCoin <= index;      
      });

      if(coinsWeCanUse.length === 0){
         solutions[index] = 0;
      }else{
         coinsWeCanUse.forEach(function(currentCoin){
            var candidateSolution = solutions[index - currentCoin] + 1;
            if(solutions[index] === undefined){
               solutions[index] = candidateSolution;      
            }else if(candidateSolution < solutions[index]){
               solutions[index] = candidateSolution;
            }else{
               // leave the solution unchanged
            }
         });
      }
   });

   return solutions[targetValue];
}

var coins = [1,2,5,10, 20, 50];
console.log("0 from " + coins + " should be 0: " + solveOptimalCoins(coins, 0));
console.log(" 1 from " + coins + " should be 1: " + solveOptimalCoins(coins, 1));
console.log("2 from " + coins + " should be 1: " + solveOptimalCoins(coins, 2));
console.log(" 3 from " + coins + " should be 2: " + solveOptimalCoins(coins, 3));
console.log(" 5 from " + coins + " should be 1: " + solveOptimalCoins(coins, 5));
console.log(" 9 from " + coins + " should be 3: " + solveOptimalCoins(coins, 9));
console.log("11 from " + coins + " should be 2: " + solveOptimalCoins(coins, 11));
console.log("23 from " + coins + " should be 3: " + solveOptimalCoins(coins, 23));
console.log("49 from " + coins + " should be 5: " + solveOptimalCoins(coins, 49));
console.log("87 from " + coins + " should be 5: " + solveOptimalCoins(coins, 87));
coins = [1, 4, 15, 20, 50];
console.log(" 3 from " + coins + " should be 3: " + solveOptimalCoins(coins, 3));
console.log("10 from " + coins + " should be 4: " + solveOptimalCoins(coins, 10));
console.log("23 from " + coins + " should be 3: " + solveOptimalCoins(coins, 23));
console.log("32 from " + coins + " should be 4: " + solveOptimalCoins(coins, 32));
