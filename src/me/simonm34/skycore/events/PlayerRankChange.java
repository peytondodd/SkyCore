package me.simonm34.skycore.events;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.api.events.PlayerRankChangeEvent;
import me.simonm34.skycore.user.User;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerRankChange implements Listener {
    public PlayerRankChange() {
        Bukkit.getServer().getPluginManager().registerEvent(PlayerRankChangeEvent.class, new Listener(){}, EventPriority.NORMAL, ($, event) -> {
            PlayerRankChangeEvent e = (PlayerRankChangeEvent) event;
            User user = e.getUser();

            Core.getCore().getScoreboardsManager().updateRank(user);
            user.getPlayer().setPlayerListName(Core.getCore().getUtils().getColor(user.getRank().getPrefix() + user.getRank().getChatColor() + user.getName()));
        }, Core.getCore());
    }
}
