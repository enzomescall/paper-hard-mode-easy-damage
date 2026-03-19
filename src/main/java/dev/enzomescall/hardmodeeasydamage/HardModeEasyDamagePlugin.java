package dev.enzomescall.hardmodeeasydamage;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public final class HardModeEasyDamagePlugin extends JavaPlugin implements Listener {
    private double damageMultiplier;
    private boolean onlyWhenHard;
    private Set<EntityType> ignoredEntityTypes = Collections.emptySet();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadSettings();
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("HardModeEasyDamage enabled. Multiplier=" + damageMultiplier + ", onlyWhenHard=" + onlyWhenHard);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            reloadConfig();
            reloadSettings();
            sender.sendMessage("HardModeEasyDamage config reloaded.");
            return true;
        }
        sender.sendMessage("Usage: /" + label + " reload");
        return true;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        if (onlyWhenHard && event.getEntity().getWorld().getDifficulty() != Difficulty.HARD) {
            return;
        }

        Entity attacker = resolveAttacker(event.getDamager());
        if (!(attacker instanceof LivingEntity livingAttacker)) {
            return;
        }

        if (attacker instanceof Player) {
            return;
        }

        if (attacker instanceof Tameable tameable && tameable.isTamed()) {
            return;
        }

        if (!livingAttacker.getType().isAlive()) {
            return;
        }

        if (ignoredEntityTypes.contains(livingAttacker.getType())) {
            return;
        }

        event.setDamage(event.getDamage() * damageMultiplier);
    }

    private Entity resolveAttacker(Entity damager) {
        if (damager instanceof Projectile projectile && projectile.getShooter() instanceof Entity shooterEntity) {
            return shooterEntity;
        }
        return damager;
    }

    private void reloadSettings() {
        FileConfiguration config = getConfig();
        damageMultiplier = clamp(config.getDouble("damage-multiplier", 0.5D), 0.0D, 1.0D);
        onlyWhenHard = config.getBoolean("only-when-hard", true);
        ignoredEntityTypes = parseEntityTypes(config.getStringList("ignored-entity-types"));
    }

    private Set<EntityType> parseEntityTypes(List<String> rawNames) {
        EnumSet<EntityType> parsed = EnumSet.noneOf(EntityType.class);
        for (String rawName : rawNames) {
            if (rawName == null || rawName.isBlank()) {
                continue;
            }
            try {
                parsed.add(EntityType.valueOf(rawName.trim().toUpperCase(Locale.ROOT)));
            } catch (IllegalArgumentException ignored) {
                getLogger().warning("Ignoring unknown entity type in config: " + rawName);
            }
        }
        return parsed.stream().collect(Collectors.toUnmodifiableSet());
    }

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}
