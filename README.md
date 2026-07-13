# BBS2Fig

> ⚠️ **UNFINISHED — PAUSED / ON HOLD.** This is an incomplete work-in-progress and is not being actively developed right now. It renders a Figura avatar inside a BBS scene, but Figura's own Lua scripts and animations do **not** come alive in the scene preview (see "Where it stands" below). Use at your own risk; expect rough edges and no support.

A Fabric 1.21.1 addon that bridges [Figura](https://modrinth.com/mod/figura) and [BBS (CML Edition)](https://discord.gg/MAHVQBSce6), exposing Figura avatars (rigs) as native BBS scene forms. The goal is to let Figura rigs — including their Lua scripts and animations — be placed, moved, transformed and keyframed inside BBS scenes just like ordinary BBS rigs.

## Where it stands

**Works:**
- A `figura` form type is registered in BBS (`bbs-addon` entrypoint) and appears in the form palette (via an `ExtraFormSection` mixin).
- The equipped Figura avatar renders inside a BBS scene through BBS's own vertex consumer provider, and can be positioned/transformed with the BBS form transform.

**Blocked / not working:**
- Figura's Lua scripts (e.g. eye tracking) and animations stay **frozen** in the scene preview. Root cause: Figura's behaviour is driven by a live entity's state (look direction, movement) and real tick time, but the BBS dashboard preview renders the form against a stub/frozen context rather than a live entity, so there is no live input to drive scripted behaviour. Binding the avatar to BBS's real `ActorEntity` only applies to in-world/exported playback, not the dashboard preview.

**Possible directions (not implemented):**
- **A.** Expose Figura model parts as BBS bones so parts can be moved/keyframed directly in BBS ("like a normal BBS rig").
- **B.** Bridge scene state into Figura's Lua entity API (look direction from the BBS camera/target, position from the form transform, tick from the scene playhead) so scripts come alive — a large, uncertain R&D effort.
- **C.** Accept BBS-transform-driven posing without live Figura scripts.

No direction has been chosen; development is paused here.

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
