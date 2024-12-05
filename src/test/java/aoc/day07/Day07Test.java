package aoc.day07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day07Test {

    String input =
        """
        """;

    @Test
    public void testPart1() {
        // Given
        String input = this.input;

        // When
        String result = new Day07().part1(input);

        // Then
        assertEquals("1", result);
    }

    @Test
    public void testPart2() {
        // Given
        String input = this.input;

        // When
        String result = new Day07().part2(input);

        // Then
        assertEquals("2", result);
    }
}
