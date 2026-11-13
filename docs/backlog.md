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

### New tile system (PR #7, parked)

PR #7 (`subfeature/redo_tilemanager_and_loading_logic`, draft) replaces the
fixed tile list with `SpriteSheet` objects (one per terrain, starting with
`Sand`) and adds a generic toolbar to the engine (`tools.gui.bar`). It is
parked until the new terrain atlas is sourced. To resume it:

- Load the new licensed terrain atlas instead of the missing `map.png`
  (`GROUNDS`).
- Give the new tiles their right types: every `Sand` tile is type `0`
  (`WATER`), and `default_level.txt` is all `0`, so there are no roads for
  enemies and no grass for towers.
- Restore the terrains removed by the PR (grass, water, roads) as sprite
  sheets.
- Merge `master` into it: conflicts in `README.md` and `MediaSource.java`
  (drop `ENEMIES`/`enemies.png`, removed in PR #6), plus the package rename
  if it lands first.
- Credit `favicon.png` in `src/res/LICENSES.txt`.
- Clean up the code (the PR's "TODO: CLEAN CODE" commit).

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
  It could run the same `jpackage` call as CI.
- **Levels are saved in the working directory**: `LoadSave` reads and writes
  `default_level.txt` relative to where the game is launched. A packaged app
  may start somewhere read-only (e.g. `/` for a macOS `.app` opened from
  Finder), so it should use a folder in the user's home instead.
- **`PathFinding` loops forever on dead ends**: if the road has a dead end or
  doesn't reach the end point, `GetRoadDirectionArray` never returns.
- **`create_mods_dir` doesn't exist**: `bash/commands/build.sh` calls it;
  `set_build_selection` creates `src/mods` before, so it only prints an error.
- **Assets licenses**: every asset (sprites, sounds, fonts) must be sourced
  again from scratch, checking its license and crediting it in
  `src/res/LICENSES.txt`. The current file is out of date: it lists
  `Menu-3-1.mp3` but the files are `menu.wav` and `playing.wav`, and the
  source of `playing.wav` is missing.

## 📝 Docs

- Add the dictionary link for the name meaning in the README.
- Add a game design document.
