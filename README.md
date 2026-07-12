# BBS2Fig

A Fabric 1.21.1 addon that bridges [Figura](https://modrinth.com/mod/figura) and [BBS (CML Edition)](https://discord.gg/MAHVQBSce6), exposing Figura avatars (rigs) as native BBS scene forms. The goal is to let Figura rigs — including their Lua scripts and animations — be placed, moved, transformed and keyframed inside BBS scenes just like ordinary BBS rigs.

## Status

Early development.

- **Phase 1 (current):** register a `figura` form type in BBS via the `bbs-addon` entrypoint, with an avatar selector (local `figura/avatars/*` folder or the currently equipped avatar). Renderer is a stub.
- **Phase 2:** render the selected Figura avatar in the scene through the BBS render pipeline, ticking its Lua runtime with an isolated stub host.
- **Phase 3:** expose Figura model parts as BBS bones for attachment and per-bone control.

## Requirements

- Minecraft 1.21.1, Fabric
- Fabric API
- BBS CML Edition `1.10.3-1.21.1`
- Figura (custom build `0.1.6-rc.3-mc1.21.1`)

## Building

This addon compiles against BBS and Figura. Place the two mod jars into `libs/` before building (they are not redistributed here):

```
libs/bbs-cml-edition-1.10.3-1.21.1.jar
libs/figura-custom-0.1.6-rc.3-mc1.21.1-fabric.jar
```

Then:

```
./gradlew build
```

The built jar lands in `build/libs/`.

## License

MIT
