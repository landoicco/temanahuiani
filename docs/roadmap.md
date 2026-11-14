# 🗺️ Roadmap

The ordered next steps to finish **Age of Aliens** on the **Temanahuiani**
engine. Each step builds on the previous ones. Details live in the
[game design](game-design.md) and the [backlog](backlog.md); this page only
says what comes next.

## 1. Design docs

- [ ] Game design document and this roadmap.

## 2. Naming

- [ ] Rename the demo package and module from `dev.licaza.temanahuiani.demo`
      to `dev.licaza.ageofaliens`, with an ADR superseding 0005 (which kept
      the code name apart from the title; the demo is now its own game, so it
      leaves the engine's namespace). Do it before resuming PR #7, so that
      branch takes in both renames in one merge.
- [ ] Ship the release app as Age of Aliens: `APP_NAME` in CI, window title,
      main menu.

## 3. Assets

- [ ] Decide how the Kenney sprites go into `src/res`: loose PNGs or
      tilesheets.
- [ ] Import the sprites the GDD uses (terrain, roads, water, towers,
      projectiles, aliens, flag, coins, hearts) and the fonts.
- [ ] Remove the old assets and rewrite `src/res/LICENSES.txt` from scratch.

## 4. Engine: grid and tiles

- [ ] Configurable tile size and grid, logical 1280×768 resolution scaled to
      the window.
- [ ] Resume PR #7 (new tile system) on top of it, with the new terrain.
- [ ] Fix `PathFinding` looping forever on dead ends.

## 5. First playable: v0.1.0

The current mechanics, on the new art, grid and screen size.

- [ ] Aliens with directional sprites and the `hit` pose.
- [ ] Towers with a base and a turret that rotates towards its target.
- [ ] Levels saved in a user data folder, with a default level shipped in the
      jar.
- [ ] First release.

## 6. Full game

Everything in the GDD.

- [ ] The five aliens and the three towers, with upgrades (one class per
      tower).
- [ ] Economy and lives: coins, selling towers, the flag as lives.
- [ ] Map editor: paint terrain and road, place the landing point and the
      flag, reject roads that don't connect them.
- [ ] Built-in levels and the level select screen.
- [ ] Screens: main menu, win, game over, credits for every asset pack.
- [ ] Sounds and music.
- [ ] Tune the numbers by playing.
- [ ] Release v1.0.0.

## Later

Decisions left **[open]** in the GDD, to take once the full game plays well:

- Ice cannon, with effects drawn in code as simple particles.
- Campaign on the Map Pack's world map.
