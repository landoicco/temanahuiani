#!/bin/bash

# # For design compliance, the full name of the module to be build
# # needs to be defined on a variable

DEMO_PATH=src/demo/dev/licaza/temanahuiani/demo
COMMONS_PATH=src/commons/dev/licaza/temanahuiani/engine/commons
TOOLS_PATH=src/tools/dev/licaza/temanahuiani/engine/tools
CORE_PATH=src/core/dev/licaza/temanahuiani/engine/core

DEMO_MODULE=dev.licaza.temanahuiani.demo
COMMONS_MODULE=dev.licaza.temanahuiani.engine.commons
TOOLS_MODULE=dev.licaza.temanahuiani.engine.tools
CORE_MODULE=dev.licaza.temanahuiani.engine.core


######################################################
# All Java-related commands (Compiling and packaging)
######################################################

# # For design compliance, we define a method for each module

# dev.licaza.temanahuiani.demo
build_demo() {

    # Safety clean...
    rm -rf src/demo/target
    rm -f src/mods/${DEMO_MODULE}.jar

    javac \
        --module-path src/mods \
        -d src/demo/target \
        ${DEMO_PATH}/configs/**.java \
        ${DEMO_PATH}/actors/enemies/**.java \
        ${DEMO_PATH}/main/**.java \
        ${DEMO_PATH}/scenes/**.java \
        ${DEMO_PATH}/ui/**.java \
        ${DEMO_PATH}/inputs/**.java \
        src/demo/module-info.java \
        || return 1

    cp -r src/res/** src/demo/target/

    jar \
        -cvfe src/mods/${DEMO_MODULE}.jar \
        ${DEMO_MODULE}.main.Game \
        -C src/demo/target .
}

# dev.licaza.temanahuiani.engine.commons
build_engine_commons() {

    # Safety clean...
    rm -rf src/commons/target
    rm -f src/mods/${COMMONS_MODULE}.jar

    javac \
        --module-path src/mods \
        -d src/commons/target \
        ${COMMONS_PATH}/misc/**.java \
        ${COMMONS_PATH}/events/**.java \
        ${COMMONS_PATH}/actors/**.java \
        ${COMMONS_PATH}/api/**.java \
        ${COMMONS_PATH}/objects/**.java \
        src/commons/module-info.java \
        || return 1

    jar -cvf src/mods/${COMMONS_MODULE}.jar -C src/commons/target .
}

# dev.licaza.temanahuiani.engine.tools
build_engine_tools() {

    # Safety clean...
    rm -rf src/tools/target
    rm -f src/mods/${TOOLS_MODULE}.jar

    javac \
        --module-path src/mods \
        -d src/tools/target \
        ${TOOLS_PATH}/math/**.java \
        ${TOOLS_PATH}/helpers/**.java \
        ${TOOLS_PATH}/gui/**.java \
        src/tools/module-info.java \
        || return 1

    jar -cvf src/mods/${TOOLS_MODULE}.jar -C src/tools/target .
}

build_engine_core() {

    # Safety clean...
    rm -rf src/core/target
    rm -f src/mods/${CORE_MODULE}.jar

    javac \
        --module-path src/mods \
        -d src/core/target \
        ${CORE_PATH}/managers/**.java \
        ${CORE_PATH}/main/**.java \
        src/core/module-info.java \
        || return 1

    jar -cvf src/mods/${CORE_MODULE}.jar -C src/core/target .
}
    

# # Method for properly list the involved java modules to be build

# Each module needs the previous ones, so stop at the first that fails
build_full_engine() {
    build_engine_commons &&
        build_engine_tools &&
        build_engine_core
}

build_all_java_modules() {
    # # Engine modules...
    build_full_engine &&
        # # Demo game module...
        build_demo
}
