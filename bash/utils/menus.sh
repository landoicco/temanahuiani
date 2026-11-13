#!/usr/bin/env bash

###################################################
# Utility methods for showing a "menu" to the user
###################################################

# Show script headline
show_headline() {
echo_text_bgreen \
"
================================
======== Builder Script ========
================================
"
}

# Show quick menu
show_quick_menu() {
    echo_text_bold "Quick options: "
    echo_text_yellow "clean, build, play, bplay, test"
    echo -n "For more options, type "
    echo_text_yellow "help"
    echo
}

# Show long menu
show_long_menu() {
    # deps
    echo -ne "\tList modules and show dependency graphs - "
    echo_text_yellow "deps"

    # clean
    echo -ne "\tClean all previous builds - "
    echo_text_yellow "clean"

    # build
    echo -ne "\tCompile code and pack modules - "
    echo_text_yellow "build"

    # bplay
    echo -ne "\tBuild source code and play game - "
    echo_text_yellow "bplay"

    # format
    echo -ne "\tFormat Java code with google-java-format - "
    echo_text_yellow "format"

    # lint
    echo -ne "\tCheck Java code is formatted - "
    echo_text_yellow "lint"

    # test
    echo -ne "\tBuild and run unit tests - "
    echo_text_yellow "test"

    # help
    echo -ne "\tList all available commands - "
    echo_text_yellow "help"

    # play
    echo -ne "\tPlay game - "
    echo_text_yellow "play"
}