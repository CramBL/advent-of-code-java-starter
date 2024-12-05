package aoc.day05;

import aoc.Day;
import aoc.Pair;
import aoc.Utils;
import java.util.ArrayList;
import java.util.Collections;
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

    Boolean isUpdateValid(
        List<Integer> update,
        List<Pair<Integer, Integer>> rules
    ) {
        boolean is_valid = true;
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
                        is_valid = false;
                    }
                }
            }
        }
        return is_valid;
    }

    @Override
    public String part1(String input) {
        var lines = Utils.splitLines(input);
        var rules = parseRules(lines);
        var updates = parseUpdates(lines);

        var valid_updates = new ArrayList<List<Integer>>();

        for (var update : updates) {
            if (isUpdateValid(update, rules)) {
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
        var lines = Utils.splitLines(input);
        var rules = parseRules(lines);
        var updates = parseUpdates(lines);

        var invalid_updates = new ArrayList<List<Integer>>();

        for (var update : updates) {
            if (!isUpdateValid(update, rules)) {
                invalid_updates.add(update);
            }
        }

        int invalid_update_count = invalid_updates.size();
        var corrected_updates = new ArrayList<List<Integer>>();
        var corrected_indices = new ArrayList<Integer>();

        int correct_remain = invalid_updates.size();
        int max_iter = 20000;
        int iter_count = 0;
        while (correct_remain > 0 && iter_count++ < max_iter) {
            for (int i = 0; i < invalid_updates.size(); i++) {
                if (corrected_indices.contains(i)) {
                    continue;
                }
                var update = invalid_updates.get(i);
                Boolean breaks_any_rule = false;
                for (var rule : rules) {
                    if (
                        update.contains(rule.getFirst()) &&
                        update.contains(rule.getSecond())
                    ) {
                        boolean breaks_rule = true;
                        for (var num : update) {
                            if (num == rule.getFirst()) {
                                breaks_rule = false;
                                break;
                            }
                            if (num == rule.getSecond()) {
                                breaks_rule = true;
                                breaks_any_rule = true;
                                break;
                            }
                        }
                        if (breaks_rule) {
                            System.out.println(
                                "Swapping " +
                                rule.getFirst() +
                                " and " +
                                rule.getSecond() +
                                " in: " +
                                update.toString()
                            );
                            int idx_first = update.indexOf(rule.getFirst());
                            int idx_second = update.indexOf(rule.getSecond());
                            Collections.swap(update, idx_first, idx_second);
                            invalid_updates.set(i, update);
                            System.out.println(
                                "After swap:" +
                                invalid_updates.get(i).toString()
                            );
                            System.out.println(
                                "Corrected: " +
                                isUpdateValid(invalid_updates.get(i), rules)
                            );
                        }
                    }
                }
                if (!breaks_any_rule) {
                    System.out.println("Corrected: " + update.toString());
                    corrected_updates.add(update);
                    corrected_indices.add(i);
                    correct_remain -= 1;
                    System.out.println(
                        "Remaining not corrected: " + correct_remain
                    );
                }
            }
        }

        System.out.println("Invalid updates: " + invalid_update_count);
        System.out.println("Corrected updates: " + corrected_updates.size());
        int verified_correct = 0;
        for (var update : corrected_updates) {
            if (isUpdateValid(update, rules)) {
                verified_correct += 1;
            }
        }
        System.out.println("Verified corrected updates: " + verified_correct);

        int middle_sum = 0;
        for (var valid_update : corrected_updates) {
            int middle_num_idx = valid_update.size() / 2;
            middle_sum += valid_update.get(middle_num_idx);
        }
        return String.valueOf(middle_sum);
    }
}
