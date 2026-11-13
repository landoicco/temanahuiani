# 🧭 Architecture decision records

Short records of the technical decisions that shape the project: what was
decided, why, and what it costs. Only decisions that are hard to reverse or
that someone would otherwise question get a record.

| ADR                                                          | Decision                                         | Status   |
| ------------------------------------------------------------ | ------------------------------------------------ | -------- |
| [0001](0001-vanilla-java-without-build-tools.md)             | Vanilla Java: no build tools, frameworks or libraries | Accepted |
| [0002](0002-nix-flake-dev-environment.md)                    | Nix flake for the dev environment                | Accepted |
| [0003](0003-code-formatting-with-google-java-format.md)      | google-java-format (AOSP style) for formatting   | Accepted |
| [0004](0004-unit-tests-with-junit-on-the-classpath.md)       | JUnit tests on the classpath, outside `src/`     | Accepted |
| [0005](0005-package-name-apart-from-game-title.md)           | `dev.licaza.temanahuiani` packages, apart from the game title | Accepted |

## Writing a new one

Copy the format of an existing record: **Context**, **Decision** and
**Consequences**, a few lines each. Number it with the next free number and
add it to the table. Records aren't edited once accepted; a new record
supersedes the old one, and the old one's status changes to
`Superseded by NNNN`.
