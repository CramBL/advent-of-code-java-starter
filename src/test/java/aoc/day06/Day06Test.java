package aoc.day06;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Day06Test {

    /*
     * 
     * 0123456789
     * 0 ....#.....
     * 1 .........#
     * 2 ..........
     * 3 ..#.......
     * 4 .......#..
     * 5 ..........
     * 6 .#..^.....
     * 7 ........#.
     * 8 #.........
     * 9 ......#...
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     */

    String input = """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...
            """;

    @Test
    public void testPart1() {
        // Given
        String input = this.input;

        // When
        String result = new Day06().part1(input);

        // Then
        assertEquals("41", result);
    }

    // Option 1: [3,6]
    // Option 2: [6,7]
    // Option 3: [7,7]
    // Option 4: [1,8]
    // Option 5: [3,8]
    // Option 6: [7,9]
    @Test
    public void testPart2() {
        // Given
        String input = this.input;

        // When
        String result = new Day06().part2(input);

        // Then
        assertEquals("6", result);
    }
}
