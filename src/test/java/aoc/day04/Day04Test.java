package aoc.day04;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day04Test {

    @Test
    public void testPart1() {
        // Given
        String input =
            """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX
            """;

        // When
        String result = new Day04().part1(input);

        // Then
        assertEquals("18", result);
    }

    @Test
    public void testPart2() {
        // Given
        String input =
            """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX
            """;

        // When
        String result = new Day04().part2(input);

        // Then
        assertEquals("9", result);
    }
}
