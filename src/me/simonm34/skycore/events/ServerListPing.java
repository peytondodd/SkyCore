package me.simonm34.skycore.events;

import me.simonm34.skycore.Core;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListPing implements Listener {
    public ServerListPing() {
        Bukkit.getServer().getPluginManager().registerEvent(ServerListPingEvent.class, new Listener(){}, EventPriority.NORMAL, ($, event) -> {
            ServerListPingEvent e = (ServerListPingEvent) event;
            e.setMotd(Core.getCore().getUtils().getColor(Core.getCore().getSettings().getServerMotd()));

            long onlinePlayers = Core.getCore().getUserManager().getUsers().stream()
                    .filter(user -> user.getSpectating() == null)
                    .count();
            e.setMaxPlayers((int) onlinePlayers + 1);
        }, Core.getCore());
    }
}
