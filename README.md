# HardModeEasyDamage

Tiny Paper plugin for a very specific goal:

- keep the server on **Hard** difficulty
- reduce **hostile mob damage against players** to an easy-like level
- keep the rest of Hard-mode mechanics intact

## What it does

When a hostile mob (or its projectile) damages a player, the plugin applies a configurable multiplier to the final damage.

Examples of hard-mode behavior that still remain because the server itself stays on Hard:

- villagers can convert instead of always dying
- zombies can break doors
- other hard-only survival mechanics still work normally

## Default config

```yaml
damage-multiplier: 0.5
only-when-hard: true
ignored-entity-types: []
```

## Build

```bash
export JAVA_HOME=/usr/lib/jvm/jdk-21.0.4-oracle-aarch64
mvn clean package
```

Built jar:

```bash
target/paper-hard-mode-easy-damage-0.1.0.jar
```

## Install

Copy the jar into the server's `plugins/` folder and restart the server.

## Command

- `/hardmodeeasydamage reload`
- alias: `/hmed reload`
