package problem3

import org.junit.Test

import org.junit.Assert.*

/**
 * Created by Andy on 01/05/2016.
 */
class BillboardTest {

    @Test
    fun testBillboardPlacement() {
        val result = billboardPlacement(listOf(6,7,12,13,14),
                            listOf(5, 6, 5,3,1),
                            20, 5)
        assertEquals(10, result.first)
        assertEquals(listOf(6,12),result.second)
    }
}