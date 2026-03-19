# HardModeEasyDamage

[![GitHub release](https://img.shields.io/github/v/release/enzomescall/paper-hard-mode-easy-damage?display_name=tag)](https://github.com/enzomescall/paper-hard-mode-easy-damage/releases)
[![GitHub downloads](https://img.shields.io/github/downloads/enzomescall/paper-hard-mode-easy-damage/total)](https://github.com/enzomescall/paper-hard-mode-easy-damage/releases)
[![Paper](https://img.shields.io/badge/platform-Paper-ffffff?logo=minecraft&logoColor=black)](https://papermc.io/)
[![Java 21](https://img.shields.io/badge/java-21-orange)](https://adoptium.net/)

A tiny Paper plugin for one specific job:

- keep the server on **Hard** difficulty
- reduce **hostile mob damage against players**
- keep the rest of **Hard-mode mechanics** intact

This is meant for servers that want Hard-mode behavior without full Hard-mode combat damage.

## Download

- Latest release: [GitHub Releases](https://github.com/enzomescall/paper-hard-mode-easy-damage/releases)
- Direct download for `v0.1.0`: [paper-hard-mode-easy-damage-0.1.0.jar](https://github.com/enzomescall/paper-hard-mode-easy-damage/releases/download/v0.1.0/paper-hard-mode-easy-damage-0.1.0.jar)

## What it does

When a hostile mob, or a projectile fired by one, damages a player, the plugin applies a configurable multiplier to the final damage.

Because the server itself still stays on **Hard**, other Hard-only behavior remains in place, including things like:

- villagers converting instead of always dying
- zombies breaking doors
- other Hard survival mechanics still behaving normally

## Default config

```yaml
damage-multiplier: 0.5
only-when-hard: true
ignored-entity-types: []
```

### Config options

- `damage-multiplier`
  - multiplies hostile mob damage dealt to players
  - `0.5` means players take half damage
- `only-when-hard`
  - if `true`, the plugin only applies its changes when the world difficulty is `hard`
- `ignored-entity-types`
  - list of Bukkit entity type names to ignore completely

## Install

1. Download the latest jar from [Releases](https://github.com/enzomescall/paper-hard-mode-easy-damage/releases)
2. Put the jar in your server's `plugins/` folder
3. Start or restart the server
4. Keep your Minecraft difficulty set to `hard`

## Commands

- `/hardmodeeasydamage reload`
- `/hmed reload`

## Build from source

```bash
mvn clean package
```

Built jar:

```bash
target/paper-hard-mode-easy-damage-0.1.0.jar
```

## Compatibility

- Paper `1.21.4`
- Java `21`

## Why this exists

Minecraft's built-in difficulty settings tie a lot of useful survival mechanics to mob damage. This plugin splits those concerns apart in a very simple way: leave the world on Hard, but tone down mob damage against players.

## License

Add a license if you want to distribute or accept contributions more formally.
