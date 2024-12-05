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
gen-day DAY:
    #!/usr/bin/env bash
    set -euo pipefail
    ls -l src/main
