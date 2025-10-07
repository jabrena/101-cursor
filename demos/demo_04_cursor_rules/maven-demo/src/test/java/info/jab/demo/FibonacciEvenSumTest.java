package info.jab.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for FibonacciEvenSum
 */
public class FibonacciEvenSumTest {

    @Test
    public void testCalculateEvenSumWithSmallLimit() {
        FibonacciEvenSum fibonacci = new FibonacciEvenSum();

        // Test with limit 10: Fibonacci sequence up to 10 is 1, 2, 3, 5, 8
        // Even numbers: 2, 8. Sum = 10
        long result = fibonacci.calculateEvenSum(10);
        assertEquals(10, result);
    }

    @Test
    public void testCalculateEvenSumWithLimit20() {
        FibonacciEvenSum fibonacci = new FibonacciEvenSum();

        // Test with limit 20: Fibonacci sequence up to 20 is 1, 2, 3, 5, 8, 13
        // Even numbers: 2, 8. Sum = 10
        long result = fibonacci.calculateEvenSum(20);
        assertEquals(10, result);
    }

    @Test
    public void testCalculateEvenSumWithLimit34() {
        FibonacciEvenSum fibonacci = new FibonacciEvenSum();

        // Test with limit 34: Fibonacci sequence up to 34 is 1, 2, 3, 5, 8, 13, 21, 34
        // Even numbers: 2, 8, 34. Sum = 44
        long result = fibonacci.calculateEvenSum(34);
        assertEquals(44, result);
    }

    @Test
    public void testCalculateEvenSumWithLimit89() {
        FibonacciEvenSum fibonacci = new FibonacciEvenSum();

        // Test with limit 89: Fibonacci sequence up to 89 is 1, 2, 3, 5, 8, 13, 21, 34, 55, 89
        // Even numbers: 2, 8, 34. Sum = 44
        long result = fibonacci.calculateEvenSum(89);
        assertEquals(44, result);
    }

    @Test
    public void testCalculateEvenSumWithLimit144() {
        FibonacciEvenSum fibonacci = new FibonacciEvenSum();

        // Test with limit 144: Fibonacci sequence up to 144 is 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144
        // Even numbers: 2, 8, 34, 144. Sum = 188
        long result = fibonacci.calculateEvenSum(144);
        assertEquals(188, result);
    }

    @Test
    public void testCalculateEvenSumWithZeroLimit() {
        FibonacciEvenSum fibonacci = new FibonacciEvenSum();

        // Test with limit 0: no Fibonacci numbers should be considered
        long result = fibonacci.calculateEvenSum(0);
        assertEquals(0, result);
    }

    @Test
    public void testCalculateEvenSumWithLimit1() {
        FibonacciEvenSum fibonacci = new FibonacciEvenSum();

        // Test with limit 1: only the first Fibonacci number (1) should be considered
        // 1 is odd, so sum should be 0
        long result = fibonacci.calculateEvenSum(1);
        assertEquals(0, result);
    }

    @Test
    public void testCalculateEvenSumWithLimit2() {
        FibonacciEvenSum fibonacci = new FibonacciEvenSum();

        // Test with limit 2: Fibonacci numbers up to 2 are 1, 2
        // Even numbers: 2. Sum = 2
        long result = fibonacci.calculateEvenSum(2);
        assertEquals(2, result);
    }
}
