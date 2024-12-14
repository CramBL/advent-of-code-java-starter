package aoc.day08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import aoc.Day;
import aoc.Pair;
import aoc.Utils;

public class Day08 implements Day {

    static HashMap<Character, List<Pair<Integer, Integer>>> parseGridAntennas(List<String> lines) {
        var antennas = new HashMap<Character, List<Pair<Integer, Integer>>>();
        for (int y = 0; y < lines.size(); y++) {
            var l = lines.get(y);
            for (int x = 0; x < l.length(); x++) {
                var c = l.charAt(x);
                if (Character.isLetterOrDigit(c)) {
                    var list = antennas.getOrDefault(c, new ArrayList<Pair<Integer, Integer>>());
                    var antenna = new Pair<Integer, Integer>(x, y);
                    System.out.println("Adding antennas (" + c + "): " + antenna);
                    list.add(new Pair<Integer, Integer>(x, y));
                    antennas.put(c, list);
                }
            }
        }
        return antennas;
    }

    static boolean isPointWithinGrid(int right_boundary, int bottom_boundary, Pair<Integer, Integer> point) {
        int x = point.getFirst();
        int y = point.getSecond();
        if (x < 0 || x > right_boundary) {
            return false;
        }
        if (y < 0 || y > bottom_boundary) {
            return false;
        }
        return true;
    }

    @Override
    public String part1(String input) {
        var lines = Utils.splitLines(input);
        var right_boundary = lines.getFirst().length() - 1;
        var bottom_boundary = lines.size() - 1;

        var antenna_map = parseGridAntennas(lines);
        var antennas_per_freq = antenna_map.values();


        var anti_nodes = new HashSet<Pair<Integer, Integer>>();
        for (var antennas : antennas_per_freq) {
            for (var pA : antennas) {
                for (var pB : antennas) {
                    if (pA.equals(pB)) {
                        continue;
                    }
                    int pAx = pA.getFirst();
                    int pBx = pB.getFirst();
                    int pAy = pA.getSecond();
                    int pBy = pB.getSecond();

                    int dx = pAx - pBx;
                    int dy = pAy - pBy;

                    var an1 = new Pair<>(pAx + dx, pAy + dy);
                    var an2 = new Pair<>(pBx - dx, pBy - dy);
                    if (isPointWithinGrid(right_boundary, bottom_boundary, an1)) {
                        System.out.println("AN " + an1);
                        anti_nodes.add(an1);
                    }
                    if (isPointWithinGrid(right_boundary, bottom_boundary, an2)) {
                        System.out.println("AN " + an2);
                        anti_nodes.add(an2);
                    }
                }
            }
        }

        return String.valueOf(anti_nodes.size());
    }

    @Override
    public String part2(String input) {
        return String.valueOf(2);
    }
}
