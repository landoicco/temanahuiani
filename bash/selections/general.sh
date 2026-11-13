#!/bin/bash

source commands/general.sh
source utils/outputs.sh

set_general_selection() {
    echo_text_bgreen ":::GENERAL TOOLS:::"

    case $1 in
    "clean")
        clean
        ;;
    "play")
        play
        ;;
    "bplay")
        clean
        build_and_pack
        play
        ;;
    "help")
        help
        ;;
    "deps")
        deps
        ;;
    "format")
        format
        ;;
    "lint")
        lint
        ;;
    "test")
        clean
        build_and_pack
        run_tests
        ;;
    *)
        echo_text_bred "ERROR: Unknown selection."
        ;;
    esac   
}
