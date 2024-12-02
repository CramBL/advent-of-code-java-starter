package aoc.day02;

import aoc.Day;
import aoc.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day02 implements Day {

    public static List<List<Integer>> inputToIntegerLists(String input) {
        List<List<Integer>> reports = new ArrayList<>();
        for (String line : input.split("\n")) {
            List<Integer> nums = Arrays.stream(line.split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            reports.add(nums);
        }
        return reports;
    }

    public static Boolean isAllIncreasingOrDecreasing(List<Integer> report) {

        boolean increasing = report.equals(report.stream().sorted().toList());
        boolean decreasing = report.equals(report.stream().sorted(Comparator.reverseOrder()).toList());

        return increasing || decreasing;
    }

    public static Boolean isLevelDistanceOnetoThree(List<Integer> report) {
        for (int i = 0; i < report.size() - 1; i++) {
            int difference = Math.abs(report.get(i) - report.get(i + 1));
            if (difference < 1 || difference > 3) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String part1(String input) {
        var reports = inputToIntegerLists(input);
        int safe_reports = 0;
        for (var report : reports) {
            if (isAllIncreasingOrDecreasing(report) && isLevelDistanceOnetoThree(report)) {
                ++safe_reports;
            }
        }

        return String.valueOf(safe_reports);
    }

    @Override
    public String part2(String input) {

        return String.valueOf(2);
    }

}
