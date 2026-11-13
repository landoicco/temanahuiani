# 🏗️ Architecture

## Modules

| Module                                   | Role                                                         |
| ---------------------------------------- | ------------------------------------------------------------ |
| `dev.licaza.temanahuiani.engine.commons` | Shared types: `Enemy`, `Tower`, `Wave`, `Tile`, `Projectile`, `PathPoint`, the `SceneMethods` interface and constants |
| `dev.licaza.temanahuiani.engine.tools`   | Helpers: GUI buttons, sprite/level loading (`LoadSave`), audio and font loading, image rotation, pathfinding |
| `dev.licaza.temanahuiani.engine.core`    | The base `Game` window and loop, plus the managers           |
| `dev.licaza.temanahuiani.demo`           | The demo game built on top of the engine                     |

The engine modules know nothing about the demo. The demo extends engine
classes (`Game` and the abstract managers) and fills in the game-specific
parts.

## Game loop

`core.main.Game` is a `JFrame` that runs the loop on its own thread:

- **Update** at 60 UPS: calls the update callback set by the subclass.
- **Render** at 120 FPS: calls `repaint()`, which ends up in
  `GameScreen.paintComponent()`.

## States and scenes

`demo.main.GameStates` holds the current state (`MENU`, `PLAYING`, `EDIT`,
`CREDITS`, `GAME_OVER`) and switches the background music when it changes.

Each state has a scene in `demo.scenes`, all extending `GameScene` and
implementing `SceneMethods`:

| State       | Scene      |
| ----------- | ---------- |
| `MENU`      | `Menu`     |
| `PLAYING`   | `Playing`  |
| `EDIT`      | `Editing`  |
| `CREDITS`   | `Credits`  |
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
| `TowerManager`      | Placed towers, upgrades and targeting (abstract)     |
| `ProjectileManager` | Projectiles, hits and explosions (abstract)          |
| `WaveManager`       | Waves and the timers between them                    |

Managers talk to each other through callbacks (`Supplier`, `IntConsumer`,
`Runnable`) instead of direct references, so they stay decoupled.

The demo implements the abstract managers as nested classes of
`demo.configs.Managers` with the same names (`Managers.TileManager`,
`Managers.EnemyManager`...). They load the game-specific sprites and create
the demo's enemies and tiles.

## Demo configs

`demo.configs` keeps the game-specific values in one place:

| Class         | Holds                                                                  |
| ------------- | ---------------------------------------------------------------------- |
| `Constants`   | Tile types, plus enemy, projectile and tower ids and their stats       |
| `Colors`      | UI color palette                                                       |
| `MediaSource` | Fonts, sounds and sprite atlases, loaded once from the classpath (copied from `src/res`) |
| `Managers`    | The demo implementations of the engine managers                        |
| `UIPositions` | Where each UI element goes on each scene (`Menu`, `Editing`, `Playing`, `Credits`) |

## Levels

Levels are plain text files in the working directory (the repo root when run
through `init.sh`). `default_level.txt` holds 400 tile ids (a 20×20 grid)
followed by the start and end path points. It is created on first run if
missing, and saved from the `Editing` scene.
