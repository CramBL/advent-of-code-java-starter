package aoc.day07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day07Test {

    String input = """
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20
                    """;

    @Test
    public void testPart1() {
        // Given
        String input = this.input;

        // When
        String result = new Day07().part1(input);

        // Then
        assertEquals("3749", result);
    }

    @Test
    public void testPart2() {
        // Given
        String input = this.input;

        // When
        String result = new Day07().part2(input);

        // Then
        assertEquals("11387", result);
    }
}
