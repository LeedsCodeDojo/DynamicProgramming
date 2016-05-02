package problem1

import org.junit.Test

import org.junit.Assert.*

/**
 * Created by Andy on 01/05/2016.
 */
class CoinsTest {

    @Test
    fun testMakeChangeBritain(){
        val coins = listOf(1,2,5,10,20,50)
        var result = makeChange(coins, 1)
        assertEquals(1, result.first)
        assertEquals(listOf(1),result.second)

        result = makeChange(coins,3)
        assertEquals(2, result.first)
        assertEquals(listOf(2,1),result.second)

        result = makeChange(coins,5)
        assertEquals(1, result.first)
        assertEquals(listOf(5),result.second)

        result = makeChange(coins,9)
        assertEquals(3, result.first)
        assertEquals(listOf(5,2,2),result.second)

        result = makeChange(coins,11)
        assertEquals(2, result.first)
        assertEquals(listOf(10,1),result.second)

        result = makeChange(coins,23)
        assertEquals(3, result.first)
        assertEquals(listOf(20,2,1),result.second)

        result = makeChange(coins,49)
        assertEquals(5, result.first)
        assertEquals(listOf(20,20,5,2,2),result.second)

        result = makeChange(coins,87)
        assertEquals(5, result.first)
        assertEquals(listOf(50,20,10,5,2),result.second)
    }

    @Test
    fun testMakeChangeLilliputian(){
        val coins = listOf(1,4,15,20,50)
        var result = makeChange(coins, 3)
        assertEquals(3, result.first)
        assertEquals(listOf(1,1,1),result.second)

        result = makeChange(coins,10)
        assertEquals(4, result.first)
        assertEquals(listOf(4,4,1,1),result.second)

        result = makeChange(coins,23)
        assertEquals(3, result.first)
        assertEquals(listOf(15,4,4),result.second)

        result = makeChange(coins,32)
        assertEquals(4, result.first)
        assertEquals(listOf(15,15,1,1),result.second)
    }
}