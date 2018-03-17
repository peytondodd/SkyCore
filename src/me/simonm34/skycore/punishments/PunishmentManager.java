package me.simonm34.skycore.punishments;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.user.User;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.Optional;
import java.util.UUID;

public class PunishmentManager {
    public void addPunishment(UUID uuid, PunishType punishType, String reason, String by, Long expire) {
        Punishment punishment = new Punishment(punishType, reason, by, true, expire + System.currentTimeMillis());
        User user = Core.getCore().getUserManager().getUser(uuid);
        if (user == null) {
            FileConfiguration data = Core.getCore().getUserFile().setupUser(uuid);
            int size = data.getConfigurationSection("punishments." + punishment.getPunishType().name()) == null ? 1 : data.getConfigurationSection("punishments." + punishment.getPunishType().name()).getKeys(false).size();
            data.set("punishments." + punishment.getPunishType().name() + "." + size + ".reason", punishment.getReason());
            data.set("punishments." + punishment.getPunishType().name() + "." + size + ".by", punishment.getBy());
            data.set("punishments." + punishment.getPunishType().name() + "." + size + ".expire", punishment.getExpire() == null ? "Never" : punishment.getExpire());
            data.set("punishments." + punishment.getPunishType().name() + "." + size + ".active", punishment.isActive());
            Core.getCore().getUserFile().saveUser();
            return;
        }
        user.addPunishment(punishment);
        kickIfBanned(user, null);
    }

    public Optional<Punishment> getActiveBanPunishment(User user) {
        return user.getPunishments().stream()
                .filter(Punishment::isActive)
                .filter(punishment -> !punishment.getPunishType().canConnect())
                .filter(punishment -> punishment.getExpire() == null || punishment.getExpire() - System.currentTimeMillis() > 0)
                .findAny();
    }
    public Optional<Punishment> getActiveMutePunishment(User user) {
        return user.getPunishments().stream()
                .filter(Punishment::isActive)
                .filter(punishment -> punishment.getPunishType() == PunishType.TEMP_MUTE || punishment.getPunishType() == PunishType.MUTE)
                .filter(punishment -> punishment.getExpire() == null || punishment.getExpire() - System.currentTimeMillis() > 0)
                .findAny();
    }
    public void kickIfBanned(User user, PlayerLoginEvent e) {
        getActiveBanPunishment(user).ifPresent(punishment -> {
            String message = ChatColor.translateAlternateColorCodes('&', "&6&lSkyTreasures &8» &ePunishments" +
                    "\n" +
                    "&7You are &e" + punishment.getPunishType().getName() + " &7from the server!" +
                    "\n" +
                    "\n" +
                    "&7Reason: &e" + punishment.getReason() +
                    "\n" +
                    "&7By: &e" + punishment.getBy() +
                    "\n" +
                    "&7Expire: &e" + (punishment.getExpire() == null ? "Never" : Core.getCore().getUtils().formatTime(punishment.getExpire())));
            if (e == null)
                user.getPlayer().kickPlayer(message);
            else
                e.disallow(PlayerLoginEvent.Result.KICK_BANNED, message);
        });
    }
}
