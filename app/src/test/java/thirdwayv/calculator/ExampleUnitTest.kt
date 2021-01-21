package thirdwayv.calculator

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import thirdwayv.calculator.utils.Calculator

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    var result: Double=0.0

    @Test
    fun testAdd(){
        result =Calculator.add(0.0,17.0)
        assertEquals("Incorrect Adding two Numbers", 17.0,result,0.0)
    }
    @Test
    fun testSub(){
        result =Calculator.sub(5.0,10.0)
        assertEquals("Incorrect Subtracting two Numbers", -5.0,result,0.0)
    }
    @Test
    fun testMultiply(){
        result =Calculator.mul(5.0,0.0)
        assertEquals("Incorrect multipling two Numbers", 0.0,result,0.0)
    }
    @Test
    fun testDivide(){
        result =Calculator.mul(5.0,0.0)
        assertEquals("Incorrect Dividing two Numbers", 0.0,result,0.0)
    }
}