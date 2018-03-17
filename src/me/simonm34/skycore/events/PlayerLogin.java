package me.simonm34.skycore.events;

import me.simonm34.skycore.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLogin implements Listener {
    public PlayerLogin() {
        Bukkit.getServer().getPluginManager().registerEvent(PlayerLoginEvent.class, new Listener(){}, EventPriority.NORMAL, ($, event) -> {
            PlayerLoginEvent e = (PlayerLoginEvent) event;
            Player player = e.getPlayer();

            Core.getCore().getUserManager().loadUser(player);
            Core.getCore().getSenderManager().loadSender(player);

            Core.getCore().getPunishmentManager().kickIfBanned(Core.getCore().getUserManager().getUser(player.getUniqueId()), e);
        }, Core.getCore());
    }
}
