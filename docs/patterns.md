# 🧠 Patterns

Patterns from Robert Nystrom's
[Game Programming Patterns](https://gameprogrammingpatterns.com/) used in this
project. When a new one is adopted, add it here with where it lives and why.

| Pattern                                                                  | Where                                    | Notes |
| ------------------------------------------------------------------------ | ---------------------------------------- | ----- |
| [Game Loop](https://gameprogrammingpatterns.com/game-loop.html)          | `core.main.Game`                         | Fixed update rate (60 UPS) decoupled from render rate (120 FPS) |
| [Update Method](https://gameprogrammingpatterns.com/update-method.html)  | Managers and scenes                      | Each one exposes `update()`, called once per tick |
| [State](https://gameprogrammingpatterns.com/state.html)                  | `demo.main.GameStates`, `Render`         | A plain enum plus `switch`, not state objects yet |
