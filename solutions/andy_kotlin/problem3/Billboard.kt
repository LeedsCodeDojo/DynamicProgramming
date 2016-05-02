package problem3

/**
 * Solution to the Billboard Placement Problem written in Kotlin
 *
 * Created by Andy on 01/05/2016.
 */

fun backtrack(values:List<Int>, separation: Int):List<Int>{
    val placements:MutableList<Int> = arrayListOf()
    var d = values.size - 1
    while (d > 0){
        if (values[d] != values[d-1]){
            placements.add(d)
            d -= separation
        }
        d -= 1
    }
    return placements.reversed()
}

/**
 * Calculate the Optimal Location of Billboards
 */
fun billboardPlacement(locations:List<Int>, revenue:List<Int>, dist:Int, separation:Int): Pair<Int, List<Int>>{
    val values : MutableList<Int> = arrayListOf()
    values.add(0)
    for (i in 1..dist){
        if (locations.contains(i)){
            val idx = locations.indexOf(i)
            if (i <= separation){
                values.add(arrayListOf(values.last(),revenue[idx]).max()!!)
            } else {
                values.add(arrayListOf(values.last(),revenue[idx] + values[i-(separation)+1]).max()!!)
            }
        } else {
            values.add(values.last())
        }
    }
    print(values)
    return Pair(values.last(),backtrack(values, separation))
}