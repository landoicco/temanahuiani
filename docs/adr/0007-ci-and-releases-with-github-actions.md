# 0007. CI and releases with GitHub Actions

**Status:** Accepted

## Context

Every change should be checked automatically (format, build, tests), and
players should be able to run the game without a JDK or the `bash/` scripts,
on Windows, macOS and Linux.

## Decision

A single workflow, `.github/workflows/ci.yml`:

- **Every PR and push to `master`**: lint, build and test on Linux, in the
  Nix `ci` shell ([ADR 0006](0006-nix-dev-shells-for-development-and-ci.md)).
- **`v*` tags (and manual runs)**: package the game on Windows, macOS and
  Linux runners. The jars from the check job are reused; each runner only
  runs `jpackage`, with a Temurin JDK of the same major version as the flake.
- **`v*` tags**: publish a GitHub Release with the three packages.

Packages are `jpackage --type app-image` folders zipped per OS: an `.exe` on
Windows, a `.app` on macOS and a binary on Linux, each bundling its own Java
runtime. No installers.

## Consequences

- Players download a zip and run it: no JDK, no install.
- No extra tools on the runners: installers would need WiX on Windows.
- The macOS app isn't signed (that needs a paid Apple Developer account), so
  Gatekeeper warns the first time it's opened.
- `macos-latest` runners are Apple Silicon: the macOS package doesn't run on
  Intel Macs.
- `jpackage` can't cross-build, so packaging always needs one runner per OS.
