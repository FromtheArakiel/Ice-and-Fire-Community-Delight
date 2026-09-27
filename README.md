# Ice and Fire Community Delight

An addon for [Farmer's Delight](https://www.curseforge.com/minecraft/mc-mods/farmers-delight) that
cooks up the creatures of [Ice and Fire](https://www.curseforge.com/minecraft/mc-mods/ice-and-fire-dragons)
into food, knives and pastries.

The mod is a 1.21.1 port of the 1.20.1 project *Ice and Fire Delight* by **Donne431**, code
by **FromtheArakiel**, rebuilt as an Architectury project so one code base ships for **Fabric** and
**NeoForge**.

## Requirements

| | |
|---|---|
| Minecraft | 1.21.1 |
| Java | 21 |
| Loader | Fabric Loader 0.19+ with Fabric API, or NeoForge 21.1+ |
| Required | [Architectury API](https://www.curseforge.com/minecraft/mc-mods/architectury-api) 13.0.11+, [Farmer's Delight](https://www.curseforge.com/minecraft/mc-mods/farmers-delight), [Ice and Fire](https://www.curseforge.com/minecraft/mc-mods/ice-and-fire-dragons) (the Community Edition build is used on Fabric) |
| Optional | [JEI](https://www.curseforge.com/minecraft/mc-mods/jei) (item descriptions), [Patchouli](https://www.curseforge.com/minecraft/mc-mods/patchouli) (the in game cookbook) |

## Integrations

JEI and Patchouli are integrations, never hard dependencies - the mod loads and plays without them,
and both are only coupled through a single dedicated class:

| Integration | File |
|---|---|
| JEI ingredient descriptions | [`JeiIntegration`](common/src/main/java/dev/arakiel/iceandfirecommunitydelight/integration/JeiIntegration.java) |
| Patchouli cookbook handout | [`PatchouliIntegration`](common/src/main/java/dev/arakiel/iceandfirecommunitydelight/integration/PatchouliIntegration.java) |

The cookbook content itself is plain data under
`common/src/main/resources/data/iceandfirecommunitydelight/patchouli_books/`, and its recipe carries
both a `fabric:load_conditions` and a `neoforge:conditions` entry so it is simply skipped when
Patchouli is missing.

## Content

Around 70 food items (raw meats, sausages, ramen, cocktails, four kinds of pie with slices, dragon
hearts on potatoes, resin jellies...) built from Ice and Fire creatures: cyclops, troll, hydra, sea
serpent, myrmex and all four dragon colours.

Eight mob effects drive the combat side of the mod - `fire_aspect`, `ice_aspect`, `lightning_strike`,
`poison_resistance`, `warming`, `dragon_flight`, `dragons_might` and `center_of_weakness` - and the
knife line (`silver_knife`, `hydra_fang_knife`, `sea_serpent_fang_knife`, the three dragonsteel
knives, `dragonbone_knife` and the `phantom_knife`) applies them on hit. Ice and Fire mobs also drop
their meat through the mod's loot tables, village trades and advancements.

## Building

The four Ice and Fire / Farmer's Delight / Patchouli / JEI jars the build compiles against are kept
locally in `libs/`, so no remote repository is needed for them.

```bash
./gradlew build
```

The finished jars land in `fabric/build/libs/` and `neoforge/build/libs/`. NightConfig (the config
library) is nested into the built jar - as a Fabric nested jar and through NeoForge's JarJar - so
players do not have to install it. Architectury API is *not* bundled and stays a normal dependency.

The project layout follows the usual Architectury split:

| Module | Contents |
|---|---|
| `common/` | Everything shared: registries, effects, config, loot, data and assets |
| `fabric/` | Fabric entry point, trades and loot table hooks |
| `neoforge/` | NeoForge entry point, global loot modifier and trade events |

## Configuration

A single TOML file is written to `config/iceandfirecommunitydelight-common.toml` on first launch and
is shared by both loaders. It controls whether the cookbook is handed out on the first join and how
often the "too much power" explosion may trigger while eating the dragon special pie, its slice and
the dragon special sausage.

## Credits

* Original 1.20.1 mod, art and design: **Donne431**
* 1.21.1 Architectury port: **FromtheArakiel**
* Built on Farmer's Delight by vectorwing and Ice and Fire by Alexthe666

## License

PolyForm-Shield-1.0.0, as declared in `fabric.mod.json` and `neoforge.mods.toml`.


