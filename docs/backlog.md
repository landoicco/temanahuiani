# 📋 Backlog

Ideas and known issues to revisit. Remove an entry once it's done or discarded.

## 💡 Ideas

### One class per tower

Replace the `int` constants and `switch` lookups in `demo.configs.Constants.Towers`
with a class per tower (`Archer`, `Cannon`, `Wizard`) that defines its own
defaults (cost, cooldown, range, damage). Solves the TODOs in
`demo/configs/Constants.java` and `TowerManager.java` (`TowerSpecs`).

A base `Tower` class takes the defaults through its constructor, and each
subclass passes its own `static final` values, e.g. `Cannon` with a range of
`200` and a damage of `3`.

### UI icons

Use an icon spritesheet (play, pause, mute, settings, home, arrows...) for
settings, pause and mute buttons. Kenney's *Game Icons* pack is a good
candidate.

### Version label on the menu

Show the game version in a corner of the menu, e.g. `v0.0.1` at the bottom
right.

## 🐛 Known issues

- **Game loop busy-waits**: `core.main.Game` loops with `while (true)` and no
  sleep, keeping one CPU core at 100%.
- **`SETTINGS` renders `Credits`**: the state wasn't renamed when the settings
  scene became the credits scene (see TODO in `MyMouseListener`).
- **Dead code**: `LoadSave.CreateFile()` creates a `test.txt` and seems unused.
- **`install` command**: listed in the `init.sh` menu but not implemented.
- **Assets licenses**: every asset (sprites, sounds, fonts) must be sourced
  again from scratch, checking its license and crediting it in
  `src/res/LICENSES.txt`. The current file is out of date: it lists
  `Menu-3-1.mp3` but the files are `menu.wav` and `playing.wav`, and the
  source of `playing.wav` is missing.

## 📝 Docs

- Add the dictionary link for the name meaning in the README.
- Add a game design document.
