# RandomTokenAbilities - Minecraft Plugin

A fully working Minecraft plugin for Paper/Spigot 1.21+ that gives players random token abilities.

## Features

- **15 Unique Token Abilities** - Each player gets one random token on join
- **Persistent Storage** - Tokens are saved using YAML and persist across server restarts
- **Configurable** - Enable/disable tokens and adjust ability settings
- **Particle Effects & Sounds** - Visual and audio feedback for all abilities
- **Balanced Gameplay** - Configurable cooldowns and damage values

## Token Abilities

1. **Fire God** - Shoot fireballs (right-click) + fire immunity
2. **Thunder Strike** - Lightning strikes on hit
3. **Speed Demon** - Speed 4 + Jump Boost 3 (passive)
4. **Tank Mode** - 40 HP + Resistance 2 (passive)
5. **Teleporter** - Sneak + right-click to teleport forward
6. **Lifesteal** - Heal 20% of damage dealt to players
7. **Freeze Touch** - Slow enemies on hit
8. **Gravity Control** - 80% fall damage reduction + slow falling
9. **Invisibility Cloak** - Sneak + right-click for invisibility
10. **Explosion Master** - Small explosions on attack
11. **Archer King** - Arrows deal 2x damage
12. **Time Slow** - Slow nearby enemies (passive aura)
13. **Shield Aura** - 30% damage reduction (passive)
14. **Poison Curse** - Poison enemies on hit
15. **Lucky God** - Random buffs every 30 seconds

## Commands

- `/token` - Get a new random token
- `/mytoken` - View your current token
- `/resettoken <player>` - Reset a player's token (admin only)

## Installation

1. **Build the plugin:**
   ```bash
   mvn clean package
   ```

2. **Copy the JAR:**
   - Find `RandomTokenAbilities.jar` in the `target/` folder
   - Copy it to your server's `plugins/` folder

3. **Start your server:**
   - The plugin will generate `config.yml` and `tokens.yml`

4. **Configure (optional):**
   - Edit `plugins/RandomTokenAbilities/config.yml` to customize abilities

## Configuration

```yaml
# Enable/Disable specific tokens
tokens:
  fire_god: true
  thunder_strike: true
  # ... etc

# Ability settings
settings:
  teleporter_distance: 10
  lifesteal_percent: 20
  freeze_duration: 3
  invisibility_duration: 10
  explosion_damage: 3.0
  archer_damage_multiplier: 2.0
  time_slow_radius: 10
  shield_aura_reduction: 0.3
  poison_duration: 5
  lucky_god_interval: 30
```

## Requirements

- Java 21+
- Paper/Spigot 1.21+
- Maven (for building)

## Project Structure

```
RandomTokenAbilities/
├── src/main/java/com/randomtoken/
│   ├── RandomTokenAbilities.java (Main plugin class)
│   ├── Token.java (Token enum)
│   ├── TokenManager.java (Storage & management)
│   └── abilities/
│       ├── AbilityManager.java
│       ├── TokenAbility.java (Base class)
│       ├── FireGodAbility.java
│       ├── ThunderStrikeAbility.java
│       ├── SpeedDemonAbility.java
│       ├── TankModeAbility.java
│       ├── TeleporterAbility.java
│       ├── LifestealAbility.java
│       ├── FreezeTouchAbility.java
│       ├── GravityControlAbility.java
│       ├── InvisibilityCloakAbility.java
│       ├── ExplosionMasterAbility.java
│       ├── ArcherKingAbility.java
│       ├── TimeSlowAbility.java
│       ├── ShieldAuraAbility.java
│       ├── PoisonCurseAbility.java
│       └── LuckyGodAbility.java
├── src/main/resources/
│   ├── plugin.yml
│   └── config.yml
└── pom.xml
```

## Building

```bash
# Clone or download the project
cd RandomTokenAbilities

# Build with Maven
mvn clean package

# Output: target/RandomTokenAbilities.jar
```

## Permissions

- `randomtoken.admin` - Access to `/resettoken` command (default: op)

## Data Storage

Player tokens are stored in `plugins/RandomTokenAbilities/tokens.yml`:
```yaml
<player-uuid>: TOKEN_NAME
```

## License

Free to use and modify.
