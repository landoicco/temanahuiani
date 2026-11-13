# 🏰 Temanahuiani

![Java](https://img.shields.io/badge/Java-vanilla-ED8B00?logo=openjdk&logoColor=white)
![Nix](https://img.shields.io/badge/Nix-flake-5277C3?logo=nixos&logoColor=white)
![Bash](https://img.shields.io/badge/build-bash-4EAA25?logo=gnubash&logoColor=white)
![No frameworks](https://img.shields.io/badge/frameworks-none-lightgrey)
![No build tools](https://img.shields.io/badge/build%20tools-none-lightgrey)

A tower defense engine and demo game written in plain Java: no frameworks,
no build tools, no libraries.

## 🪶 About the name

**Temanahuiani** is a Nahuatl word meaning *defender* or *protector*: the one
who defends others (*te-* "people", *manahuia* "to defend", *-ni* "one who
does"). A fitting name for a tower defense game.

<!-- TODO: Add link to the dictionary entry -->

## 🎯 Goals

1. A fun learning project to understand how Java works at its roots, in the
   most "pure" and "vanilla" way.
2. A reusable engine to build other games of this genre.

## 🧰 Requirements

The only requirement is a JDK. The repo ships a [Nix](https://nixos.org/) flake
with a dev shell that provides it:

```shell
nix develop
```

Without Nix, any recent JDK with `java`, `javac`, `jar` and `jdeps` on the
`PATH` will do. To format or lint the code you also need
[google-java-format](https://github.com/google/google-java-format) on the
`PATH`.

## 🎮 How to play?

The easiest way: download the zip for your OS from the
[Releases](https://github.com/landoicco/temanahuiani/releases) page, unzip it
and run the `Temanahuiani` app. It bundles its own Java, nothing to install.

To build it from source, run the commands from the `bash/` directory:

```shell
cd bash/
bash init.sh bplay
```

This cleans previous builds, compiles the *engine* modules (commons, tools and
core) and the *demo* module, packs them into jars and starts the game.

Run `bash init.sh` with no arguments for an interactive wizard.

> 💡 Use `bash init.sh` instead of `./init.sh` or `sh init.sh`: the scripts rely
> on bash features and `/bin/bash` doesn't exist on every system (e.g. NixOS).

## 🛠️ Commands

| Command                       | Description                                   |
| ----------------------------- | --------------------------------------------- |
| `bash init.sh bplay`          | Clean, build everything and play              |
| `bash init.sh play`           | Play using the last build                     |
| `bash init.sh clean`          | Remove all previous builds                    |
| `bash init.sh deps`           | Fresh build, list modules and their deps      |
| `bash init.sh format`         | Format all Java code (google-java-format, AOSP style) |
| `bash init.sh lint`           | Fail if any Java file isn't formatted         |
| `bash init.sh test`           | Clean, build everything and run the unit tests in `test/` |
| `bash init.sh help`           | List the available commands                   |
| `bash init.sh build <module>` | Build `all`, `engine`, `demo`, `commons`, `tools` or `core` |
| `bash init.sh log <command>`  | Run `build`, `bplay` or `play` writing output to `logs/`; `clear` removes them |

## 🧩 Modules

The project is split into [Java modules](https://openjdk.org/projects/jigsaw/),
compiled in dependency order:

```
dev.licaza.temanahuiani.engine.commons    actors, waves, tiles, projectiles, constants
        ▲
dev.licaza.temanahuiani.engine.tools      GUI widgets, resource loading, pathfinding
        ▲
dev.licaza.temanahuiani.engine.core       base game loop and managers
        ▲
dev.licaza.temanahuiani.demo              the demo game: scenes, enemies, UI, assets
```

Each module only requires `java.desktop` from the JDK, plus the engine
modules above it.

## 🐚 What about `bash/`?

Well, Maven/Gradle is for the weak... right?

The scripts in `bash/` work like a hand-made *Maven*: they call `javac` and
`jar` for each module and put the results in `src/mods`, which is then used as
the module path to run the game. Take a look at them, they are full of long
commands worth reading.

Want to be sure there are no external dependencies? Run:

```shell
bash init.sh deps
```

## 📚 Docs

Architecture, build internals and the game programming patterns in use are
documented in [`docs/`](docs/README.md).

## 🙏 Credits

- The roots of this project are based on the great work of
  [Kaarin Gaming](https://www.youtube.com/watch?v=kclnyiXmY7Q&list=PL4rzdwizLaxb0-TajNIp5DOoT_PAxhx0T).
- Sprites, sounds and fonts belong to their authors, listed in
  [`src/res/LICENSES.txt`](src/res/LICENSES.txt).
