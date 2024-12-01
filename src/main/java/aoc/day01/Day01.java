package aoc.day01;

import aoc.Day;
import aoc.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import aoc.Pair;

public class Day01 implements Day {

    public static Pair<List<Integer>, List<Integer>> extractColumns(String input) {
        List<Integer> columnA = new ArrayList<>();
        List<Integer> columnB = new ArrayList<>();

        for (String line : input.split("\\R")) {
            String[] numbers = line.trim().split("\\s+");
            columnA.add(Integer.parseInt(numbers[0]));
            columnB.add(Integer.parseInt(numbers[1]));
        }

        return new Pair<>(columnA, columnB);
    }

    @Override
    public String part1(String input) {

        Pair<List<Integer>, List<Integer>> columns = extractColumns(input);

        List<Integer> columnA = columns.getFirst();
        List<Integer> columnB = columns.getSecond();

        // Sort in ascending order (larger numbers at higher indices)
        Collections.sort(columnA);
        Collections.sort(columnB);

        List<Integer> distances = new ArrayList<>();
        for (int i = 0; i < columnA.size(); i++) {
            distances.add(Math.abs(columnA.get(i) - columnB.get(i)));
        }

        int sum = 0;
        for (int d : distances) {
            sum += d;
        }


        return String.valueOf(sum);
    }

    @Override
    public String part2(String input) {

        Pair<List<Integer>, List<Integer>> columns = extractColumns(input);

        List<Integer> columnA = columns.getFirst();
        List<Integer> columnB = columns.getSecond();

        int similarity_score = 0;
        for (int a : columnA) {
            int count = 0;
            for (int b : columnB) {
                if (a == b) {
                    ++count;
                }
            }
            similarity_score += a * count;
        }

        return String.valueOf(similarity_score);
    }

}
