package aoc.day07;

import java.util.ArrayList;
import java.util.List;

import aoc.Day;
import aoc.Utils;

public class Day07 implements Day {

    enum Operator {
        Add {
            @Override
            Operator nextOp() {
                return Mul;
            }

            @Override
            Long evaluate(Long current_val, Long next_val) {
                return current_val + next_val;
            }
        },
        Mul {
            @Override
            Operator nextOp() {
                return Add;
            }

            @Override
            Long evaluate(Long current_val, Long next_val) {
                return current_val * next_val;
            }
        };

        abstract Operator nextOp();

        abstract Long evaluate(Long current_val, Long next_val);
    }

    static boolean isSolvable(long testval, List<Integer> nums) {
        return solve(testval, nums, 0, nums.get(0), Operator.Add) ||
                solve(testval, nums, 0, nums.get(0), Operator.Mul);
    }

    static boolean solve(long testval, List<Integer> nums, int index, long current, Operator op) {
        if (index == nums.size() - 1) {
            return current == testval;
        }
        long next_val = nums.get(index + 1);

        if (solve(testval, nums, index + 1,
                op.evaluate(current, next_val),
                Operator.Add)) {
            return true;
        }

        return solve(testval, nums, index + 1,
                op == Operator.Add ? Operator.Mul.evaluate(current, next_val)
                        : Operator.Add.evaluate(current, next_val),
                Operator.Mul);
    }

    static Long parseTestValue(String line) {
        var equation_parts = line.split(":");
        assert (equation_parts.length == 2);
        Long test_value = Long.parseLong(equation_parts[0]);

        var equation_nums = equation_parts[1].split(" ");
        List<Integer> nums = new ArrayList<>();
        for (var n : equation_nums) {
            if (n.length() > 0) {
                nums.add(Integer.parseInt(n));
            }
        }

        if (isSolvable(test_value, nums)) {
            return test_value;
        }

        return 0L;
    }

    @Override
    public String part1(String input) {
        var lines = Utils.splitLines(input);
        Long sum = 0L;
        for (var l : lines) {
            sum += parseTestValue(l);
            System.out.println("-- " + sum + " --");
        }
        return String.valueOf(sum);
    }

    @Override
    public String part2(String input) {
        return String.valueOf(2);
    }
}
