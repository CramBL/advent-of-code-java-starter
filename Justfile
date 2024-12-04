GRADLE := "./gradlew"

@_default:
    just --list --no-aliases

run DAY="1" PART="1":
    {{GRADLE}} run --args="{{DAY}} {{PART}}"

test-all:
    {{GRADLE}} test

[linux]
test DAY="1" PART="1":
    #!/usr/bin/env bash
    zero_padded_day=$(printf %02d {{DAY}})
    {{GRADLE}} test --tests "Day${zero_padded_day}Test.testPart{{PART}}" --info

[windows]
test DAY="01" PART="1":
    {{GRADLE}} test --tests "Day{{DAY}}Test.testPart{{PART}}" --info