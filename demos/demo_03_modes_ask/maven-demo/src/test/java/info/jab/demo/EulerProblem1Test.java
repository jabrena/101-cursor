package info.jab.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EulerProblem1Test {

    @Test
    @DisplayName("Should return 23 when calculating sum of multiples of 3 or 5 below 10")
    void shouldReturnCorrectSumForExample() {
        // Given
        EulerProblem1 problem = new EulerProblem1();
        long expected = 23;

        // When
        long result = problem.solution(10);

        // Then
        assertEquals(expected, result, "The sum of multiples of 3 or 5 below 10 should be 23");
    }

    @Test
    @DisplayName("Should return correct sum for multiples of 3 or 5 below 1000")
    void shouldReturnCorrectSumForProblem() {
        // Given
        EulerProblem1 problem = new EulerProblem1();
        long expected = 233168;

        // When
        long result = problem.solution(1000);

        // Then
        assertEquals(expected, result, "The sum of multiples of 3 or 5 below 1000 should be 233168");
    }
} 