package aoc.day06;

import aoc.Day;
import aoc.Pair;
import aoc.Utils;

import java.util.HashSet;
import java.util.List;

public class Day06 implements Day {

    enum GuardDirection {
        Top {
            @Override
            GuardDirection turnRight() {
                return GuardDirection.Right;
            }
        },
        Right {
            @Override
            GuardDirection turnRight() {
                return GuardDirection.Bottom;
            }
        },
        Bottom {
            @Override
            GuardDirection turnRight() {
                return GuardDirection.Left;
            }
        },
        Left {
            @Override
            GuardDirection turnRight() {
                return GuardDirection.Top;
            }
        };

        abstract GuardDirection turnRight();
    }

    static Pair<Pair<Integer, Integer>, GuardDirection> findGuardPosition(List<String> lines) {

        int line_num = 0;
        for (var l : lines) {
            for (int i = 0; i < l.length(); i++) {
                var curr_char = l.charAt(i);
                var curr_pos = new Pair<>(i, line_num);
                if (curr_char == '^') {
                    return new Pair<Pair<Integer, Integer>, Day06.GuardDirection>(curr_pos,
                            GuardDirection.Top);
                }
            }

            line_num += 1;
        }
        return null;
    }

    static Integer parseUniqueGuardPos(List<String> lines, Pair<Pair<Integer, Integer>, GuardDirection> initial_guard) {

        HashSet<Pair<Integer, Integer>> visited_points = new HashSet<Pair<Integer, Integer>>();

        var guard_pos = initial_guard.getFirst();
        var guard_direction = initial_guard.getSecond();

        var right_boundary = lines.get(0).length() - 1;
        var bottom_boundary = lines.size() - 1;

        while (true) {
            var x = guard_pos.getFirst();
            var y = guard_pos.getSecond();
            switch (guard_direction) {
                case GuardDirection.Top -> y -= 1;
                case GuardDirection.Right -> x += 1;
                case GuardDirection.Bottom -> y += 1;
                case GuardDirection.Left -> x -= 1;
            }
            if (y < 0 || x < 0 || y > bottom_boundary || x > right_boundary) {
                visited_points.add(guard_pos);
                break;
            }
            var next_char = lines.get(y).charAt(x);
            if (next_char == '#') {
                guard_direction = guard_direction.turnRight();
            } else {
                visited_points.add(guard_pos);
                System.out.println("[" + visited_points.size() + "]" + "(" + guard_pos.getFirst() + ","
                        + guard_pos.getSecond() + ")");
                guard_pos = new Pair<Integer, Integer>(x, y);
            }

        }

        return visited_points.size();
    }

    public String part1(String input) {
        var lines = Utils.splitLines(input);

        var guard_initial = findGuardPosition(lines);

        System.out.println("Initial guard position: (" + guard_initial.getFirst().getFirst() + ","
                + guard_initial.getFirst().getSecond() + ")");

        var count_visited_points = parseUniqueGuardPos(lines, guard_initial);

        return String.valueOf(count_visited_points);
    }

    private static boolean isLoopCreated(List<String> lines, Pair<Integer, Integer> obstaclePos) {
        var guard_initial = findGuardPosition(lines);
        var guard_pos = guard_initial.getFirst();
        var guard_direction = guard_initial.getSecond();

        var right_boundary = lines.get(0).length() - 1;
        var bottom_boundary = lines.size() - 1;
        var visited_state_set = new HashSet<String>();

        while (true) {
            var x = guard_pos.getFirst();
            var y = guard_pos.getSecond();

            var currentState = x + "," + y + "," + guard_direction;
            if (visited_state_set.contains(currentState)) {
                return true;
            }
            visited_state_set.add(currentState);

            switch (guard_direction) {
                case Top -> y -= 1;
                case Right -> x += 1;
                case Bottom -> y += 1;
                case Left -> x -= 1;
            }

            if (y < 0 || x < 0 || y > bottom_boundary || x > right_boundary) {
                return false;
            }

            if (x == obstaclePos.getFirst() && y == obstaclePos.getSecond()) {
                guard_direction = guard_direction.turnRight();
            } else {
                var nextChar = lines.get(y).charAt(x);
                if (nextChar == '#') {
                    guard_direction = guard_direction.turnRight();
                } else {
                    guard_pos = new Pair<>(x, y);
                }
            }
        }
    }

    @Override
    public String part2(String input) {
        var lines = Utils.splitLines(input);
        var obstacles = new HashSet<Pair<Integer, Integer>>();

        for (int y = 0; y < lines.size(); y++) {
            for (int x = 0; x < lines.get(y).length(); x++) {
                if (lines.get(y).charAt(x) == '.') {
                    var obstacle_pos = new Pair<>(x, y);
                    if (isLoopCreated(lines, obstacle_pos)) {
                        obstacles.add(obstacle_pos);
                    }
                }
            }
        }

        return String.valueOf(obstacles.size());
    }
}
