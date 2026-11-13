# 0004. Unit tests with JUnit on the classpath

**Status:** Accepted

## Context

The engine has pure logic (pathfinding, matrix helpers, level loading and
saving) that is easy to break and worth testing. Scenes and rendering are
Swing code, expensive to test for what they'd catch.

## Decision

- Write tests with JUnit Jupiter, using the standalone console launcher jar.
  It's the Java standard, and it's a single jar used only to run tests, never
  shipped with the game ([ADR 0001](0001-vanilla-java-without-build-tools.md)).
- Tests live in `test/<module>/`, mirroring the packages in `src/`, so they
  never end up in the module jars.
- Tests are compiled and run on the classpath, with the module jars as plain
  jars, instead of as modules. This way a test can sit in the same package as
  the class it tests without patching modules.

## Consequences

- Tests don't check module boundaries (`exports`, `requires`); the regular
  build does.
- Unit tests cover logic only. Scenes, UI and managers that draw need a
  refactor to separate logic from rendering before they can be tested.
- JUnit isn't in nixpkgs: the flake fetches a pinned version from Maven
  Central ([ADR 0002](0002-nix-flake-dev-environment.md)).
