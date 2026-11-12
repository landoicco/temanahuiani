# 🏗️ Architecture

## Modules

| Module                            | Role                                                         |
| --------------------------------- | ------------------------------------------------------------ |
| `licaza.tdefender.engine.commons` | Shared types: `Enemy`, `Tower`, `Wave`, `Tile`, `Projectile`, `PathPoint`, the `SceneMethods` interface and constants |
| `licaza.tdefender.engine.tools`   | Helpers: GUI buttons, sprite/level loading (`LoadSave`), audio and font loading, image rotation, pathfinding |
| `licaza.tdefender.engine.core`    | The base `Game` window and loop, plus the managers           |
| `licaza.tdefender.demo`           | The demo game built on top of the engine                     |

The engine modules know nothing about the demo. The demo extends engine
classes (`Game`, `EnemyManager`, `TileManager`) and fills in the game-specific
parts.

## Game loop

`core.main.Game` is a `JFrame` that runs the loop on its own thread:

- **Update** at 60 UPS: calls the update callback set by the subclass.
- **Render** at 120 FPS: calls `repaint()`, which ends up in
  `GameScreen.paintComponent()`.

## States and scenes

`demo.main.GameStates` holds the current state (`MENU`, `PLAYING`, `EDIT`,
`SETTINGS`, `GAME_OVER`) and switches the background music when it changes.

Each state has a scene in `demo.scenes`, all extending `GameScene` and
implementing `SceneMethods`:

| State       | Scene      |
| ----------- | ---------- |
| `MENU`      | `Menu`     |
| `PLAYING`   | `Playing`  |
| `EDIT`      | `Editing`  |
| `SETTINGS`  | `Credits`  |
| `GAME_OVER` | `GameOver` |

Every frame, `demo.main.Game` (update) and `Render` (draw) switch on the
current state and delegate to the matching scene. Mouse and keyboard
listeners in `demo.inputs` forward events the same way.

## Managers

Managers live in `core.managers` and each one owns one kind of object,
exposing `update()`, `draw(Graphics)` and `reset()`:

| Manager             | Owns                                                 |
| ------------------- | ---------------------------------------------------- |
| `TileManager`       | Sprite atlas and tile definitions (abstract)         |
| `EnemyManager`      | Enemies on the map, spawning and rewards (abstract)  |
| `TowerManager`      | Placed towers, upgrades and targeting                |
| `ProjectileManager` | Projectiles, hits and explosions                     |
| `WaveManager`       | Waves and the timers between them                    |

Managers talk to each other through callbacks (`Supplier`, `IntConsumer`,
`Runnable`) instead of direct references, so they stay decoupled.

## Levels

Levels are plain text files in the working directory (the repo root when run
through `init.sh`). `default_level.txt` holds 400 tile ids (a 20×20 grid)
followed by the start and end path points. It is created on first run if
missing, and saved from the `Editing` scene.
