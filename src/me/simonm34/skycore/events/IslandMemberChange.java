package me.simonm34.skycore.events;

import me.simonm34.skyblock.island.Island;
import me.simonm34.skycore.Core;
import me.simonm34.skycore.api.events.IslandMemberChangeEvent;
import me.simonm34.skycore.user.User;
import org.bukkit.Bukkit;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class IslandMemberChange {
    public IslandMemberChange() {
        Bukkit.getServer().getPluginManager().registerEvent(IslandMemberChangeEvent.class, new Listener(){}, EventPriority.NORMAL, ($, event) -> {
            IslandMemberChangeEvent e = (IslandMemberChangeEvent) event;
            Island island = e.getIsland();

            island.getAllMembers().forEach(uuid -> {
                User user = Core.getCore().getUserManager().getUser(uuid);
                if (user != null)
                    Core.getCore().getScoreboardsManager().updateMembers(user);
            });
        }, Core.getCore());
    }
}
