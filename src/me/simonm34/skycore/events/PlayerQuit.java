package me.simonm34.skycore.events;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.user.User;
import me.simonm34.skycore.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
    public PlayerQuit() {
        Bukkit.getServer().getPluginManager().registerEvent(PlayerQuitEvent.class, new Listener(){}, EventPriority.NORMAL, ($, event) -> {
            PlayerQuitEvent e = (PlayerQuitEvent) event;
            Player player = e.getPlayer();
            e.setQuitMessage(null);

            User user = Core.getCore().getUserManager().getUser(player.getUniqueId());
            if (user.getSpectating() != null) {
                Warp warp = Core.getCore().getWarpManager().getWarp("spawn");
                if (warp != null)
                    user.teleport(warp.getLocation());
            }
            if (user.getSpectator() != null) {
                User target = Core.getCore().getUserManager().getUser(user.getSpectating());
                if (target != null) {
                    target.sendMsg("&e" + target.getName() + " &cleft the game whilst you were spectating!");
                    target.setSpectating(null);
                    return;
                }
                user.setSpectator(null);
            }

            Core.getCore().getUserManager().saveUser(player);
            Core.getCore().getSenderManager().saveSender(player);
        }, Core.getCore());
    }
}
