package me.simonm34.skycore.events;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.user.User;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {
    public PlayerMove() {
        Bukkit.getServer().getPluginManager().registerEvent(PlayerMoveEvent.class, new Listener(){}, EventPriority.NORMAL, ($, event) -> {
            PlayerMoveEvent e = (PlayerMoveEvent) event;
            User user = Core.getCore().getUserManager().getUser(e.getPlayer().getUniqueId());
            if (!user.isTeleporting()) return;

            if (e.getFrom().getBlockX() != e.getTo().getBlockX() ||
                    e.getFrom().getBlockY() != e.getTo().getBlockZ() ||
                    e.getFrom().getBlockZ() != e.getTo().getBlockZ()) {
                user.setTeleporting(false);
                user.sendMsg("&cTeleportation canceled. Don't move!");
            }
        }, Core.getCore());
    }
}
