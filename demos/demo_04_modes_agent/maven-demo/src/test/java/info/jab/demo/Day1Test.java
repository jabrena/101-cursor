package info.jab.demo;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Day1Test {

    @Test
    void testPart1() {
        // Given
        Day1 day1 = new Day1();

        // When
        int result = day1.part1();

        // Then
        assertThat(result).isEqualTo(11);
    }
} 