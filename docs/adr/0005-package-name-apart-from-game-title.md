# 0005. Package name apart from the game title

**Status:** Accepted

## Context

Packages were named `licaza.tdefender`, which matched neither the author's
domain (licaza.dev) nor the project name. The game's display title is still
open: *Temanahuiani* is original, but long and hard to pronounce for
non-Spanish speakers.

## Decision

- Java packages and modules use `dev.licaza.temanahuiani` (reverse domain +
  project name), e.g. `dev.licaza.temanahuiani.engine.core`.
- The game's display title (window title, README heading, releases) is a
  separate decision and doesn't have to match the package name.

## Consequences

- The package name is unique and stable: changing the title never requires
  renaming code.
- The code name and the title may differ, which is common and only shows in
  the source.
