package aoc.day04;

import aoc.Day;
import aoc.Utils;
import javax.sound.midi.Sequence;

public class Day04 implements Day {

    enum SequenceStart {
        X {
            @Override
            Boolean isHorizontalSequence(String line_rest) {
                if (line_rest.length() < 3) {
                    return false;
                }

                return line_rest.startsWith("MAS");
            }

            @Override
            Integer countVerticalSequences(
                String curr_line,
                String line2,
                String line3,
                String line4
            ) {
                int seq_count = 0;
                for (int i = 0; i < curr_line.length(); i++) {
                    if (
                        curr_line.charAt(i) == 'X' &&
                        line2.charAt(i) == 'M' &&
                        line3.charAt(i) == 'A' &&
                        line4.charAt(i) == 'S'
                    ) {
                        seq_count += 1;
                    }
                }
                return seq_count;
            }
        },
        S {
            @Override
            Boolean isHorizontalSequence(String line_rest) {
                if (line_rest.length() < 3) {
                    return false;
                }
                return line_rest.startsWith("AMX");
            }

            @Override
            Integer countVerticalSequences(
                String curr_line,
                String line2,
                String line3,
                String line4
            ) {
                int seq_count = 0;
                for (int i = 0; i < curr_line.length(); i++) {
                    if (
                        curr_line.charAt(i) == 'S' &&
                        line2.charAt(i) == 'A' &&
                        line3.charAt(i) == 'M' &&
                        line4.charAt(i) == 'X'
                    ) {
                        seq_count += 1;
                    }
                }
                return seq_count;
            }
        };

        abstract Boolean isHorizontalSequence(String line);

        abstract Integer countVerticalSequences(
            String curr_line,
            String line2,
            String line3,
            String line4
        );
    }

    Integer countHorizontalSequences(String line) {
        Integer count = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.length() - i - 1 < 3) {
                break;
            }
            if (line.charAt(i) == 'X') {
                System.out.println(
                    "X at [" + i + ']' + " Substring: " + line.substring(i + 1)
                );
                if (
                    SequenceStart.X.isHorizontalSequence(line.substring(i + 1))
                ) {
                    count += 1;
                }
            } else if (line.charAt(i) == 'S') {
                if (
                    SequenceStart.S.isHorizontalSequence(line.substring(i + 1))
                ) {
                    count += 1;
                }
            }
        }

        return count;
    }

    Integer countVerticalSequences(
        String curr_line,
        String line2,
        String line3,
        String line4
    ) {
        Integer count = 0;
        count += SequenceStart.X.countVerticalSequences(
            curr_line,
            line2,
            line3,
            line4
        );
        count += SequenceStart.S.countVerticalSequences(
            curr_line,
            line2,
            line3,
            line4
        );
        return count;
    }

    Integer countDiagonalSequences(
        String curr_line,
        String line2,
        String line3,
        String line4
    ) {
        Integer count = 0;
        for (int i = 0; i < curr_line.length(); i++) {
            if (curr_line.charAt(i) == 'X') {
                if (i > 2) {
                    // Diagonal down left is possible
                    if (
                        line2.charAt(i - 1) == 'M' &&
                        line3.charAt(i - 2) == 'A' &&
                        line4.charAt(i - 3) == 'S'
                    ) {
                        count += 1;
                    }
                }
                if (curr_line.length() - i > 3) {
                    // diagonal down right is possible
                    char second_diag_right = line2.charAt(i + 1);
                    char third_diag_right = line3.charAt(i + 2);
                    char fourth_diag_right = line4.charAt(i + 3);
                    System.out.println(
                        "Diag down right: " +
                        second_diag_right +
                        third_diag_right +
                        fourth_diag_right
                    );
                    if (
                        second_diag_right == 'M' &&
                        third_diag_right == 'A' &&
                        fourth_diag_right == 'S'
                    ) {
                        count += 1;
                    }
                }
            } else if (curr_line.charAt(i) == 'S') {
                if (i > 2) {
                    // Diagonal down left is possible
                    if (
                        line2.charAt(i - 1) == 'A' &&
                        line3.charAt(i - 2) == 'M' &&
                        line4.charAt(i - 3) == 'X'
                    ) {
                        count += 1;
                    }
                }
                if (curr_line.length() - i > 3) {
                    // diagonal down right is possible
                    if (
                        line2.charAt(i + 1) == 'A' &&
                        line3.charAt(i + 2) == 'M' &&
                        line4.charAt(i + 3) == 'X'
                    ) {
                        count += 1;
                    }
                }
            }
        }
        return count;
    }

    @Override
    public String part1(String input) {
        var lines = Utils.splitLines(input);

        int seq_count = 0;

        for (int i = 0; i < lines.size(); i++) {
            System.out.println(i + ": seqs=" + seq_count);
            var remainder_size = lines.size() - i - 1;

            var curr_line = lines.get(i);

            seq_count += countHorizontalSequences(curr_line);

            if (remainder_size > 2) {
                // Remainder could contain vertical or diagonal instances of the sequence
                var line2 = lines.get(i + 1);
                var line3 = lines.get(i + 2);
                var line4 = lines.get(i + 3);
                seq_count += countVerticalSequences(
                    curr_line,
                    line2,
                    line3,
                    line4
                );
                seq_count += countDiagonalSequences(
                    curr_line,
                    line2,
                    line3,
                    line4
                );
            }
        }

        return String.valueOf(seq_count);
    }

    @Override
    public String part2(String input) {
        var lines = Utils.splitLines(input);

        int seq_count = 0;

        for (int i = 0; i < lines.size(); i++) {
            var remainder_size = lines.size() - i - 1;

            if (remainder_size > 1) {
                var curr_line = lines.get(i);
                var line2 = lines.get(i + 1);
                var line3 = lines.get(i + 2);
                // Skips first and last letter
                for (int j = 1; j < curr_line.length() - 1; j++) {
                    if (line2.charAt(j) == 'A') {
                        boolean diag_down_right_ok = false;
                        boolean diag_down_left_ok = false;
                        if (
                            (curr_line.charAt(j - 1) == 'S' &&
                                line3.charAt(j + 1) == 'M') ||
                            (curr_line.charAt(j - 1) == 'M' &&
                                line3.charAt(j + 1) == 'S')
                        ) {
                            diag_down_right_ok = true;
                        }
                        if (
                            (curr_line.charAt(j + 1) == 'S' &&
                                line3.charAt(j - 1) == 'M') ||
                            (curr_line.charAt(j + 1) == 'M' &&
                                line3.charAt(j - 1) == 'S')
                        ) {
                            diag_down_left_ok = true;
                        }
                        if (diag_down_left_ok && diag_down_right_ok) {
                            System.out.println(
                                "X-MAS with A at l=" + (i + 1) + " [" + j + ']'
                            );
                            seq_count += 1;
                        }
                    }
                }
            }
            System.out.println(i + ": seqs=" + seq_count);
        }

        return String.valueOf(seq_count);
    }
}
