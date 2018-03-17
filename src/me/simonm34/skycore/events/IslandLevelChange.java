package me.simonm34.skycore.events;

import me.simonm34.skyblock.island.Island;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.api.events.IslandLevelChangeEvent;
import me.simonm34.skycore.user.User;
import org.bukkit.Bukkit;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class IslandLevelChange {
    public IslandLevelChange() {
        Bukkit.getServer().getPluginManager().registerEvent(IslandLevelChangeEvent.class, new Listener(){}, EventPriority.NORMAL, ($, event) -> {
            IslandLevelChangeEvent e = (IslandLevelChangeEvent) event;
            Island island = e.getIsland();

            island.getAllMembers().forEach(uuid -> {
                User user = Core.getCore().getUserManager().getUser(uuid);
                if (user != null)
                    Core.getCore().getScoreboardsManager().updateLevel(user);
            });
        }, Core.getCore());
    }
}
