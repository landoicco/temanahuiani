# 🎮 Game design

> **Status:** draft. Items marked **[open]** are still to be decided.

This document describes **Age of Aliens**, the demo game.

- **Temanahuiani** is the engine: the repo, the `engine.*` modules and the
  build scripts. It knows nothing about aliens.
- **Age of Aliens** is the demo game built on it, in the `demo` module.

The title is a playful nod to *Age of Empires*: same idea of managing
resources to beat the enemy, but the enemy is a horde of cartoon aliens.

## Concept

**"The aliens have landed. Defend yourself."**

A classic tower defense with a simple, well-developed theme: cartoon aliens
in space helmets walk down the road towards your flag. Build towers along the
road to stop them. If enough aliens reach the flag, it falls and you lose.

Pillars:

- **Simple and readable**: one road, clear enemy types, few towers that each
  do one thing well.
- **Cartoon tone**: light and colorful, no gore. Aliens get knocked out, not
  killed.
- **Make your own levels**: the map editor is part of the game, not a dev
  tool.

## Core loop

1. Before a wave, place or upgrade towers with your coins.
2. A wave of aliens walks the road from the landing point to the flag.
3. Towers shoot aliens in range; each knocked-out alien gives coins.
4. Every alien that reaches the flag costs one life.
5. Survive all waves to win. At zero lives the flag falls: game over.

## Map

- A grid of 20×10 terrain tiles (see [Screen size](#screen-size)) with one
  road from the **landing point** (start) to the **flag** (end).
- Aliens only walk on the road; towers are only built on buildable terrain.
- Terrain types: buildable ground (grass, dirt, sand, stone), road, and
  blocking terrain (water).

## Aliens (enemies)

Five aliens, one per helmet color. They face the direction they walk: side
view (flipped for left/right), front when walking down, back when walking up.
The `hit` pose flashes when they take damage.

| Alien  | Role          | Speed  | Health | Coins |
| ------ | ------------- | ------ | ------ | ----- |
| Green  | Basic         | Medium | Medium | Low   |
| Blue   | Runner        | Fast   | Low    | Low   |
| Yellow | Swarm         | Medium | Low    | Very low, comes in large groups |
| Pink   | Tank          | Slow   | High   | Medium |
| Beige  | Boss          | Slow   | Very high | High, last wave only |

Exact numbers **[open]**, to be tuned by playing.

## Towers

Built from the Tower Defense pack: a base plus a turret that rotates towards
its target.

| Tower         | Shoots   | Strength                 | Weakness              |
| ------------- | -------- | ------------------------ | --------------------- |
| Machine gun   | Bullets  | Fast fire, good vs runners | Low damage          |
| Cannon        | Shells   | Area damage, good vs swarms | Slow fire          |
| Rocket launcher | Rockets | Long range, high damage, good vs tanks | Expensive |

- Each tower can be upgraded (the pack has 1- and 2-rocket launchers and
  several turret variants for upgrade levels).
- **Ice cannon [open]**: the current game has a slowing tower (Wizard) with
  no matching art in the packs. Idea: replace it with a cannon that shoots
  ice blocks and slows down the aliens it hits. Its effects (ice shards,
  frost on slowed aliens) could be drawn in code with very simple particles
  instead of sprites. To be decided later.

## Economy and lives

- **Coins**: start amount per level; earned by knocking out aliens. Spent on
  building and upgrading towers. Selling a tower returns part of its cost.
- **Lives**: shown as the flag's health. The flag waves while lives are left
  and falls (`flag_down`) on game over.

## Levels

- **Built-in levels**: a set of levels made with the editor and shipped with
  the game.
- **Campaign [open]**: the Map Pack's world map (islands per biome, numbered
  level markers, yellow paths) could become a level select screen, unlocking
  levels in order. Biomes would be limited to the terrains the Tower Defense
  pack has for levels: grass, dirt, sand and stone.

## Map editor

A main menu option. The player:

1. Paints terrain and road tiles on the grid.
2. Places the landing point and the flag.
3. Saves the level and plays it.

The editor must reject levels whose road doesn't connect the landing point
to the flag (today `PathFinding` loops forever on them).

## Screens

Main menu (Play, Editor, Credits, Quit) → level select → level (map + action
bar) → win or game over. Credits list every asset pack.

## Art and audio

| Use                  | Source                                          |
| -------------------- | ----------------------------------------------- |
| Level terrain, roads, decoration | Kenney *Tower Defense (top-down)*   |
| Water                | Kenney *Map Pack*                               |
| Towers, projectiles, explosions | Kenney *Tower Defense (top-down)*    |
| Aliens               | Kenney *Platformer Pack Remastered* (players)   |
| Flag, coins, hearts  | Kenney *Platformer Pack Remastered* (items, HUD) |
| World map **[open]** | Kenney *Map Pack*                               |
| Fonts                | Patrick Hand and Caveat Brush (Google Fonts, OFL) |
| Sounds and music **[open]** | To be sourced (CC0 preferred, WAV)       |

All Kenney packs are CC0.

- Sprites are used at their native 64×64 px, so they stay sharp (at 32 px
  details like the aliens' faces get lost).
- Aliens are drawn larger than a tile (about 130×190 px) and will be scaled
  down to fit one tile.

## Screen size

- **Tiles**: 64×64 px.
- **Map**: 20×10 tiles (1280×640 px), wider than tall.
- **Action bar**: 128 px below the map.
- **Logical resolution**: 1280×768. The game always draws at this size, and
  the engine scales it to fit the window: down on smaller screens, up when
  maximized or in fullscreen. Mouse input is mapped back with the same scale.

## Out of scope (for now)

- Multiplayer, online level sharing.
- Story, dialogues or cutscenes beyond the premise.
- Mobile or web versions.
