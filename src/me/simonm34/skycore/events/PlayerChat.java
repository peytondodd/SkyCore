package me.simonm34.skycore.events;

import me.simonm34.skyblock.island.Island;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.chat.ChatType;
import me.simonm34.skycore.punishments.Punishment;
import me.simonm34.skycore.sender.Sender;
import me.simonm34.skycore.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Optional;
import java.util.UUID;

public class PlayerChat implements Listener {
    public PlayerChat() {
        Bukkit.getServer().getPluginManager().registerEvent(AsyncPlayerChatEvent.class, new Listener(){}, EventPriority.NORMAL, ($, event) -> {
            AsyncPlayerChatEvent e = (AsyncPlayerChatEvent) event;
            User user = Core.getCore().getUserManager().getUser(e.getPlayer().getUniqueId());
            String message = e.getMessage();

            Optional<Punishment> mute = Core.getCore().getPunishmentManager().getActiveMutePunishment(user);
            if (mute.isPresent()) {
                Punishment punishment = mute.get();
                user.sendMsg("&6&lSkyTreasures &8» &ePunishments" +
                        "\n" +
                        "&7You are &e" + punishment.getPunishType().getName() + " &7from the server!" +
                        "\n" +
                        "\n" +
                        "&7Reason: &e" + punishment.getReason() +
                        "\n" +
                        "&7By: &e" + punishment.getBy() +
                        "\n" +
                        "&7Expire: &e" + (punishment.getExpire() == null ? "Never" : Core.getCore().getUtils().formatTime(punishment.getExpire())));
                e.setCancelled(true);
                return;
            }

            if (!user.hasPerm("skycore.chat.cooldown.bypass")) {
                Long cooldown = user.getChatCooldown();
                if (cooldown != null) {
                    if (cooldown - System.currentTimeMillis() > 0) {
                        user.sendMsg("&cYou can send another message in &e" + ((cooldown - System.currentTimeMillis()) / 1000) + " &cseconds!");
                        e.setCancelled(true);
                        return;
                    }
                }
            }
            if (!user.hasPerm("skycore.chat.swears.bypass")) {
                for (String word : Core.getCore().getSettings().getSwears()) {
                    if (message.toLowerCase().contains(word.toLowerCase())) {
                        user.sendMsg("&cPlease do not swear!");
                        e.setCancelled(true);
                        return;
                    }
                }
            }
            user.setChatCooldown(System.currentTimeMillis() + 3000);

            message = format(message, user);
            switch (user.getChat()) {
                case STAFF:
                    for (User user1 : Core.getCore().getUserManager().getUsers())
                        if (user1.hasPerm("skycore.staffchat"))
                            user.sendRawMsg("&7(STAFF) " + user.getRank().getPrefix() + user.getName() + " &8» &f" + message);
                    Sender console = Core.getCore().getSenderManager().getConsole();
                    console.sendRawMsg("&7(STAFF) " + user.getRank().getPrefix() + user.getName() + " &8» &f" + message);
                    e.setCancelled(true);
                    return;
                case TEAM:
                    Island island = Core.getCore().getSkyblock().getIslandManager().getIsland(user.getUUID());
                    if (island != null) {
                        for (UUID uuid : island.getAllMembers()) {
                            User target = Core.getCore().getUserManager().getUser(uuid);
                            if (target != null)
                                user.sendRawMsg("&7(TEAM) " + user.getRank().getPrefix() + user.getName() + " &8» &f" + message);
                        }
                        e.setCancelled(true);
                        return;
                    }
                    user.sendMsg("&cYou do not have an island to talk to. Disabling team chat!");
                    user.setChat(ChatType.PUBLIC);
            }
            e.setFormat(ChatColor.translateAlternateColorCodes('&',
                    user.getRank().getPrefix() + user.getNickname() + " &8» " + user.getRank().getChatColor() + message));
        }, Core.getCore());
    }

    private String format(String message, User user) {
        if (user.hasPerm("skycore.chat.emoji"))
            message = message.replace(":)", "☺")
                    .replace(":-)", "☻")
                    .replace(":(", "☹")
                    .replace("<3", "❤");
        if (!user.hasPerm("skycore.chat.color"))
            message = message.replaceAll("[&][0-9A-f]", "");
        if (!user.hasPerm("skycore.chat.format"))
            message = message.replaceAll("[&][L-Ol-oKkRr]", "");
        return message;
    }
}
