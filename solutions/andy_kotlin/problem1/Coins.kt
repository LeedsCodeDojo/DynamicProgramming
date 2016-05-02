package problem1

/**
 * Created by Andy on 01/05/2016.
 */

fun makeChange(coins: List<Int>, targetValue: Int):Pair<Int,List<Int>>{
    val memo : MutableList<Pair<Int,Int>> = arrayListOf()
    memo.add(Pair(0,0))
    for (t in 1..targetValue) {
        val bestCoin = coins.filter({it <= t})
                .map { it -> Pair<Int,Int>(memo[t-it].first + 1, it)}
                .minBy { it.first }
        memo.add(bestCoin!!)
    }
    val selected : MutableList<Int> = arrayListOf()
    var x = memo.size - 1
    while (x > 0){
        selected.add(memo[x].second)
        x -= memo[x].second
    }
    return Pair(memo.last().first,selected.sortedByDescending{it})
}

