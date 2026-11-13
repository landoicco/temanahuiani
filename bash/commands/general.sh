#!/usr/bin/env bash

source commands/java.sh

###############################
# Most frequently used methods
###############################

clean() {

    echo_text_byellow "=== Cleaning Java ==="

    # Destroy all .class files
    rm -rf src/**/target

    # Clean prev builds
    rm -rf src/mods

    # Create module path directory
    mkdir src/mods

    echo_text_green "Cleaned!"
}

# Compiles and packages all Java code into modules
build_and_pack() {

    echo_text_byellow "=== Building Java ==="

    # Compile and jar all Java code
    if ! build_all_java_modules; then
        echo_text_bred "Build failed!"
        return 1
    fi

    echo_text_green "Java modules compiled and packaged!"

}

# Start game
play() {

    echo_text_byellow "=== Running Java ==="

    java \
        --module-path src/mods \
        --module dev.licaza.temanahuiani.demo/dev.licaza.temanahuiani.demo.main.Game  
}

# Show to the user all available commands 
help() {
    echo_text_byellow "=== ALL COMMANDS ==="
    show_long_menu
}

# List all Java sources, skipping compiled output
java_sources() {
    find src test -name "*.java" -not -path "*/target/*" -print0
}

# Compile the tests against the built modules and run them with JUnit.
# Tests run on the classpath (not as modules), so each test can live in the
# same package as the class it tests. Needs a previous build_and_pack.
run_tests() {
    echo_text_byellow "=== Running tests ==="

    if [ ! -f "$JUNIT_JAR" ]; then
        echo_text_bred "JUNIT_JAR is not set, run 'nix develop' first."
        return 1
    fi

    local game_jars
    game_jars=$(ls src/mods/*.jar | tr '\n' ':')

    rm -rf test/target

    javac \
        --class-path "${game_jars}${JUNIT_JAR}" \
        -d test/target \
        $(find test -name "*.java" -not -path "*/target/*") || return 1

    java -jar "$JUNIT_JAR" execute \
        --class-path "${game_jars}test/target" \
        --scan-class-path test/target
}

# Rewrite all Java sources with google-java-format (AOSP style: 4 spaces)
format() {
    echo_text_byellow "=== Formatting Java ==="

    java_sources | xargs -0 google-java-format --aosp --replace

    echo_text_green "Code formatted!"
}

# Fail if any Java source isn't formatted, listing the offending files
lint() {
    echo_text_byellow "=== Checking Java format ==="

    if java_sources | xargs -0 google-java-format --aosp --dry-run --set-exit-if-changed; then
        echo_text_green "All files are formatted!"
    else
        echo_text_bred "The files above aren't formatted, run './init.sh format'."
        return 1
    fi
}

deps() {
    # For sanity, make a fresh build
    build_and_pack

    echo_text_byellow "=== Dependency graph ==="

    echo_text_bold "Modules found:\n\t"
    ls src/mods

    echo
    jdeps -s src/mods/*
}