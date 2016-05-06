
type solved =
            {targetValue : int
             numberOfCoins  : int; 
             lastCoin : int
             coins : list<int>
            }

let solve (coins:int[]) targetValue =
    let (solutions:solved[]) = Array.zeroCreate (targetValue + 1)

    // We know the solution for a target value of 0 is 0 coins
    solutions.[0] <- {solved.targetValue = 0; solved.numberOfCoins=0; solved.lastCoin=0; solved.coins=[]  }

    // Now solve for a target value of 1 upwards
    for i in [1..targetValue] do

        // We can ignore any coins less than our target value.  For example no point looking at 50s when the total value is only 10
        let possibleCoins = coins |> Array.filter (fun x -> x <= i)

        // The clever bit....  for each coin create a tuple containing that coin and the previous solution it can be paired with.
        // Then sort the solutions so that the solution which uses the least number of coins is at the top
        // Then take the top solution
        let (theCoin, theSolution) = possibleCoins 
                                     |> Array.map (fun coin -> coin, solutions.[0..i - 1] |> Array.find (fun sol -> sol.targetValue + coin = i ))
                                     |> Array.sortBy (fun (coin, sol) -> sol.numberOfCoins)
                                     |> Array.head 

        solutions.[i] <- {
                          solved.targetValue = i; 
                          solved.numberOfCoins = theSolution.numberOfCoins+1; 
                          solved.lastCoin = theCoin; 
                          solved.coins = (theCoin :: theSolution.coins)  
                          }  

    // Return both the number of coins,  and the coins used
    solutions.[targetValue].numberOfCoins, solutions.[targetValue].coins


//Testing...
let coins = [|1;4;15;20;50|]
solve coins 23

