package problem2

/**
 * Created by Andy on 01/05/2016.
 */
enum class Direction(){
    HOME(), RIGHT(), DOWN()
}

fun backtrack(matrix: List<List<Int>>, scores:List<List<Pair<Int,Direction>>>): List<Int>{
    val selectedItems : MutableList<Int> = arrayListOf()
    var x = scores.last().size - 1
    var y = scores.size - 1
    selectedItems.add(matrix[y][x])
    while (scores[y][x].second != Direction.HOME){
        when (scores[y][x].second) {
            Direction.RIGHT ->  x -= 1
            Direction.DOWN -> y -= 1
        }
        selectedItems.add(matrix[y][x])
    }
    return selectedItems.reversed()
}

fun findPath(matrix:List<List<Int>>):Pair<Int,List<Int>>{
    var scores : MutableList<List<Pair<Int,Direction>>> = arrayListOf()
    var row : MutableList<Pair<Int,Direction>> = arrayListOf()
    row.add(Pair(matrix[0][0], Direction.HOME))
    for (i in 1 .. matrix[0].size -1){
        row.add(Pair(row.last().first+matrix[0][i], Direction.RIGHT))
    }
    scores.add(row)

    for (j in 1 .. matrix.size-1){
        row = arrayListOf()
        row.add(Pair(scores.last()[0].first + matrix[j][0], Direction.DOWN))
        for (i in 1..matrix[0].size-1){
            val nextItem = arrayOf(Pair(row.last().first + matrix[j][i], Direction.RIGHT),
                    Pair(scores.last()[i].first + matrix[j][i], Direction.DOWN))
                    .minBy { it.first }
            row.add(nextItem!!)
        }
        scores.add(row)
    }
    return Pair(scores.last().last().first, backtrack(matrix, scores))
}
