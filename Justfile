@_default:
    just --list --no-aliases

run DAY="1" PART="1":
    ./gradlew run --args="{{DAY}} {{PART}}"

test-all:
    ./gradlew test

test DAY="01" PART="1":
    ./gradlew test --tests "Day{{DAY}}Test.testPart{{PART}}" --info