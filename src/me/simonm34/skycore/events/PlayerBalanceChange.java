package me.simonm34.skycore.events;

import me.simonm34.skycore.Core;
import me.simonm34.skycore.api.events.PlayerBalanceChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerBalanceChange implements Listener {
    public PlayerBalanceChange() {
        Bukkit.getServer().getPluginManager().registerEvent(PlayerBalanceChangeEvent.class, new Listener(){}, EventPriority.NORMAL, ($, event) -> {
            PlayerBalanceChangeEvent e = (PlayerBalanceChangeEvent) event;
            Core.getCore().getScoreboardsManager().updateBalance(e.getUser());
        }, Core.getCore());
    }
}
