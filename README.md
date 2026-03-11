# Dusk Lights (Multiloader)

This repository is now set up as a **multiloader Minecraft mod project** targeting:

- **Fabric** (`/fabric`)
- **Forge** (`/forge`)
- Shared code in **Common** (`/common`)

## Project layout

- `common/` - shared Java code and resources used by all loaders.
- `fabric/` - Fabric loader bootstrap + Fabric metadata.
- `forge/` - Forge loader bootstrap + `mods.toml`.

## Useful Gradle tasks

From the repository root:

- `./gradlew :fabric:runClient` - run Fabric client in dev.
- `./gradlew :forge:runClient` - run Forge client in dev.
- `./gradlew build` - build all subprojects.

## Configuration

Project and mod metadata (version, mod id, loader versions, etc.) is configured in `gradle.properties`.
