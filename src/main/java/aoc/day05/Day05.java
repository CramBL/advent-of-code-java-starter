package aoc.day05;

import aoc.Day;
import aoc.Pair;
import aoc.Utils;
import java.util.ArrayList;
import java.util.List;

public class Day05 implements Day {

    List<Pair<Integer, Integer>> parseRules(List<String> lines) {
        var rules = new ArrayList<Pair<Integer, Integer>>();

        for (int i = 0; i < lines.size(); i++) {
            var line = lines.get(i);
            if (line.contains("|")) {
                var string_arr = line.split("\\|");
                var num_before = Integer.parseUnsignedInt(string_arr[0]);
                var num_restrict = Integer.parseUnsignedInt(string_arr[1]);
                System.out.println(
                    "Adding rule " + num_before + " before " + num_restrict
                );
                rules.add(new Pair<Integer, Integer>(num_before, num_restrict));
            }
        }

        return rules;
    }

    List<List<Integer>> parseUpdates(List<String> lines) {
        var updates = new ArrayList<List<Integer>>();

        for (var l : lines) {
            if (l.contains(",")) {
                var string_arr = l.split(",");
                var update = new ArrayList<Integer>();
                for (var num : string_arr) {
                    update.add(Integer.parseInt(num));
                }
                updates.add(update);
            }
        }
        return updates;
    }

    @Override
    public String part1(String input) {
        var lines = Utils.splitLines(input);
        var rules = parseRules(lines);
        var updates = parseUpdates(lines);

        var valid_updates = new ArrayList<List<Integer>>();

        for (var update : updates) {
            boolean valid_update = true;
            for (var rule : rules) {
                if (
                    update.contains(rule.getFirst()) &&
                    update.contains(rule.getSecond())
                ) {
                    for (var num : update) {
                        if (num == rule.getFirst()) {
                            break;
                        }
                        if (num == rule.getSecond()) {
                            valid_update = false;
                        }
                    }
                }
            }
            if (valid_update) {
                valid_updates.add(update);
            }
        }

        int middle_sum = 0;
        for (var valid_update : valid_updates) {
            int middle_num_idx = valid_update.size() / 2;
            middle_sum += valid_update.get(middle_num_idx);
        }

        return String.valueOf(middle_sum);
    }

    @Override
    public String part2(String input) {
        return String.valueOf(2);
    }
}
