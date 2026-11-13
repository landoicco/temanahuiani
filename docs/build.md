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

## Generated files

| Path               | Content                     |
| ------------------ | --------------------------- |
| `src/*/target/`    | Compiled classes            |
| `src/mods/`        | Module jars                 |
| `logs/`            | Output of `init.sh log ...` |

All of them are git-ignored.
