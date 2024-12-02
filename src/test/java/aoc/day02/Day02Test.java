package aoc.day02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test {

    @Test
    public void testPart1() {
        // Given
        String input = """
                7 6 4 2 1
                1 2 7 8 9
                9 7 6 2 1
                1 3 2 4 5
                8 6 4 4 1
                1 3 6 7 9
                """;

        // When
        String result = new Day02().part1(input);

        // Then
        assertEquals("2", result);
    }

    @Test
    public void testPart2() {
        // Given
        String input = """
                """;

        // When
        String result = new Day02().part2(input);

        // Then
        assertEquals("2", result);
    }
}
