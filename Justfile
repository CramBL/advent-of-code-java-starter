@_default:
    just --list --no-aliases

run DAY PART:
    ./gradlew run --args="{{DAY}} {{PART}}"

test:
    ./gradlew test --info