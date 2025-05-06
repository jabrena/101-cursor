package info.jab.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FizzBuffTest {

    private FizzBuff fizzBuff;

    @BeforeEach
    void setUp() {
        fizzBuff = new FizzBuff();
    }

    private static Stream<Arguments> fizzBuzzTestData() {
        return Stream.of(
            Arguments.of(1, List.of("1")),
            Arguments.of(3, List.of("1", "2", "Fizz")),
            Arguments.of(5, List.of("1", "2", "Fizz", "4", "Buzz")),
            Arguments.of(15, List.of(
                "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz",
                "11", "Fizz", "13", "14", "FizzBuzz"
            )),
            Arguments.of(20, List.of(
                "1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz",
                "11", "Fizz", "13", "14", "FizzBuzz", "16", "17", "Fizz", "19", "Buzz"
            ))
        );
    }

    @ParameterizedTest(name = "For input n = {0}")
    @MethodSource("fizzBuzzTestData")
    @DisplayName("should return correct FizzBuzz sequence for various inputs")
    void shouldCalculateFizzBuzzSequenceCorrectly(int n, List<String> expectedOutput) {
        // Given
        // n and expectedOutput are provided by @MethodSource

        // When
        List<String> result = fizzBuff.calculate(n);

        // Then
        assertThat(result).isEqualTo(expectedOutput);
    }
}
