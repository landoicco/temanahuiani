# 0001. Vanilla Java without build tools

**Status:** Accepted

## Context

The project is a learning exercise: the goal is to understand how Java works
at its roots, and to build a reusable tower defense engine on top of the JDK
alone.

## Decision

- No build tools (Maven, Gradle), frameworks or libraries in the game. The
  JDK tools (`javac`, `jar`, `java`, `jdeps`) are called directly from the
  bash scripts in `bash/`.
- The code is split into Java modules (JPMS): `engine.commons`,
  `engine.tools`, `engine.core` and `demo`, built in dependency order.
- Graphics, input and audio use only the JDK (`java.desktop`: Swing, AWT,
  Java Sound).

Development tools that never ship with the game (formatter, test runner) are
allowed, each one with its own ADR.

## Consequences

- Nothing to download to build or run the game, beyond a JDK.
- Everything a build tool would do is done by hand: new packages must be
  added to `bash/commands/java.sh`, and there's no dependency management.
- Tools built for Maven or Gradle (e.g. Spotless) can't be used; only those
  with a standalone CLI.
