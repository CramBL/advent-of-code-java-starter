GRADLE := "./gradlew"

@_default:
    just --list --no-aliases

run DAY="1" PART="1":
    {{GRADLE}} run --args="{{DAY}} {{PART}}"

test-all:
    {{GRADLE}} test

test DAY="01" PART="1":
    {{GRADLE}} test --tests "Day{{DAY}}Test.testPart{{PART}}" --info