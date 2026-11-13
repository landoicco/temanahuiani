# 0003. Code formatting with google-java-format

**Status:** Accepted

## Context

The code had no consistent style, and CI should be able to check it. The
usual choice, Spotless, is a Maven/Gradle plugin, which
[ADR 0001](0001-vanilla-java-without-build-tools.md) rules out.

## Decision

Use [google-java-format](https://github.com/google/google-java-format), a
standalone CLI, in AOSP style (4-space indent, like the code already had).

- `bash init.sh format` rewrites the Java files in `src/` and `test/`.
- `bash init.sh lint` fails if any of them isn't formatted; CI runs it.

## Consequences

- No style discussions or configuration: the formatter decides.
- It only checks formatting, not bad practices (unused code, naming...).
  Checkstyle could be added later for that.
- Comments between imports aren't allowed; the formatter fails on them.
