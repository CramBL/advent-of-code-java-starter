package aoc.day08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day08Test {

    String input =
        """
        """;

    @Test
    public void testPart1() {
        // Given
        String input = this.input;

        // When
        String result = new Day08().part1(input);

        // Then
        assertEquals("1", result);
    }

    @Test
    public void testPart2() {
        // Given
        String input = this.input;

        // When
        String result = new Day08().part2(input);

        // Then
        assertEquals("2", result);
    }
}
