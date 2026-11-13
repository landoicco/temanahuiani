# 🔨 Build

No Maven, no Gradle: the scripts in `bash/` call the JDK tools directly.

## Layout

```
bash/
├── init.sh        entry point, parses arguments or shows the wizard
├── selections/    maps each argument to a command
├── commands/      the actual work (java.sh holds every javac/jar call)
└── utils/         colored output and menus
```

`init.sh` moves to the repo root before doing anything, so all paths are
relative to it.

## Steps

For each module, in dependency order (`commons` → `tools` → `core` → `demo`):

1. Remove `src/<module>/target` and the previous jar.
2. Compile with `javac --module-path src/mods -d src/<module>/target`,
   so each module sees the jars built before it.
3. Pack the classes into `src/mods/<module name>.jar`. The demo jar also
   gets the contents of `src/res/` (sprites, sounds and fonts) and declares
   `dev.licaza.temanahuiani.demo.main.Game` as its entry point.

Then the game runs with:

```shell
java --module-path src/mods \
     --module dev.licaza.temanahuiani.demo/dev.licaza.temanahuiani.demo.main.Game
```

## Adding a package

New packages must be added by hand to the `javac` call of their module in
`bash/commands/java.sh`, and exported in its `module-info.java` if other
modules use them.

## Tests

Unit tests live in `test/<module>/`, mirroring the packages in `src/`
(see [ADR 0004](adr/0004-unit-tests-with-junit-on-the-classpath.md)).
`bash init.sh test` builds the game, then:

1. Compiles the tests into `test/target` with the module jars and JUnit on
   the classpath.
2. Runs them with the JUnit console launcher (`$JUNIT_JAR`, provided by the
   Nix dev shell).

To test a new package, just add its test class under the same package in
`test/<module>/`.

## Formatting

`bash init.sh format` rewrites every Java file in `src/` and `test/` with
google-java-format, and `bash init.sh lint` fails if any of them isn't
formatted (see [ADR 0003](adr/0003-code-formatting-with-google-java-format.md)).

## CI and releases

`.github/workflows/ci.yml` runs `lint` and `test` in the Nix `ci` shell
(`nix develop .#ci`) on every PR and push to `master`. To publish a release,
push a version tag:

```shell
git tag v0.1.0
git push origin v0.1.0
```

CI then packages the game with `jpackage` on Windows, macOS and Linux, and
creates a GitHub Release with one zip per OS. A manual run (*Actions → CI →
Run workflow*) builds the same zips as artifacts, without a release. See
[ADR 0007](adr/0007-ci-and-releases-with-github-actions.md).

## Generated files

| Path               | Content                     |
| ------------------ | --------------------------- |
| `src/*/target/`    | Compiled classes            |
| `src/mods/`        | Module jars                 |
| `test/target/`     | Compiled tests              |
| `logs/`            | Output of `init.sh log ...` |

All of them are git-ignored.
