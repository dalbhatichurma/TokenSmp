package com.randomtoken;

import org.bukkit.ChatColor;

public enum Token {
    FIRE_GOD("Fire God", ChatColor.RED + "Fire God", "Shoot fireballs + fire immunity"),
    THUNDER_STRIKE("Thunder Strike", ChatColor.YELLOW + "Thunder Strike", "Lightning on hit"),
    SPEED_DEMON("Speed Demon", ChatColor.AQUA + "Speed Demon", "Extreme speed + jump boost"),
    TANK_MODE("Tank Mode", ChatColor.DARK_GRAY + "Tank Mode", "Double health + resistance"),
    TELEPORTER("Teleporter", ChatColor.LIGHT_PURPLE + "Teleporter", "Right click to teleport forward"),
    LIFESTEAL("Lifesteal", ChatColor.DARK_RED + "Lifesteal", "Gain health when hitting players"),
    FREEZE_TOUCH("Freeze Touch", ChatColor.BLUE + "Freeze Touch", "Slow enemies on hit"),
    GRAVITY_CONTROL("Gravity Control", ChatColor.WHITE + "Gravity Control", "Reduce fall damage + float ability"),
    INVISIBILITY_CLOAK("Invisibility Cloak", ChatColor.GRAY + "Invisibility Cloak", "Temporary invisibility"),
    EXPLOSION_MASTER("Explosion Master", ChatColor.GOLD + "Explosion Master", "Small explosions on attack"),
    ARCHER_KING("Archer King", ChatColor.GREEN + "Archer King", "Arrows deal double damage"),
    TIME_SLOW("Time Slow", ChatColor.DARK_PURPLE + "Time Slow", "Slow nearby enemies"),
    SHIELD_AURA("Shield Aura", ChatColor.DARK_AQUA + "Shield Aura", "Passive damage reduction"),
    POISON_CURSE("Poison Curse", ChatColor.DARK_GREEN + "Poison Curse", "Poison enemies on hit"),
    LUCKY_GOD("Lucky God", ChatColor.YELLOW + "Lucky God", "Random buffs every 30 seconds");

    private final String name;
    private final String displayName;
    private final String description;

    Token(String name, String displayName, String description) {
        this.name = name;
        this.displayName = displayName;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }
}
