// Algorithm based on Andy's Kotlin solution

let x = [|6; 7; 10; 12; 13; 14; 17; 19; 22; 23; 26; 28; 29; 30|] //Locations
let r = [|4; 11; 8; 5; 3; 5; 9; 5; 4; 5; 3; 3; 4; 7|] //Revenues
let M = 30  //Total miles from Leeds to Manchester
let n = 4   //Mimimum gap

let workspace = Array.zeroCreate (M+1)

// When we are at zero miles from the start (ie. at Leeds) then there is no where to place a board
workspace.[0] <- 0 

// We now find the best solution for 1 mile ... M miles
for i in [1..M] do 
    match x |> Array.tryFindIndex (fun b -> b = i) with
    | None -> workspace.[i] <- workspace.[i - 1]  //No change to previous best solution as we have no board to place
    | Some(idx) ->  workspace.[i] <- if i <= n then 
                                        max workspace.[i - 1] r.[idx]  //In the first n miles we just place the best board we have
                                     else
                                        max workspace.[i - 1] (r.[idx] + workspace.[i-(n+1)])   //decide between the previous best and placing this board plus the best @ gap

