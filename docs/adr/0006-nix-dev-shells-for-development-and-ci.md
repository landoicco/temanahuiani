# 0006. Nix dev shells for development and CI

**Status:** Accepted. Supersedes [0002](0002-nix-flake-dev-environment.md).

## Context

[ADR 0002](0002-nix-flake-dev-environment.md) gave the project a single dev
shell. CI needs the same JDK, formatter and JUnit, but not the local-only
tools (gh). Installing them separately in CI would mean keeping two sets of
versions in sync by hand.

## Decision

`flake.nix` defines two dev shells, with versions pinned by `flake.lock`:

- `ci` (`nix develop .#ci`): what's needed to build, lint and test: JDK,
  google-java-format and JUnit (`JUNIT_JAR`).
- `default` (`nix develop`): everything in `ci` (through `inputsFrom`), plus
  tools for local development like gh.

Tools missing from nixpkgs, like the JUnit jar, are fetched in the flake with
a fixed hash. CI runs lint, build and tests inside the `ci` shell.

## Consequences

- CI and local development use exactly the same tool versions; updating
  `flake.lock` updates both.
- Nix doesn't run on Windows, so the packaging jobs install a JDK with the
  same major version instead (see [ADR 0007](0007-ci-and-releases-with-github-actions.md)).
- Contributors without Nix install the tools by hand (see the README).
- No shell just to play: players use the packaged releases instead.
