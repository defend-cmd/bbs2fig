# bbs2fig

Figura avatars as BBS scene forms. Fabric 1.21.1, for the BBS CML edition + Figura.

On hold for now, and not really finished. The avatar shows up as a form in the BBS palette and renders in a scene, and you can move it around with the normal form transform. What doesn't work yet is Figura's own side of things — Lua scripts and animations don't run inside the scene preview, the model just sits there in a frozen pose. So right now it's only good as a static model, not a live rig.

Might come back to it later.

## Build

Put the bbs and figura jars in `libs/`:

```
libs/bbs-cml-edition-1.10.3-1.21.1.jar
libs/figura-custom-0.1.6-rc.3-mc1.21.1-fabric.jar
```

Then `./gradlew build` (needs JDK 21). Output ends up in `build/libs/`.

## License

MIT
