GRADLE := "./gradlew"

alias t := test
alias r := run

@_default:
    just --list --no-aliases

run DAY="1" PART="1":
    {{GRADLE}} run --args="{{DAY}} {{PART}}"

test-all *ARGS:
    {{GRADLE}} test {{ARGS}}

[linux]
test DAY="1" PART="1":
    #!/usr/bin/env bash
    zero_padded_day=$(printf %02d {{DAY}})
    {{GRADLE}} test --tests "Day${zero_padded_day}Test.testPart{{PART}}" --info

[windows]
test DAY="01" PART="1":
    {{GRADLE}} test --tests "Day{{DAY}}Test.testPart{{PART}}" --info

# generate boilerplate for the given day. Assumes day 01 boilerplate existss

DAY_DIR := "src/main/java/aoc"
TEST_DIR := "src/test/java/aoc"
BOILER_PLATE_DAY := join(DAY_DIR, "day00/Day00.java")
BOILER_PLATE_TEST := join(TEST_DIR, "day00/Day00Test.java")

[linux]
gen-day DAY:
    #!/usr/bin/env bash
    set -euo pipefail
    zero_padded_day=$(printf %02d {{DAY}})
    test_fname="Day${zero_padded_day}Test.java"
    new_test_dir="{{TEST_DIR}}/day${zero_padded_day}"
    mkdir -p ${new_test_dir}
    new_test_fpath="${new_test_dir}/${test_fname}"
    cp "{{BOILER_PLATE_TEST}}" "${new_test_fpath}"
    sed -i "s/00/${zero_padded_day}/g" "${new_test_fpath}"
    new_day_dir="{{DAY_DIR}}/day${zero_padded_day}"
    mkdir -p "${new_day_dir}"
    day_fname="Day${zero_padded_day}.java"
    new_day_fpath="${new_day_dir}/${day_fname}"
    cp "{{BOILER_PLATE_DAY}}" "${new_day_fpath}"
    sed -i "s/00/${zero_padded_day}/g" "${new_day_fpath}"
