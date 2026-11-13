# 0002. Nix flake for the dev environment

**Status:** Accepted

## Context

The project needs a JDK plus a few development tools (gh, the formatter, the
test runner), and the same versions should be used on every machine.

## Decision

`flake.nix` defines a dev shell (`nix develop`) with only the tools the
project needs. `flake.lock` pins their versions. Tools missing from nixpkgs,
like the JUnit jar, are fetched in the flake with a fixed hash and exposed
through an environment variable (`JUNIT_JAR`).

## Consequences

- One command gives the right JDK and tools, with no global installs.
- Contributors without Nix install the tools by hand (see the README).
- Nix doesn't run on Windows, so CI can't rely on the flake on every runner.
  Wherever CI installs tools itself, their versions must be kept in sync with
  the flake by hand.
