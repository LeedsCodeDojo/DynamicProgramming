type Direction = START | RIGHT | DOWN

let solve (matrix:int[][]) = 

    let numberOfRows = matrix.Length
    let numberOfColumns = matrix.[0].Length
    let workspace = Array2D.zeroCreate numberOfRows numberOfColumns

    // Assign values for the top row,  working left to right
    workspace.[0,0] <- Direction.START, matrix.[0].[0]
    for column in [1..(numberOfColumns - 1)] do
        workspace.[0,column] <- Direction.RIGHT, matrix.[0].[column] + snd workspace.[0,column - 1]

    // Assign values for the first column,  working top to bottom
    for row in [1..(numberOfRows - 1)] do
        workspace.[row, 0] <- Direction.DOWN, matrix.[row].[0] + snd workspace.[row - 1,0]   

    // Assign values for all other rows,  taking the lower of the value from 
    // either the left or above.
    for row in [1..(numberOfRows - 1)] do
        for column in [1..(numberOfColumns - 1)] do
            let fromAbove = snd workspace.[row - 1,column]
            let fromLeft = snd workspace.[row,column - 1]
            workspace.[row, column] <- if fromAbove < fromLeft then
                                            Direction.DOWN, matrix.[row].[column] + fromAbove
                                       else
                                            Direction.RIGHT, matrix.[row].[column] + fromLeft

    // Walk back up the tree to produce the route
    let rec walkRoute row column solution =
        let solution' = (string) matrix.[row].[column] + if solution = "" then "" else " -> " + solution
        match fst workspace.[row,column] with
        | Direction.START -> solution'
        | Direction.RIGHT -> walkRoute row (column - 1) solution'
        | Direction.DOWN -> walkRoute (row - 1) column solution'
  
    //Work out the solution and mess about formatting it correctly
    let solution = walkRoute (numberOfRows - 1) (numberOfColumns - 1) ""
    solution + " = " + ((string) (snd (workspace.[numberOfRows - 1,numberOfColumns - 1])))






let matrix = [| 
                [|8;7;4;2;3;5|];
                [|3;2;1;2;5;2|];
                [|2;4;5;3;3;6|];
                [|6;4;5;4;3;2|];
                [|1;3;4;5;6;3|];
                [|7;3;2;1;3;5|]
             |]


solve matrix
// = 8 → 3 → 2 →1 →2 →3 →3 →3 →2 →3 →5 = 35


let matrix2 = [|
                [|2;5;17;13;1;11;10;6;19;20;10;17;14;15;11;2;4;8;13;11|];
                [|10;13;11;14;3;12;14;16;9;14;2;20;12;4;12;8;3;6;5;4|];
                [|8;8;12;10;3;6;1;3;9;10;6;3;16;3;4;8;11;16;19;8|];
                [|13;14;6;18;11;17;5;18;9;20;4;12;18;16;15;19;11;1;17;10|];
                [|8;16;10;1;3;9;3;14;5;8;8;17;7;13;14;3;18;4;5;4|];
                [|8;15;18;7;17;7;10;20;2;14;2;15;8;9;8;19;8;1;20;17|];
                [|12;6;11;14;17;15;14;7;5;19;4;15;20;14;14;6;4;8;8;14|];
                [|10;8;17;2;3;4;17;19;17;18;12;16;1;1;6;10;14;16;5;4|];
                [|15;20;4;16;12;7;13;3;19;9;15;12;8;20;4;20;10;13;20;16|];
                [|20;19;10;7;17;9;1;18;16;19;20;2;9;1;7;8;20;19;18;19|];
              |]    
solve matrix2 
//= 2 →5 →17 →13 →1 →3 →3 →6 →1 →3 →9 →10 →6 →3 →16 →3 →4 →8 →11 →11 →1 →4 →1 →8 →  
    

